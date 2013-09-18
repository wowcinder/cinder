package xdata.etl.kafka.transform.cclient.data;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import xdata.etl.hbase.entity.HbaseAttachment;
import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.kafka.exception.KafkaTransformException;
import xdata.etl.kafka.exception.TerminalFieldValueParseException;
import xdata.etl.kafka.transform.cclient.column.CClientColumnInfo;
import xdata.etl.util.ClassNameUtil;

public abstract class AbstractCClientDataSwapper {
	protected List<String> mainPartData;
	protected List<List<String>> attachmentDatas;

	protected int repeatStartIndex = -1;
	protected List<CClientColumnInfo> mainPartColumnInfos;
	protected List<CClientColumnInfo> attachmentColumnInfos;
	protected Class<? extends HbaseAttachment> attachmentClass;
	protected Class<? extends HbaseEntity> mainPartClass;
	protected String[] strs;
	protected String raw;

	protected Field attachmentField;

	public AbstractCClientDataSwapper(String raw) {
		this.raw = raw;
		mainPartData = new ArrayList<String>();
		attachmentDatas = new ArrayList<List<String>>();
		preRaw();
	}

	abstract protected void preRaw();

	protected boolean doMatch() {
		if (attachmentClass == null) {
			if (strs.length == mainPartColumnInfos.size()) {
				return true;
			}
		} else {
			if ((strs.length - mainPartColumnInfos.size())
					% attachmentColumnInfos.size() == 0) {
				return true;
			}
		}
		return false;
	}

