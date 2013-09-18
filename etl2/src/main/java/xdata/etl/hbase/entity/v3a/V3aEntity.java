package xdata.etl.hbase.entity.v3a;

import com.google.gson.annotations.SerializedName;

import xdata.etl.hbase.entity.HbaseEntity;

public class V3aEntity extends HbaseEntity {
	@SerializedName("serverip")
	private String serverIp;
	@SerializedName("startTime")
	private Long requestTime;
	@SerializedName("endTime")
	private Long responseTime;
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public Long getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Long requestTime) {
		this.requestTime = requestTime;
	}
	public Long getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Long responseTime) {
		this.responseTime = responseTime;
	}

}
