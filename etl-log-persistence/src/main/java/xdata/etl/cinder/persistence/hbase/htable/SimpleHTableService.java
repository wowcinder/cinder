/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.persistence.hbase.htable;

import java.io.IOException;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.springframework.stereotype.Service;

/**
 * @author XuehuiHe
 * @date 2013年10月12日
 */
@Service
public class SimpleHTableService extends AbstractHTableService {

	public SimpleHTableService() {
	}

	public HTableInterface getHTable(String tableName) throws IOException {
		HTable htable = new HTable(getHbaseConfig(), tableName);
		return htable;
	}

	@Override
	public void shutdown() {
	}

}