	public HbaseEntity build() throws KafkaTransformException,
			TerminalFieldValueParseException {
		if (!doMatch()) {
			throw new KafkaTransformException(this.mainPartClass, raw,
					"column match exception");
		}
		dealRaw();
		HbaseEntity entity = null;
		try {
			entity = mainPartClass.newInstance();
			for (int i = 0; i < mainPartColumnInfos.size(); i++) {
				CClientColumnInfo columnInfo = mainPartColumnInfos.get(i);
				fillAttri(entity, columnInfo, mainPartData.get(i));
			}
			if (attachmentClass != null) {
				List<HbaseAttachment> attachments = getAttachmentList(entity);
				attachments.addAll(getAttachmentList());
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return entity;
	}

	private List<HbaseAttachment> getAttachmentList()
			throws TerminalFieldValueParseException {
		List<HbaseAttachment> list = new ArrayList<HbaseAttachment>();
		for (List<String> strList : attachmentDatas) {
			HbaseAttachment attachment = null;
			try {
				attachment = attachmentClass.newInstance();
				for (int i = 0; i < attachmentColumnInfos.size(); i++) {
					CClientColumnInfo columnInfo = attachmentColumnInfos.get(i);
					fillAttri(attachment, columnInfo, strList.get(i));
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (attachment != null) {
				list.add(attachment);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	private List<HbaseAttachment> getAttachmentList(HbaseEntity entity) {
		String attriName = attachmentField.getName();
		String methodName = ClassNameUtil.getGetMethodName(attriName);
		Method method;
		List<HbaseAttachment> list = null;
		try {
			method = mainPartClass.getMethod(methodName);
			list = (List<HbaseAttachment>) method.invoke(entity);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void fillAttri(HbaseEntity t, CClientColumnInfo columnInfo,
			String bytes) throws TerminalFieldValueParseException {
		Field field = columnInfo.getField();
		Class<?> attrClazz = field.getType();
		String attrName = field.getName();
		String methodName = ClassNameUtil.getSetMethodName(attrName);
		try {
			Method method = t.getClass().getMethod(methodName, attrClazz);
			method.invoke(t, getValue(columnInfo, bytes));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void dealRaw() {
		if (repeatStartIndex == -1) {
			repeatStartIndex = mainPartColumnInfos.size();
		}
		for (int i = 0; i < repeatStartIndex; i++) {
			mainPartData.add(strs[i]);
		}
		if (attachmentClass != null) {
			int srvCount = (strs.length - mainPartColumnInfos.size())
					/ attachmentColumnInfos.size();
			int mainStart2 = repeatStartIndex + srvCount
					* attachmentColumnInfos.size();
			for (int i = mainStart2; i < strs.length; i++) {
				mainPartData.add(strs[i]);
			}
			for (int i = 0; i < srvCount; i++) {
				int start = repeatStartIndex + i * attachmentColumnInfos.size();
				int end = start + attachmentColumnInfos.size();
				List<String> srvRowInfo = new ArrayList<String>();
				for (int j = start; j < end; j++) {
					srvRowInfo.add(strs[j]);
				}
				attachmentDatas.add(srvRowInfo);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue(CClientColumnInfo columnInfo, String b)
			throws UnsupportedEncodingException,
			TerminalFieldValueParseException {
		if (b == null) {
			return null;
		}
		Class<?> clazz = columnInfo.getField().getType();
		b = b.trim();
		if (!clazz.equals(String.class) && "".equals(b)) {
			return null;
		}

		try {
			if (clazz.equals(boolean.class) || clazz.equals(Boolean.class)) {
				return (T) new Boolean(b);
			} else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
				return (T) new Long(b);
			} else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
				return (T) new Short(b);
			} else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
				return (T) new Double(b);
			} else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
				return (T) new Float(b);
			} else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
				return (T) new Integer(b);
			} else if (clazz.equals(String.class)) {
				return (T) b;
			} else if (clazz.equals(BigDecimal.class)) {
				return (T) new BigDecimal(b);
			} else if (clazz.equals(Date.class)) {
				return (T) getDate(b);
			} else if (clazz.equals(BigInteger.class)) {
				return (T) new BigInteger(b);
			}
		} catch (Exception e) {
			TerminalFieldValueParseException ex = new TerminalFieldValueParseException(
					this.mainPartClass, raw);
			ex.setFieldStr(b);
			ex.setTargetField(columnInfo.getField());
			throw ex;
		}

		return null;
	}

	public static final SimpleDateFormat sf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	protected Date getDate(String d) throws ParseException {
		if (d.matches("^\\d+$")) {
			Long time = new Long(d);
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(time);
			return c.getTime();
		} else {
			return sf.parse("2" + d);
		}
	}

	public List<String> getMainPartData() {
		return mainPartData;
	}

	public List<List<String>> getAttachmentDatas() {
		return attachmentDatas;
	}

	public int getRepeatStartIndex() {
		return repeatStartIndex;
	}

	public List<CClientColumnInfo> getMainPartColumnInfos() {
		return mainPartColumnInfos;
	}

	public List<CClientColumnInfo> getAttachmentColumnInfos() {
		return attachmentColumnInfos;
	}

	public Class<? extends HbaseAttachment> getAttachmentClass() {
		return attachmentClass;
	}

	public void setMainPartData(List<String> mainPartData) {
		this.mainPartData = mainPartData;
	}

	public void setAttachmentDatas(List<List<String>> attachmentDatas) {
		this.attachmentDatas = attachmentDatas;
	}

	public void setRepeatStartIndex(int repeatStartIndex) {
		this.repeatStartIndex = repeatStartIndex;
	}

	public void setMainPartColumnInfos(
			List<CClientColumnInfo> mainPartColumnInfos) {
		this.mainPartColumnInfos = mainPartColumnInfos;
	}

	public void setAttachmentColumnInfos(
			List<CClientColumnInfo> attachmentColumnInfos) {
		this.attachmentColumnInfos = attachmentColumnInfos;
	}

	public void setAttachmentClass(
			Class<? extends HbaseAttachment> attachmentClass) {
		this.attachmentClass = attachmentClass;
	}

	public Field getAttachmentField() {
		return attachmentField;
	}

	public void setAttachmentField(Field attachmentField) {
		this.attachmentField = attachmentField;
	}

	public Class<? extends HbaseEntity> getMainPartClass() {
		return mainPartClass;
	}

	public void setMainPartClass(Class<? extends HbaseEntity> mainPartClass) {
		this.mainPartClass = mainPartClass;
	}
}
