/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.logmodel.transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn;
import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelSimpleColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import xdata.etl.logmodel.transformer.exception.LogTransformException;

/**
 * @author XuehuiHe
 * @date 2013年9月24日
 */
public class CTypeLogModelTransformer extends
		LogModelTransformer<CTypeLogModelVersion> {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CTypeLogModelTransformer.class);

	private final List<CTypeLogModelColumn> columns;
	private final Integer mainSize;
	private CTypeLogModelGroupColumn repeatedGroupColumn;
	private Integer repeatedSize;

	public CTypeLogModelTransformer(CTypeLogModelVersion version) {
		super(version);
		columns = getVersion().getRootNode().getColumns();
		for (CTypeLogModelColumn column : columns) {
			if (column instanceof CTypeLogModelGroupColumn) {
				if (repeatedGroupColumn == null) {
					repeatedGroupColumn = (CTypeLogModelGroupColumn) column;
				} else {
					String msg = getVersion().getModel().getName() + "#"
							+ getVersion().getVersion() + " 有多个repeated column";
					LOGGER.warn(msg);
					throw new LogTransformException(msg);
				}
			}
		}
		if (repeatedGroupColumn == null) {
			mainSize = columns.size();
		} else {
			mainSize = columns.size() - 1;
			repeatedSize = repeatedGroupColumn.getColumns().size();
		}
	}

	@Override
	public Map<String, List<HbaseRecord<String>>> transform(String log) {
		final String raw = log;
		Map<String, List<HbaseRecord<String>>> map = new HashMap<String, List<HbaseRecord<String>>>();
		int index = log.indexOf('#');
		if (index == -1) {
			String msg = "stamp not found\traw:" + raw;
			LOGGER.warn(msg);
			throw new LogTransformException(msg);
		}
		String stampStr = log.substring(0, index);
		log = log.substring(index + 1);
		Date stamp = getStamp(stampStr);
		CTypeLogHelper helper = new CTypeLogHelper(log);
		if (!helper.isMatchColumns()) {
			String msg = "columns not match\t"
					+ getVersion().getModel().getName() + ",raw:" + raw
					+ "\tmsg:" + helper.getColumnsMatchMsg();
			throw new LogTransformException(msg);
		}
		Integer repeatedTimes = helper.getRepeatTimes();
		String[] strs = helper.strs;
		String key = generateKey(stamp);

		HbaseRecord<String> record = new HbaseRecord<String>(stamp,
				getVersion().getRootNode().getHbaseTableVersion().getVersion());
		putInMap(map, getVersion().getRootNode().getHbaseTableVersion()
				.getTable().getName(), record);

		record.setKey(key);
		int i = 0;
		for (CTypeLogModelColumn itemColumn : columns) {
			if (itemColumn instanceof CTypeLogModelSimpleColumn) {
				CTypeLogModelSimpleColumn simpleColumn = (CTypeLogModelSimpleColumn) itemColumn;
				HbaseTableColumn hbaseColumn = simpleColumn
						.getHbaseTableColumn();
				Object t = null;
				try {
					t = TypeParser.parse(strs[i], hbaseColumn.getType()
							.getClazz());
				} catch (Exception e) {
					LOGGER.warn("parse wrong:str:" + strs[i] + ",table:"
							+ hbaseColumn.getVersion().getTable().getName()
							+ ",version:"
							+ hbaseColumn.getVersion().getVersion()
							+ ",column:" + hbaseColumn.getName() + ",type:"
							+ hbaseColumn.getType().getClazz().getSimpleName());
				}
				if (t != null) {
					record.getData().put(hbaseColumn.getName(), t);
				}
				i++;
			} else {
				CTypeLogModelGroupColumn groupColumn = (CTypeLogModelGroupColumn) itemColumn;
				for (int j = 0; j < repeatedTimes; j++) {
					HbaseRecord<String> subRecord = new HbaseRecord<String>(
							stamp, groupColumn.getHbaseTableVersion()
									.getVersion());
					putInMap(map, groupColumn.getHbaseTableVersion().getTable()
							.getName(), subRecord);
					subRecord.setKey(generateKey(key, j + 1));
					for (CTypeLogModelColumn column : groupColumn.getColumns()) {
						CTypeLogModelSimpleColumn simpleColumn = (CTypeLogModelSimpleColumn) column;
						HbaseTableColumn hbaseColumn = simpleColumn
								.getHbaseTableColumn();
						Object t = null;
						try {
							t = TypeParser.parse(strs[i], hbaseColumn.getType()
									.getClazz());
						} catch (Exception e) {
							LOGGER.warn("parse wrong:str:"
									+ strs[i]
									+ ",table:"
									+ hbaseColumn.getVersion().getTable()
											.getName()
									+ ",version:"
									+ hbaseColumn.getVersion().getVersion()
									+ ",column:"
									+ hbaseColumn.getName()
									+ ",type:"
									+ hbaseColumn.getType().getClazz()
											.getSimpleName());
						}
						if (t != null) {
							subRecord.getData().put(hbaseColumn.getName(), t);
						}
						i++;
					}
				}
			}
		}
		return map;
	}

	protected void putInMap(Map<String, List<HbaseRecord<String>>> map,
			String name, HbaseRecord<String> record) {
		if (!map.containsKey(name)) {
			map.put(name, new ArrayList<HbaseRecord<String>>());
		}
		map.get(name).add(record);
	}

	public List<CTypeLogModelColumn> getColumns() {
		return columns;
	}

	public CTypeLogModelGroupColumn getRepeatedGroupColumn() {
		return repeatedGroupColumn;
	}

	public class CTypeLogHelper {
		private final String raw;
		private boolean isVerticalEnding = false;
		protected String[] strs;

		public CTypeLogHelper(String raw) {
			this.raw = raw;
			preprocess();
		}

		protected void preprocess() {
			if (raw.charAt(raw.length() - 1) == '|') {
				isVerticalEnding = true;
			}
			strs = raw.split("\\||\\t");
			if (isVerticalEnding) {
				String[] tempStrs = new String[strs.length + 1];
				for (int i = 0; i < strs.length; i++) {
					tempStrs[i] = strs[i];
				}
				tempStrs[strs.length] = "";
				strs = tempStrs;
			}
		}

		protected boolean isMatchColumns() {
			if (repeatedGroupColumn == null) {
				if (strs.length == mainSize) {
					return true;
				} else if (strs.length == mainSize + 1 && isVerticalEnding) {
					strs = Arrays.copyOf(strs, strs.length - 1);
					return true;
				}
			} else {
				if ((strs.length - mainSize) % repeatedSize == 0) {
					return true;
				} else if ((strs.length - mainSize - 1) % repeatedSize == 0
						&& isVerticalEnding) {
					strs = Arrays.copyOf(strs, strs.length - 1);
					return true;
				}
			}
			return false;
		}

		protected Integer getRepeatTimes() {
			if (repeatedGroupColumn != null) {
				return (strs.length - mainSize) / repeatedSize;
			}
			return 0;
		}

		protected String getColumnsMatchMsg() {
			String info = "fact:" + strs.length;
			if (isVerticalEnding) {
				info += "or " + (strs.length - 1);
			}
			info += ",expect:";
			if (repeatedGroupColumn == null) {
				info += mainSize;
			} else {
				info += mainSize + "+" + repeatedSize + "x";
			}
			return info;
		}
	}
}
