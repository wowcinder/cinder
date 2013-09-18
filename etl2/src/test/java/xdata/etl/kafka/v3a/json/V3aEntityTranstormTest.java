package xdata.etl.kafka.v3a.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xdata.etl.entity.v3a.V3aOrder;
import xdata.etl.entity.v3a.V3aPlayAuth2;
import xdata.etl.entity.v3a.request.V3aPlayAuth2Request;
import xdata.etl.entity.v3a.response.V3aPlayAuth2Response;
import xdata.etl.entity.v3a.srv.V3aPlayAuth2PicAd;
import xdata.etl.entity.v3a.srv.V3aPlayAuth2TxtAd;
import xdata.etl.entity.v3a.srv.V3aPlayAuth2VideoAd;
import xdata.etl.hbase.HbaseContext;
import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.hbase.exception.RowGenerateException;
import xdata.etl.kafka.KafkaContext;
import xdata.etl.kafka.exception.KafkaTransformException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext-common.xml" })
public class V3aEntityTranstormTest {
	@Resource
	public KafkaContext kafkaCxt;
	@Resource
	public HbaseContext hbaseCxt;

	@Test
	public void test() throws IOException, RowGenerateException,
			KafkaTransformException {
		String json2 = "{\"reqno\":\"355375_1368517023431\",\"status\":\"-13\",\"resultdesc\":\"用户超出有效期\",\"play_url\":\"http://172.16.10.139:80/trends_index.jsp?key=355375_e8d3898e7ad7a22f9b96b484d1532e5e\",\"delaydeduct\":\"0\",\"delaytime\":\"180\",\"pid\":\"340009\",\"playtime\":{\"time\":\"0\",\"stime\":\"0\",\"etime\":\"300\"},\"adinfo\":{\"txtlist\":[{\"txtid\":[],\"txt\":\"优朋广告 优朋广告 优朋广告\",\"inserttime\":\"-1\",\"length\":\"20\"}],\"piclist\":[{\"pic\":\"http://imgadmin.voole.com/ishow/201108021635261001z211f\",\"length\":\"15\"}],\"videolist\":[{\"video\":\"31ed101dc6ecb5f1f9356e66d17794bf\",\"txt\":\"现在拿起遥控器按777有惊喜哦\",\"inserttime\":\"0\",\"length\":\"15\"}]}}";
		String json = "{\"serverip\":\"125.125.125.125\",\"startTime\":\"111\",\"endTime\":\"222\",\"request\":\"action=playauth2&oemid=153&uid=100405038&hid=bc83a7ec31be0000000000000000000000000000&mid=24534761&sid=1&fid=34a1a6a693d3b97f2987274890deb5bd&pid=101005&playtype=0&ext=&ip=110.98.184.248&format=0\",\"response\":"
				+ json2 + "}";
		V3aPlayAuth2 m = (V3aPlayAuth2) kafkaCxt
				.transform("3a_play_auth2", json).get().iterator().next();

		assertEquals("125.125.125.125", m.getServerIp());
		assertEquals(111L, m.getRequestTime().longValue());
		assertEquals(222L, m.getResponseTime().longValue());

		V3aPlayAuth2Request request = m.getV3aPlayAuth2Request();
		assertNotNull(request);

		assertEquals(request.getFid(), "34a1a6a693d3b97f2987274890deb5bd");
		assertEquals(request.getHid(),
				"bc83a7ec31be0000000000000000000000000000");
		assertEquals(request.getMid().intValue(), 24534761);
		assertEquals(request.getOemid().intValue(), 153);
		assertEquals(request.getPid().intValue(), 101005);
		assertEquals(request.getPlaytype().intValue(), 0);
		assertEquals(request.getSid().intValue(), 1);
		assertEquals(request.getUid().longValue(), 100405038L);

		V3aPlayAuth2Response v = m.getV3aPlayAuth2Response();

		assertEquals(v.getDelaydeduct().intValue(), 0);
		assertEquals(v.getDelaytime().intValue(), 180);
		assertEquals(v.getPid(), "340009");
		assertEquals(v.getEtime().longValue(), 300L);
		assertEquals(v.getStime().longValue(), 0L);
		assertEquals(v.getTime().longValue(), 0L);
		assertEquals(
				v.getPlayUrl(),
				"http://172.16.10.139:80/trends_index.jsp?key=355375_e8d3898e7ad7a22f9b96b484d1532e5e");
		assertEquals(v.getStatus(), "-13");

		assertEquals(m.getV3aPlayAuth2PicAd().size(), 1);
		assertEquals(m.getV3aPlayAuth2TxtAd().size(), 1);
		assertEquals(m.getV3aPlayAuth2VideoAd().size(), 1);
		V3aPlayAuth2TxtAd txtAd = m.getV3aPlayAuth2TxtAd().get(0);
		assertNull(txtAd.getTxtid());
		assertEquals(txtAd.getTxt(), "优朋广告 优朋广告 优朋广告");
		assertEquals(txtAd.getInserttime().intValue(), -1);

		V3aPlayAuth2PicAd picAd = m.getV3aPlayAuth2PicAd().get(0);
		assertEquals(picAd.getPic(),
				"http://imgadmin.voole.com/ishow/201108021635261001z211f");
		assertEquals(picAd.getLength().intValue(), 15);

		V3aPlayAuth2VideoAd videoAd = m.getV3aPlayAuth2VideoAd().get(0);
		assertEquals(videoAd.getLength().longValue(), 15L);
		assertEquals(videoAd.getInserttime().longValue(), 0L);
		assertEquals(videoAd.getTxt(), "现在拿起遥控器按777有惊喜哦");
		assertEquals(videoAd.getVideo(), "31ed101dc6ecb5f1f9356e66d17794bf");

		assertEquals(m.getV3aPlayAuth2PicAd().getMainPart(), m);
		assertEquals(m.getV3aPlayAuth2PicAd().get(0).getMainPart(), m);
		assertEquals(m.getV3aPlayAuth2PicAd().get(0).getIndex(), 0);

	}

