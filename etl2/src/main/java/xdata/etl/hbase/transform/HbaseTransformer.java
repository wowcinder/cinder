package xdata.etl.hbase.transform;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import xdata.etl.hbase.HbaseContext;
import xdata.etl.hbase.column.HbaseColumnInfo;
import xdata.etl.hbase.column.HbaseColumnInfoIndex;
import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.hbase.exception.RowGenerateException;
import xdata.etl.util.ClassNameUtil;

public class HbaseTransformer {
	private HbaseContext hbaseCxt;

	public HbaseTransformer(HbaseContext hbaseCxt) {
		this.hbaseCxt = hbaseCxt;
	}

	public Put transformToPut(HbaseEntity t) throws RowGenerateException {
		Put put = new Put(Bytes.toBytes(hbaseCxt.getRow(t)));
		HbaseColumnInfoIndex columnInfoIndex = hbaseCxt.getColumnInfoManager()
				.getColumnInfoIndex(t.getClass());
		fillPut(put, t, columnInfoIndex.getColumnInfos());
		if (put.size() == 0) {
			return null;
		}
		return put;
	}

	private void fillPut(Put put, HbaseEntity t,
			List<HbaseColumnInfo> columnInfos) {
		for (HbaseColumnInfo hbaseColumnInfo : columnInfos) {
			byte[] v = getFieldValue(t, hbaseColumnInfo);
			if (v != null) {
				put.add(Bytes.toBytes("d"),
						Bytes.toBytes(hbaseColumnInfo.getColumnName()), v);
			}

		}
	}

	public List<Put> transformToPuts(List<HbaseEntity> entitys)
			throws RowGenerateException {
		return transformToPuts(entitys, false);
	}

	public List<Put> transformToPuts(List<HbaseEntity> entitys,
			boolean isWriteWAL) throws RowGenerateException {
		List<Put> list = new ArrayList<Put>();
		if (entitys.size() == 0) {
			return list;
		}
		HbaseColumnInfoIndex columnInfoIndex = hbaseCxt.getColumnInfoManager()
				.getColumnInfoIndex(entitys.get(0).getClass());
		for (HbaseEntity t : entitys) {
			Put put = new Put(Bytes.toBytes(hbaseCxt.getRow(t)));
			fillPut(put, t, columnInfoIndex.getColumnInfos());
			if (put.size() > 0) {
				put.setWriteToWAL(isWriteWAL);
				list.add(put);
			}
		}
		return list;
	}

	public HbaseTransformResult transform(HbaseEntity t)
			throws RowGenerateException {
		return transform(t, false);
	}

