package xdata.etl.hbase.helper;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.stereotype.Component;

import xdata.etl.RunableCLI;
import xdata.etl.hbase.HbaseContext;
import xdata.etl.hbase.column.HbaseColumnInfo;
import xdata.etl.hbase.column.HbaseColumnInfoIndex;
import xdata.etl.hbase.entity.HbaseEntity;

@Component
public class HbaseTableHelper {
	@Resource
	private HbaseContext hbaseCxt;

	public void createTable() throws IOException {
		HBaseAdmin admin = new HBaseAdmin(hbaseCxt.getCfg());
		List<String> names = hbaseCxt.getTableNameManager().getTableNames();
		for (String name : names) {
			if (admin.tableExists(name)) {
				continue;
			} else {
				HTableDescriptor tableDesc = new HTableDescriptor(name);
				tableDesc.addFamily(new HColumnDescriptor("d"));
				admin.createTable(tableDesc);
			}
		}
		admin.close();
	}

	public void createTable(Class<? extends HbaseEntity> clazz)
			throws IOException {
		HBaseAdmin admin = new HBaseAdmin(hbaseCxt.getCfg());
		String name = hbaseCxt.getTableNameManager().getTableName(clazz);
		if (admin.tableExists(name)) {
		} else {
			HTableDescriptor tableDesc = new HTableDescriptor(name);
			tableDesc.addFamily(new HColumnDescriptor("d"));
			admin.createTable(tableDesc);
		}
		admin.close();
	}

	public void dropHiveTable() {
		List<String> names = hbaseCxt.getTableNameManager().getTableNames();
		for (String name : names) {
			System.out.println("drop table " + name + ";");
		}
	}

	public void createHiveTable(Class<? extends HbaseEntity> clazz) {
		System.out.println(getCreateHiveTableSql(hbaseCxt.getTableNameManager()
				.getTableName(clazz)));
	}

	public void createHiveTable() {
		List<String> names = hbaseCxt.getTableNameManager().getTableNames();
		for (String name : names) {
			System.out.println(getCreateHiveTableSql(name));
		}
	}

	public String getCreateHiveTableSql(String tablename) {
		HbaseColumnInfoIndex columnInfoIndex = hbaseCxt
				.getColumnInfoManager()
				.getColumnInfoIndex(
						hbaseCxt.getTableNameManager().getTableClass(tablename));

		List<HbaseColumnInfo> columnInfos = columnInfoIndex.getColumnInfos();

		StringBuffer sb = new StringBuffer();
		sb.append("CREATE EXTERNAL TABLE " + tablename + "(");
		sb.append("\r\n");
		sb.append(getColumnSql(columnInfos));
		sb.append("\r\n");
		sb.append(")STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES (\"hbase.columns.mapping\" = ");
		sb.append("\r\n");
		sb.append("\"" + getColumnMappingSql(columnInfos) + "\"");
		sb.append("\r\n");
		sb.append(")TBLPROPERTIES(\"hbase.table.name\" = \"" + tablename
				+ "\");");
		return sb.toString();
	}

	public static String getColumnMappingSql(List<HbaseColumnInfo> meta) {
		StringBuffer sb = new StringBuffer(":key");

		for (HbaseColumnInfo hbaseColumnInfo : meta) {
			String name = hbaseColumnInfo.getColumnName();
			Class<?> clazz = hbaseColumnInfo.getField().getType();
			boolean isString = false;
			if (clazz.equals(String.class)) {
				isString = true;
			}
			sb.append(",");
			sb.append("d:" + name + (isString ? "" : "#b"));
		}

		return sb.toString();
	}

	public static String getColumnSql(List<HbaseColumnInfo> meta) {
		StringBuffer sb = new StringBuffer("key string");

		for (HbaseColumnInfo hbaseColumnInfo : meta) {
			String name = hbaseColumnInfo.getColumnName();
			Class<?> clazz = hbaseColumnInfo.getField().getType();
			String type = "string";
			if (clazz.equals(String.class)) {

			} else if (clazz.equals(Integer.class)) {
				type = "int";
			} else if (clazz.equals(Long.class)) {
				type = "bigint";
			} else if (clazz.equals(Date.class)) {
				type = "bigint";
			}
			sb.append(",");
			sb.append(name).append(" ").append(type);
		}

		return sb.toString();
	}

	public void dropAllTables() throws IOException {
		HBaseAdmin admin = new HBaseAdmin(hbaseCxt.getCfg());
		HTableDescriptor[] tables = admin.listTables();
		for (HTableDescriptor table : tables) {
			admin.disableTable(table.getName());
			admin.deleteTable(table.getName());
		}
		admin.close();
	}

	public void dropTable(Class<? extends HbaseEntity> clazz)
			throws IOException {
		HBaseAdmin admin = new HBaseAdmin(hbaseCxt.getCfg());
		String tableName = hbaseCxt.getTableNameManager().getTableName(clazz);
		if (admin.tableExists(tableName)) {
			admin.disableTable(tableName);
			admin.deleteTable(tableName);
		}
		admin.close();
	}

	public void clearTable(Class<? extends HbaseEntity> clazz)
			throws IOException {
		dropTable(clazz);
		createTable(clazz);
	}

	public static void main(String[] args) throws IOException {
		final AbstractXmlApplicationContext factory = RunableCLI.LOCAL_PRODUTION
				.getSpringCxt();

		HbaseTableHelper v = factory.getBean(HbaseTableHelper.class);
		v.createTable();
		factory.close();
	}

	public HbaseContext getHbaseCxt() {
		return hbaseCxt;
	}

	public void setHbaseCxt(HbaseContext hbaseCxt) {
		this.hbaseCxt = hbaseCxt;
	}

}
