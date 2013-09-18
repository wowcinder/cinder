package xdata.etl.kafka.apachelog;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xdata.etl.entity.apachelog.ApacheLog;
import xdata.etl.hbase.HbaseContext;
import xdata.etl.hbase.exception.RowGenerateException;
import xdata.etl.kafka.KafkaContext;
import xdata.etl.kafka.exception.KafkaTransformException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext-common.xml" })
public class ApacheLogTransfomerTest {

	@Resource
	public KafkaContext kafkaCxt;
	@Resource
	public HbaseContext hbaseCxt;

	public ApacheLogTransfomerTest() {
	}

	@Test
	public void test() throws IOException, RowGenerateException, KafkaTransformException {
		String raw = "10.1.100.116	2013-05-22 14:21:47	POST	/cms_apk_temp/getDesktop	?url=cms_apk_temp/getDesktop	200	101	http://platform.voole.com/cms_desktop_section?act=cms_desktop_section&menuid=309	Mozilla/5.0 (Windows NT 5.1; rv:22.0) Gecko/20100101 Firefox/22.0	platform.voole.com	66080";
		// Mozilla/5.0 (Windows NT 5.1; rv:22.0) Gecko/20100101 Firefox/22.0
		// platform.voole.com 66080
		ApacheLog log = (ApacheLog) kafkaCxt.transform("apache", raw);
		assertEquals("10.1.100.116", log.getUserIp());
		assertEquals("POST", log.getRequestMethod());
		assertEquals("/cms_apk_temp/getDesktop", log.getRequestUri());
		assertEquals("?url=cms_apk_temp/getDesktop", log.getUrlQueryString());
		assertEquals(200, log.getHttpCode().intValue());
		assertEquals(101, log.getResponseLen().intValue());
		assertEquals(
				"http://platform.voole.com/cms_desktop_section?act=cms_desktop_section&menuid=309",
				log.getReferer());
		assertEquals(
				"Mozilla/5.0 (Windows NT 5.1; rv:22.0) Gecko/20100101 Firefox/22.0",
				log.getUserAgent());

		assertEquals("platform.voole.com", log.getRequestHost());
		assertEquals(66080L, log.getUsedTime().longValue());

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		assertEquals("2013-05-22 14:21:47", df.format(log.getRequestTime()));

		hbaseCxt.save(log);

	}
}
