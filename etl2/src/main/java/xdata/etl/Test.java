package xdata.etl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.javaapi.producer.ProducerData;
import kafka.producer.ProducerConfig;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.WritableByteArrayComparable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.NullOutputFormat;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xdata.etl.hbase.HbaseContext;
import xdata.etl.kafka.KafkaContext;
import xdata.etl.kafka.consumer.ConsumerBuilder;
import xdata.etl.kafka.consumer.IConsumer;
import xdata.etl.kafka.exception.KafkaTransformException;
import xdata.etl.kafka.transform.KafkaTransformer;
import xdata.etl.kafka.transform.result.KafkaTransformResult;

public class Test {

	private static IConsumer consumer;

	public static void main20(String[] args) throws IOException {
		final AbstractXmlApplicationContext factory = RunableCLI.LOCAL_PRODUTION
				.getSpringCxt();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if (consumer != null) {
					consumer.shutdown();
				}
				factory.close();
			}
		});
		factory.close();
		// final ConsumerFactory f = factory.getBean(ConsumerFactory.class);
		// consumer = f.createConsumer("cdn_cache_update");
		// consumer.run();
	}

	public static void main2(String[] args) throws KafkaTransformException {
		String json = "1372743176593#{\"uid\":\"100555\",\"appId\":\"22\",\"oemid\":\"649\",\"hid\":\"1234\",\"packagename\":\"com.voole.VideoItemListAndroid\",\"resultCode\":0,\"resultText\":\"汇报成功\",\"xmlList\":[{\"appName\":\"优朋客户端\",\"uid\":\"100555\",\"errorText\":\"\n    CDATA[ExceptionStack]\n   \",\"appId\":\"22\",\"oemid\":\"649\",\"hid\":\"1234\",\"appVersion\":\"1.0\",\"errorCode\":\"-2\",\"value\":\"downloadFailed\",\"type\":\"updateDownload\"}],\"ip\":\"127.0.0.1\"}";
		final ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext-common.xml");
		KafkaContext kafkaContext = factory.getBean(KafkaContext.class);

		KafkaTransformer kafkaTransformer = kafkaContext.getTransformerCache()
				.getTransformer("t_verupdate");
		KafkaTransformResult result = kafkaTransformer.transform(kafkaContext
				.getTopicManager().getClazz("t_verupdate"), json);
		System.out.println(result);
		factory.close();
	}

	public static void main4(String[] args) {
		Properties props = new Properties();
		props.put("zk.connect", "125.39.40.213:2181");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		ProducerConfig config = new ProducerConfig(props);
		Producer<String, String> producer = new Producer<String, String>(config);
		ProducerData<String, String> data = new ProducerData<String, String>(
				"3a_price_query2", "1372743176593#test-message");
		producer.send(data);
	}

	public static void main2233(String[] args) throws IOException {
		Calendar c = Calendar.getInstance();
		String startKey = ((Long.MAX_VALUE - c.getTimeInMillis()) / 1000) + "";
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) - 6);
		String endKey = ((Long.MAX_VALUE - c.getTimeInMillis()) / 1000) + "";
		final AbstractXmlApplicationContext factory = RunableCLI.LOCAL_PRODUTION
				.getSpringCxt();

		HbaseContext ctx = factory.getBean(HbaseContext.class);
		Scan scan = new Scan();
		scan.setCaching(500);
		scan.setStartRow(Bytes.toBytes(startKey));
		scan.setStopRow(Bytes.toBytes(endKey));
		Filter filter = new RowFilter(CompareOp.EQUAL,
				new WritableByteArrayComparable() {

					@Override
					public int compareTo(byte[] value, int offset, int length) {
						return 0;
					}
				});

		scan.setFilter(new RowFilter(CompareOp.EQUAL, new SimpleRowFilter()));
		// scan.setFilter(new SingleColumnValueFilter(Bytes.toBytes("d"), Bytes
		// .toBytes("price"), CompareOp.GREATER, Bytes.toBytes(0)));
		HTable currentTable = new HTable(ctx.getCfg(),
				"msg_v3a_order_price_query_film_price");
		ResultScanner rs = currentTable.getScanner(scan);

		long startTime = System.currentTimeMillis();
		int i = 0;
		for (Result result : rs) {
			System.out.println(Bytes.toString(result.getRow())
					+ "\t"
					+ Bytes.toInt(result.getValue(Bytes.toBytes("d"),
							Bytes.toBytes("price"))));
			i++;
			break;
		}
		System.out.println("total:" + i);
		System.out.println("using:" + (System.currentTimeMillis() - startTime));
		rs.close();
		currentTable.close();
		factory.close();
	}

	public static void main22(String[] args) {
		final AbstractXmlApplicationContext factory = RunableCLI.LOCAL_TEST
				.getSpringCxt();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if (consumer != null) {
					consumer.shutdown();
				}
				factory.close();
			}
		});
		final ConsumerBuilder builder = factory.getBean(ConsumerBuilder.class);
		consumer = builder.create();
		consumer.run();
	}

	public static void main111(String[] args) throws IOException,
			InterruptedException, ClassNotFoundException {
		Calendar c = Calendar.getInstance();
		String startKey = ((Long.MAX_VALUE - c.getTimeInMillis()) / 1000) + "";
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) - 6);
		String endKey = ((Long.MAX_VALUE - c.getTimeInMillis()) / 1000) + "";
		final AbstractXmlApplicationContext factory = RunableCLI.LOCAL_PRODUTION
				.getSpringCxt();
		HbaseContext ctx = factory.getBean(HbaseContext.class);

		Job job = new Job(ctx.getCfg(), "test-hbase-job");
		Scan scan = new Scan();
		scan.setCaching(500);
		scan.setStartRow(Bytes.toBytes(startKey));
		scan.setStopRow(Bytes.toBytes(endKey));

		TableMapReduceUtil.initTableMapperJob(
				"msg_v3a_order_price_query_film_price", scan, null, null, null,
				job);

		job.setOutputFormatClass(NullOutputFormat.class);
		boolean b = job.waitForCompletion(true);
		if (!b) {
			throw new IOException("error with job!");
		}
	}
	
	public static void main(String[] args) {
		String name = "test";
		say(name);
	}
	
	public static void say(String name){
		System.out.println(name);
	}

	public static class TestHbaseTableMapper extends TableMapper<Text, Text> {

	}

	public static class SimpleRowFilter extends WritableByteArrayComparable {
		private String str;

		public SimpleRowFilter() {
			super(Bytes.toBytes("7331a662"));
			this.str = "7331a662";
		}

		public byte[] getValue() {
			return Bytes.toBytes(str);
		}

		@Override
		public void readFields(DataInput in) throws IOException {
			String substr = in.readUTF();
			this.str = substr;
		}

		@Override
		public void write(DataOutput out) throws IOException {
			out.writeUTF(str);
		}

		@Override
		public int compareTo(byte[] value, int offset, int length) {
			String key = Bytes.toString(value, offset, length);
			String[] strs = key.split("_");
			if (strs[2].equals(str)) {
				return 0;
			}
			return 1;
		}

	}
}
