/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import xdata.etl.logmodel.transformer.LogModelTransformer;

/**
 * @author XuehuiHe
 * @date 2013年9月25日
 */
public class Test {
	public static void main(String[] args) {

		String json2 = "{\"reqno\":\"355375_1368517023431\",\"status\":\"-13\",\"resultdesc\":\"用户超出有效期\",\"play_url\":\"http://172.16.10.139:80/trends_index.jsp?key=355375_e8d3898e7ad7a22f9b96b484d1532e5e\",\"delaydeduct\":\"0\",\"delaytime\":\"180\",\"pid\":\"340009\",\"playtime\":{\"time\":\"0\",\"stime\":\"0\",\"etime\":\"300\"},\"adinfo\":{\"txtlist\":[{\"txtid\":[],\"txt\":\"优朋广告 优朋广告 优朋广告\",\"inserttime\":\"-1\",\"length\":\"20\"}],\"piclist\":[{\"pic\":\"http://imgadmin.voole.com/ishow/201108021635261001z211f\",\"length\":\"15\"}],\"videolist\":[{\"video\":\"31ed101dc6ecb5f1f9356e66d17794bf\",\"txt\":\"现在拿起遥控器按777有惊喜哦\",\"inserttime\":\"0\",\"length\":\"15\"}]}}";
		String json = "1372743176593#{\"serverip\":\"125.125.125.125\",\"startTime\":\"111\",\"endTime\":\"222\",\"request\":\"action=playauth2&oemid=153&uid=100405038&hid=bc83a7ec31be0000000000000000000000000000&mid=24534761&sid=1&fid=34a1a6a693d3b97f2987274890deb5bd&pid=101005&playtype=0&ext=&ip=110.98.184.248&format=0\",\"response\":"
				+ json2 + "}";

		final ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext(
				"spring-cinder-xa.xml");
		JsonLogModelService service = factory
				.getBean(JsonLogModelService.class);
		JsonLogModelVersion jsonLogModelVersion = service
				.getJsonLogModelVersion("3a_play_auth2", "0.0");
		System.out.println(jsonLogModelVersion);
		LogModelTransformer<JsonLogModelVersion> transformer = LogModelTransformer
				.createTransformer(jsonLogModelVersion);
		Map<String, List<HbaseRecord<String>>> recordmap = transformer
				.transform(json);
		System.out.println(recordmap);
		factory.close();
	}
}
