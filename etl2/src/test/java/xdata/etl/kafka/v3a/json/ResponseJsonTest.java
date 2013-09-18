package xdata.etl.kafka.v3a.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import xdata.etl.entity.v3a.response.V3a3rdPaymentResponse;
import xdata.etl.entity.v3a.response.V3aDelayDeductResponse;
import xdata.etl.entity.v3a.response.V3aOnlineStatusResponse;
import xdata.etl.entity.v3a.response.V3aOrderCancelResponse;
import xdata.etl.entity.v3a.response.V3aOrderResponse;
import xdata.etl.entity.v3a.response.V3aRechargeResponse;
import xdata.etl.entity.v3a.response.V3aRepreatPlayQueryResponse;
import xdata.etl.entity.v3a.response.V3aUserAuthResponse;
import xdata.etl.entity.v3a.srv.V3aOrderPriceQueryFilmPrice;
import xdata.etl.entity.v3a.srv.V3aPlayAuth2PicAd;
import xdata.etl.entity.v3a.srv.V3aPlayAuth2TxtAd;
import xdata.etl.entity.v3a.srv.V3aPlayAuth2VideoAd;
import xdata.etl.kafka.transform.json.V3aGson;
import xdata.etl.kafka.transform.json.v3a.shadow.V3aOrderPriceQueryResponseShadow;
import xdata.etl.kafka.transform.json.v3a.shadow.V3aPlayAuth2ResponseShadow;

public class ResponseJsonTest {
	private V3aGson gson;

	public ResponseJsonTest() {
		gson = xdata.etl.kafka.transform.json.V3aGson.getInstance();
	}

	@Test
	public void testV3aUserAuthResponse() {
		String json = "{\"reqno\":\"100110259_1368603261477\",\"status\":\"0\",\"resultdesc\":\"操作成功\",\"userinfo\":{\"uid\":\"100110259\",\"oemid\":\"348\",\"hid\":\"0203200000000000000000000000000000000000\",\"epgportal\":\"http://stboxpay.voole.com/\",\"epgportal2\":\"http://stboxpay.voole.com/?uid=100110259&oemid=348&hid=0203200000000000000000000000000000000000&balance=0&version=v1.0\",\"balance\":\"0\",\"ispid\":\"10002001\"}}";
		V3aUserAuthResponse v = gson.fromJson(json, V3aUserAuthResponse.class);
		assertEquals(v.getStatus(), "0");
		assertEquals(v.getBalance().intValue(), 0);
		assertEquals(v.getEpgportal(), "http://stboxpay.voole.com/");
		assertEquals(
				v.getEpgportal2(),
				"http://stboxpay.voole.com/?uid=100110259&oemid=348&hid=0203200000000000000000000000000000000000&balance=0&version=v1.0");
		assertEquals(v.getHid(), "0203200000000000000000000000000000000000");
		assertEquals(v.getIspid().intValue(), 10002001);
		assertEquals(v.getOemid().intValue(), 348);
		assertEquals(v.getUid().longValue(), 100110259L);
	}

	@Test
	public void testV3aRepreatPlayQueryResponse() throws ParseException {
		String json = "{\"reqno\":\"259593_1368604100050\",\"status\":\"0\",\"resultdesc\":\"操作成功\",\"viewed\":\"1\",\"endtime\":\"2012-07-26 14:39:16\",\"order\":\"2\",\"pid\":\"-1\"}";
		V3aRepreatPlayQueryResponse v = gson.fromJson(json,
				V3aRepreatPlayQueryResponse.class);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date endTime = df.parse("2012-07-26 14:39:16");
		assertEquals(endTime, v.getEndtime());
		assertEquals(2, v.getOrder().intValue());
		assertEquals(-1, v.getPid().intValue());
		assertNull(v.getTjpid());
	}

	@Test
	public void testV3aOnlineStatusResponse() {
		String json = "{\"reqno\":\"100107_1368604586841\",\"status\":\"0\",\"resultdesc\":\"操作成功\"}";
		V3aOnlineStatusResponse v = gson.fromJson(json,
				V3aOnlineStatusResponse.class);
		assertEquals("0", v.getStatus());
	}

