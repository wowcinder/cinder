/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.server;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;

/**
 * @author XuehuiHe
 * @date 2013年9月24日
 */
public class CleanHbase {
	public static void main(String[] args) throws IOException {
		Configuration config = HBaseConfiguration.create();
		HBaseAdmin admin = new HBaseAdmin(config);
		HTableDescriptor[] tables = admin.listTables();
		for (HTableDescriptor hTableDescriptor : tables) {
			admin.disableTable(hTableDescriptor.getName());
		}
		admin.close();
	}
}