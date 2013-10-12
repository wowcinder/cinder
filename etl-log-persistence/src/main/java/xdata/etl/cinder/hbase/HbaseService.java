/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.hbase;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;


/**
 * @author XuehuiHe
 * @date 2013年10月12日
 */
public interface HbaseService {
	public void save(Map<String, List<HbaseRecord<String>>> recordMap) throws IOException;
}
