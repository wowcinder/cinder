package xdata.etl.kafka.transform.json.v3a.shadow;

import com.google.gson.annotations.SerializedName;

public class V3aUserAuthResponseShadow {
	public String status;
	public UserInfo userinfo;
	public static class UserInfo {
		@SerializedName("uid")
		public Long uid;
		@SerializedName("balance")
		public Integer balance;
		@SerializedName("hid")
		public String hid;
		@SerializedName("oemid")
		public Integer oemid;
		@SerializedName("ispid")
		public Integer ispid;
		@SerializedName("epgportal")
		public String epgportal;
		@SerializedName("epgportal2")
		public String epgportal2;
	}
}