	public HbaseTransformResult transform(HbaseEntity t, boolean isWriteWAL)
			throws RowGenerateException {
		HbaseTransformResult result = new HbaseTransformResult(t.getTableName());
		Put put = transformToPut(t);
		if (put == null) {
			return null;
		}
		put.setWriteToWAL(isWriteWAL);
		result.setMainPartPut(put);
		List<Field> fields = hbaseCxt.getAttachmentManager()
				.getMainPartClassToAttachmentFields().get(t.getClass());
		if (fields != null && fields.size() > 0) {
			for (Field field : fields) {
				List<HbaseEntity> attachments = getMainPartAttachments(t, field);
				if (attachments != null) {
					List<Put> puts = transformToPuts(attachments);
					String tableName = hbaseCxt.getTableNameManager()
							.getTableName(
									hbaseCxt.getAttachmentManager()
											.getFieldToAttachmentClass()
											.get(field));
					result.addAttachmentPut(tableName, puts);
				}
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	private static List<HbaseEntity> getMainPartAttachments(Object o,
			Field field) {
		Method method = getMethod(o, field);
		if (method == null) {
			return null;
		}
		List<HbaseEntity> list = null;
		try {
			list = (List<HbaseEntity>) method.invoke(o);
		} catch (SecurityException e) {
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

	private static byte[] getFieldValue(HbaseEntity t,
			HbaseColumnInfo hbaseColumnInfo) {
		Field field = hbaseColumnInfo.getField();
		Object o = getRealObject(t, hbaseColumnInfo);
		if (o == null) {
			return null;
		}
		Method method = getMethod(o, field);
		if (method == null) {
			return null;
		}
		Class<?> clazz = field.getType();
		byte[] bytes = null;
		try {
			bytes = getBytes(clazz, method.invoke(o));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	private static Method getMethod(Object t, Field field) {
		String attriName = field.getName();
		String methodName = ClassNameUtil.getGetMethodName(attriName);
		Method method = null;
		try {
			method = t.getClass().getMethod(methodName);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return method;
	}

	private static Object getRealObject(Object t,
			HbaseColumnInfo hbaseColumnInfo) {
		if (!hbaseColumnInfo.isEmbeddable()) {
			return t;
		}
		Field field = hbaseColumnInfo.getEmbeddFiled();
		Method method = getMethod(t, field);
		if (method == null) {
			return null;
		}
		Object o = null;
		try {
			o = method.invoke(t);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return o;
	}

	private static byte[] getBytes(Class<?> clazz, Object obj) {
		if (obj == null) {
			return null;
		}
		if (clazz.equals(boolean.class) || clazz.equals(Boolean.class)) {
			return Bytes.toBytes((Boolean) obj);
		} else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
			return Bytes.toBytes((Long) obj);
		} else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
			return Bytes.toBytes((Short) obj);
		} else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
			return Bytes.toBytes((Double) obj);
		} else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
			return Bytes.toBytes((Float) obj);
		} else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
			return Bytes.toBytes((Integer) obj);
		} else if (clazz.equals(String.class)) {
			return Bytes.toBytes((String) obj);
		} else if (clazz.equals(BigDecimal.class)) {
			return Bytes.toBytes((BigDecimal) obj);
		} else if (clazz.equals(ByteBuffer.class)) {
			return Bytes.toBytes((ByteBuffer) obj);
		} else if (clazz.equals(Date.class)) {
			return Bytes.toBytes(((Date) obj).getTime());
		} else if (clazz.equals(BigInteger.class)) {
			return ((BigInteger) obj).toByteArray();
		}
		return null;
	}

	@SuppressWarnings("unused")
	private static Object getValue(Class<?> clazz, byte[] b) {
		if (b == null) {
			return null;
		}
		if (clazz.equals(boolean.class) || clazz.equals(Boolean.class)) {
			return Bytes.toBoolean(b);
		} else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
			return Bytes.toLong(b);
		} else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
			return Bytes.toShort(b);
		} else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
			return Bytes.toDouble(b);
		} else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
			return Bytes.toFloat(b);
		} else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
			return Bytes.toInt(b);
		} else if (clazz.equals(String.class)) {
			return Bytes.toString(b);
		} else if (clazz.equals(BigDecimal.class)) {
			return Bytes.toBigDecimal(b);
		} else if (clazz.equals(Date.class)) {
			Date d = new Date();
			d.setTime(Bytes.toLong(b));
			return d;
		} else if (clazz.equals(BigInteger.class)) {
			return new BigInteger(b);
		}
		return null;
	}

	public static class HbaseTransformResultCollection {
		private Map<String, List<Put>> putsMap;

		public HbaseTransformResultCollection() {
			putsMap = new HashMap<String, List<Put>>();
		}

		public Map<String, List<Put>> getPutsMap() {
			return putsMap;
		}

		public void setPutsMap(Map<String, List<Put>> putsMap) {
			this.putsMap = putsMap;
		}

		public void merge(HbaseTransformResult result) {
			if (result.getMainPartPut() != null) {
				merge(result.getTableName(), result.getMainPartPut());
			}
			merge(result.getAttachmentPutsMap());
		}

		public void merge(Map<String, List<Put>> map) {
			if (map == null) {
				return;
			}
			for (String tableName : map.keySet()) {
				List<Put> puts = map.get(tableName);
				merger(tableName, puts);
			}
		}

		public void merge(String tableName, Put put) {
			if (put == null) {
				return;
			}
			if (!putsMap.containsKey(tableName)) {
				putsMap.put(tableName, new ArrayList<Put>());
			}
			putsMap.get(tableName).add(put);
		}

		public void merger(String tableName, List<Put> puts) {
			if (puts == null || puts.size() == 0) {
				return;
			}
			if (!putsMap.containsKey(tableName)) {
				putsMap.put(tableName, new ArrayList<Put>());
			}
			putsMap.get(tableName).addAll(puts);
		}

		public void merger(HbaseTransformResultCollection c) {
			merge(c.getPutsMap());
		}
	}

	public static class HbaseTransformResult {
		private String tableName;
		private Put mainPartPut;
		private Map<String, List<Put>> attachmentPutsMap;

		public HbaseTransformResult() {
			attachmentPutsMap = new HashMap<String, List<Put>>();
		}

		public HbaseTransformResult(String tableName) {
			this();
			this.tableName = tableName;
		}

		public Put getMainPartPut() {
			return mainPartPut;
		}

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public Map<String, List<Put>> getAttachmentPutsMap() {
			return attachmentPutsMap;
		}

		public void setMainPartPut(Put mainPartPut) {
			this.mainPartPut = mainPartPut;
		}

		public void setAttachmentPutsMap(
				Map<String, List<Put>> attachmentPutsMap) {
			this.attachmentPutsMap = attachmentPutsMap;
		}

		public void addAttachmentPut(String tableName, Put put) {
			if (put == null || put.size() == 0) {
				return;
			}
			if (!attachmentPutsMap.containsKey(tableName)) {
				attachmentPutsMap.put(tableName, new ArrayList<Put>());
			}
			attachmentPutsMap.get(tableName).add(put);
		}

		public void addAttachmentPut(String tableName, List<Put> puts) {
			if (puts == null || puts.size() == 0) {
				return;
			}
			if (!attachmentPutsMap.containsKey(tableName)) {
				attachmentPutsMap.put(tableName, new ArrayList<Put>());
			}
			attachmentPutsMap.get(tableName).addAll(puts);
		}

	}
}