	@Test
	public void test2() throws KafkaTransformException {
		String json = "{\"serverip\":\"127.0.0.1\",\"startTime\":\"1369811351335\",\"endTime\":\"1369811351585\",\"request\":\"actiontype=4&uid=364410&sequenceno=10000101_20120410222417780&starttime=975513600000&spid=10002001&oemid=135&callbackurl=&pagecode=UTF-8&pid=330109&format=1&type=1&datetime=1334067857780&feetype=200&version=v1.0&randomNumber=0.09993905108422041\",\"response\":{\"respsequenceno\":\"10000101_20130529150911351\",\"status\":\"004\",\"resultdesc\":\"账户余额不足\",\"uid\":\"364410\"}}";
		V3aOrder t = (V3aOrder) kafkaCxt.transform("3a_subscribe", json).get()
				.iterator().next();
		assertNotNull(t);
	}

	@Test
	public void test3() throws KafkaTransformException, IOException {
		URL url = this.getClass().getClassLoader()
				.getResource("testEntity.txt");
		FileReader fileReader = new FileReader(url.getFile());
		BufferedReader read = new BufferedReader(fileReader);
		String line = "";
		while ((line = read.readLine()) != null) {
			String[] strs = line.split("\\t");
			test(strs[0], strs[1]);
		}
		read.close();
	}

	private int i = 1;

	public void test(String topic, String raw) {
		HbaseEntity t = null;
		try {
			t = (HbaseEntity) kafkaCxt.transform(topic, raw).get().iterator()
					.next();
			assertNotNull(t);
		} catch (KafkaTransformException e) {
			System.out.println("-------------" + i + "-----------------");
			e.printStackTrace();
		}
		i++;
	}
}