	@Test
	public void testV3aRechargeResponse() {
		String json = "{\"respsequenceno\":\"10000101_20130515155735068\",\"status\":\"502\",\"resultdesc\":\"您的充值卡密码有误，请确认！如需帮助，请拨打优朋普乐客服热线400-655-7899\",\"uid\":\"100107\",\"cardno\":\"无效卡号:3519755975350567\",\"money\":\"0\",\"balance\":\"0\"}";
		V3aRechargeResponse v = gson.fromJson(json, V3aRechargeResponse.class);
		assertEquals(0, v.getBalance().intValue());
		assertEquals(0, v.getMoney().intValue());
		assertEquals("502", v.getStatus());

	}

	@Test
	public void testV3aOrderResponse() {
		String json = "{\"respsequenceno\":\"10000101_20130515160027348\",\"status\":\"004\",\"resultdesc\":\"账户余额不足\",\"uid\":\"364410\"}";
		V3aOrderResponse v = gson.fromJson(json, V3aOrderResponse.class);
		assertEquals("004", v.getStatus());
	}

	@Test
	public void testV3aOrderCancelResponse() {
		String json = "{\"respsequenceno\":\"10000101_20130515161156500\",\"status\":\"014\",\"resultdesc\":\"当前状态为已取消\"}";
		V3aOrderCancelResponse v = gson.fromJson(json,
				V3aOrderCancelResponse.class);
		assertEquals(v.getStatus(), "014");
	}

	@Test
	public void testV3a3rdPaymentResponse() {
		String json = "{\"respsequenceno\":\"10000101_20130515162153433\",\"status\":\"255\",\"resultdesc\":\"接口加密验证错误\",\"cardno\":[],\"money\":\"0\",\"balance\":\"0\",\"uid\":\"314251\"}";
		V3a3rdPaymentResponse v = gson.fromJson(json,
				V3a3rdPaymentResponse.class);
		assertEquals(v.getBalance().intValue(), 0);
		assertEquals(v.getCardno(), null);
		assertEquals(v.getMoney().intValue(), 0);
		assertEquals(v.getStatus(), "255");
		assertEquals(v.getUid().longValue(), 314251L);
	}

	@Test
	public void testV3aOrderPriceQueryResponse() {
		String json = "{\"Count\":\"8\",\"Time\":\"2013-05-15 17:08:25\",\"FilmPrice\":[{\"Mid\":\"99676361\",\"Mtype\":\"1\",\"Price\":\"0\"},{\"Mid\":\"50014646\",\"Mtype\":\"1\",\"Price\":\"0\"},{\"Mid\":\"45874519\",\"Mtype\":\"1\",\"Price\":\"0\"},{\"Mid\":\"56922431\",\"Mtype\":\"1\",\"Price\":\"0\"},{\"Mid\":\"36819573\",\"Mtype\":\"1\",\"Price\":\"0\"},{\"Mid\":\"89178024\",\"Mtype\":\"1\",\"Price\":\"0\"},{\"Mid\":\"30834261\",\"Mtype\":\"1\",\"Price\":\"0\"},{\"Mid\":\"49417941\",\"Mtype\":\"1\",\"Price\":\"0\"}]}";
		V3aOrderPriceQueryResponseShadow v = gson.fromJson(json,
				V3aOrderPriceQueryResponseShadow.class);
		assertEquals(8, v.getCount().intValue());
		assertEquals(8, v.getFilmPrices().size());
		V3aOrderPriceQueryFilmPrice filmPrice = v.getFilmPrices().get(0);
		assertEquals(filmPrice.getMid().intValue(), 99676361);
		assertEquals(filmPrice.getMtype(), "1");
		assertEquals(filmPrice.getPrice().intValue(), 0);
	}

