package xdata.etl.kafka.transform.json.v3a.shadow;

import xdata.etl.entity.v3a.V3aPlayAuth2;
import xdata.etl.entity.v3a.request.V3aPlayAuth2Request;
import xdata.etl.hbase.entity.v3a.V3aEntity;
import xdata.etl.kafka.transform.json.v3a.shadow.V3aPlayAuth2ResponseShadow.AdInfo;

import com.google.gson.annotations.SerializedName;

public class V3aPlayAuth2Shadow extends V3aEntity {
	@SerializedName("request")
	private V3aPlayAuth2Request v3aPlayAuth2Request = new V3aPlayAuth2Request();
	@SerializedName("response")
	private V3aPlayAuth2ResponseShadow v3aPlayAuth2Response = new V3aPlayAuth2ResponseShadow();

	public V3aPlayAuth2 getEntity() {
		V3aPlayAuth2 v = new V3aPlayAuth2();
		v.setRequestTime(getRequestTime());
		v.setResponseTime(getResponseTime());
		v.setServerIp(getServerIp());

		v.setV3aPlayAuth2Request(getV3aPlayAuth2Request());
		V3aPlayAuth2ResponseShadow response = getV3aPlayAuth2Response();
		if (response != null) {
			if (response.getResponse() != null) {
				v.setV3aPlayAuth2Response(response.getResponse());
			}
			AdInfo adInfo = response.getAdinfo();
			if (adInfo != null) {
				v.getV3aPlayAuth2PicAd().addAll(adInfo.getPiclist());
				v.getV3aPlayAuth2TxtAd().addAll(adInfo.getTxtlist());
				v.getV3aPlayAuth2VideoAd().addAll(adInfo.getVideolist());
			}
		}

		return v;
	}

	public V3aPlayAuth2Request getV3aPlayAuth2Request() {
		return v3aPlayAuth2Request;
	}

	public V3aPlayAuth2ResponseShadow getV3aPlayAuth2Response() {
		return v3aPlayAuth2Response;
	}

	public void setV3aPlayAuth2Request(V3aPlayAuth2Request v3aPlayAuth2Request) {
		this.v3aPlayAuth2Request = v3aPlayAuth2Request;
	}

	public void setV3aPlayAuth2Response(
			V3aPlayAuth2ResponseShadow v3aPlayAuth2Response) {
		this.v3aPlayAuth2Response = v3aPlayAuth2Response;
	}

}
