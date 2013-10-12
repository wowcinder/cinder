/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;

/**
 * @author XuehuiHe
 * @date 2013年10月12日
 */
public class LazySaveServiceImpl implements LazySaveService {
	private int thresholdSize;

	private final List<Map<String, List<HbaseRecord<String>>>> recordMapList;
	@Autowired
	private HbaseService hbaseService;

	private final AtomicLong lastSaveTime;
	private final AtomicLong total;
	private final AtomicBoolean isSaving;

	public LazySaveServiceImpl() {
		recordMapList = Collections
				.synchronizedList(new ArrayList<Map<String, List<HbaseRecord<String>>>>());

		total = new AtomicLong();
		isSaving = new AtomicBoolean(false);

		lastSaveTime = new AtomicLong();
		lastSaveTime.set(System.currentTimeMillis());
	}

	public void save() throws IOException {
		if (!isSaving.compareAndSet(false, true)) {
			return;
		}
		if (!(total.get() >= thresholdSize)) {
			return;
		}
		List<Map<String, List<HbaseRecord<String>>>> recordMapList = new ArrayList<Map<String, List<HbaseRecord<String>>>>();
		synchronized (this) {
			recordMapList.addAll(this.recordMapList);
			this.recordMapList.clear();
			this.total.set(0);
		}
		Map<String, List<HbaseRecord<String>>> recordMap = new HashMap<String, List<HbaseRecord<String>>>();
		for (Map<String, List<HbaseRecord<String>>> itemMap : recordMapList) {
			for (Entry<String, List<HbaseRecord<String>>> entry : itemMap
					.entrySet()) {
				String name = entry.getKey();
				if (!recordMap.containsKey(name)) {
					recordMap.put(name, new ArrayList<HbaseRecord<String>>());
				}
				recordMap.get(name).addAll(entry.getValue());
			}
		}
		hbaseService.save(recordMap);
		isSaving.set(false);
	}

	@Override
	public void lazySave(Map<String, List<HbaseRecord<String>>> recordMap)
			throws IOException {
		synchronized (this) {
			this.recordMapList.add(recordMap);
			total.incrementAndGet();
		}
		if (total.get() >= thresholdSize) {
			save();
		}
	}
}