	@Test
	public void testV3aOrderPriceQueryResponseWhenFilmPriceOnlyOne() {
		String json = "{\"Count\":\"1\",\"Time\":\"2013-07-11 15:01:56\",\"FilmPrice\":{\"Mid\":\"49417941\",\"Mtype\":\"1\",\"Price\":\"0\"}}";
		V3aOrderPriceQueryResponseShadow v = gson.fromJson(json,
				V3aOrderPriceQueryResponseShadow.class);
		assertEquals(1, v.getCount().intValue());
		assertEquals(1, v.getFilmPrices().size());
		V3aOrderPriceQueryFilmPrice filmPrice = v.getFilmPrices().get(0);
		assertEquals(filmPrice.getMid().intValue(), 49417941);
		assertEquals(filmPrice.getMtype(), "1");
		assertEquals(filmPrice.getPrice().intValue(), 0);
	}

	@Test
	public void testV3aPlayAuth2Response() {
		String json = "{\"reqno\":\"355375_1368517023431\",\"status\":\"-13\",\"resultdesc\":\"用户超出有效期\",\"play_url\":\"http://172.16.10.139:80/trends_index.jsp?key=355375_e8d3898e7ad7a22f9b96b484d1532e5e\",\"delaydeduct\":\"0\",\"delaytime\":\"180\",\"pid\":\"340009\",\"playtime\":{\"time\":\"0\",\"stime\":\"0\",\"etime\":\"300\"},\"adinfo\":{\"txtlist\":[{\"txtid\":[],\"txt\":\"优朋广告 优朋广告 优朋广告\",\"inserttime\":\"-1\",\"length\":\"20\"}],\"piclist\":[{\"pic\":\"http://imgadmin.voole.com/ishow/201108021635261001z211f\",\"length\":\"15\"}],\"videolist\":[{\"video\":\"31ed101dc6ecb5f1f9356e66d17794bf\",\"txt\":\"现在拿起遥控器按777有惊喜哦\",\"inserttime\":\"0\",\"length\":\"15\"}]}}";
		V3aPlayAuth2ResponseShadow v = gson.fromJson(json,
				V3aPlayAuth2ResponseShadow.class);
		assertEquals(v.getDelaydeduct().intValue(), 0);
		assertEquals(v.getDelaytime().intValue(), 180);
		assertEquals(v.getPid(), "340009");
		assertEquals(v.getPlaytime().getEtime().longValue(), 300L);
		assertEquals(v.getPlaytime().getStime().longValue(), 0L);
		assertEquals(v.getPlaytime().getTime().longValue(), 0L);
		assertEquals(
				v.getPlayUrl(),
				"http://172.16.10.139:80/trends_index.jsp?key=355375_e8d3898e7ad7a22f9b96b484d1532e5e");
		assertEquals(v.getStatus(), "-13");

		assertEquals(v.getAdinfo().getTxtlist().size(), 1);
		assertEquals(v.getAdinfo().getPiclist().size(), 1);
		assertEquals(v.getAdinfo().getVideolist().size(), 1);
		V3aPlayAuth2TxtAd txtAd = v.getAdinfo().getTxtlist().get(0);
		assertNull(txtAd.getTxtid());
		assertEquals(txtAd.getTxt(), "优朋广告 优朋广告 优朋广告");
		assertEquals(txtAd.getInserttime().intValue(), -1);

		V3aPlayAuth2PicAd picAd = v.getAdinfo().getPiclist().get(0);
		assertEquals(picAd.getPic(),
				"http://imgadmin.voole.com/ishow/201108021635261001z211f");
		assertEquals(picAd.getLength().intValue(), 15);

		V3aPlayAuth2VideoAd videoAd = v.getAdinfo().getVideolist().get(0);
		assertEquals(videoAd.getLength().longValue(), 15L);
		assertEquals(videoAd.getInserttime().longValue(), 0L);
		assertEquals(videoAd.getTxt(), "现在拿起遥控器按777有惊喜哦");
		assertEquals(videoAd.getVideo(), "31ed101dc6ecb5f1f9356e66d17794bf");
	}

	@Test
	public void testV3aDelayDeductResponse() {
		String json = "{\"reqno\":\"355375_1368611299685\",\"status\":\"0\",\"resultdesc\":\"操作成功\",\"balance\":\"0\"}";
		V3aDelayDeductResponse v = gson.fromJson(json,
				V3aDelayDeductResponse.class);

		assertEquals(v.getBalance().intValue(), 0);
		assertEquals(v.getStatus(), "0");
	}
}
