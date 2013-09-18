package xdata.etl.kafka.transform.json.v3a.shadow;

import xdata.etl.entity.v3a.V3aOrderPriceQuery;
import xdata.etl.entity.v3a.request.V3aOrderPriceQueryRequest;
import xdata.etl.hbase.entity.v3a.V3aEntity;

import com.google.gson.annotations.SerializedName;

public class V3aOrderPriceQueryShadow extends V3aEntity {
	@SerializedName("request")
	private V3aOrderPriceQueryRequest v3aOrderPriceQueryRequest = new V3aOrderPriceQueryRequest();
	@SerializedName("response")
	private V3aOrderPriceQueryResponseShadow v3aOrderPriceQueryResponse = new V3aOrderPriceQueryResponseShadow();

	public V3aOrderPriceQuery getEntity() {
		V3aOrderPriceQuery v = new V3aOrderPriceQuery();
		v.setRequestTime(getRequestTime());
		v.setResponseTime(getResponseTime());
		v.setServerIp(getServerIp());

		v.setV3aOrderPriceQueryRequest(getV3aOrderPriceQueryRequest());

		V3aOrderPriceQueryResponseShadow response = getV3aOrderPriceQueryResponse();
		if (response != null) {
			if (response.getResponse() != null) {
				v.setV3aOrderPriceQueryResponse(response.getResponse());
			}
			v.getV3aOrderPriceQueryFilmPrice().addAll(response.getFilmPrices());
		}
		return v;
	}

	public V3aOrderPriceQueryRequest getV3aOrderPriceQueryRequest() {
		return v3aOrderPriceQueryRequest;
	}

	public V3aOrderPriceQueryResponseShadow getV3aOrderPriceQueryResponse() {
		return v3aOrderPriceQueryResponse;
	}

	public void setV3aOrderPriceQueryRequest(
			V3aOrderPriceQueryRequest v3aOrderPriceQueryRequest) {
		this.v3aOrderPriceQueryRequest = v3aOrderPriceQueryRequest;
	}

	public void setV3aOrderPriceQueryResponse(
			V3aOrderPriceQueryResponseShadow v3aOrderPriceQueryResponse) {
		this.v3aOrderPriceQueryResponse = v3aOrderPriceQueryResponse;
	}

}
