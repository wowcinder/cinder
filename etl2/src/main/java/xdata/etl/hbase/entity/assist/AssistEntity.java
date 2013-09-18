package xdata.etl.hbase.entity.assist;

import com.google.gson.annotations.SerializedName;

import xdata.etl.hbase.entity.HbaseEntity;

public class AssistEntity extends HbaseEntity {
	@SerializedName("ip")
	private String serverIp;

	public AssistEntity() {
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

}
