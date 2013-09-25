/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.logmodel.transformer;

import java.util.List;
import java.util.Map;

import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import xdata.etl.logmodel.transformer.helper.JsonArrayMap;
import xdata.etl.logmodel.transformer.helper.JsonLogModelTransformerHelper;

/**
 * @author XuehuiHe
 * @date 2013年9月24日
 */
public class JsonLogModelTransformer extends
		LogModelTransformer<JsonLogModelVersion> {

	private final JsonArrayMap jsonMap;

	public JsonLogModelTransformer(JsonLogModelVersion version) {
		super(version);
		jsonMap = JsonArrayMap.create(version.getRootNode());

	}

	@Override
	public Map<String, List<HbaseRecord<String>>> transform(String log) {
		JsonLogModelTransformerHelper helper = new JsonLogModelTransformerHelper(
				log, jsonMap);
		return helper.analyze();
	}
}
