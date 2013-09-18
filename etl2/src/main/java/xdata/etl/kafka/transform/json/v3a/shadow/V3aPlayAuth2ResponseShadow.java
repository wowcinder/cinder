package xdata.etl.kafka.transform.json.v3a.shadow;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.entity.v3a.response.V3aPlayAuth2Response;
import xdata.etl.entity.v3a.srv.V3aPlayAuth2PicAd;
import xdata.etl.entity.v3a.srv.V3aPlayAuth2TxtAd;
import xdata.etl.entity.v3a.srv.V3aPlayAuth2VideoAd;

import com.google.gson.annotations.SerializedName;

public class V3aPlayAuth2ResponseShadow {
	@SerializedName("status")
	private String status;
	@SerializedName("play_url")
	private String playUrl;
	@SerializedName("delaydeduct")
	private Integer delaydeduct;
	@SerializedName("delaytime")
	private Integer delaytime;
	@SerializedName("pid")
	private String pid;
	@SerializedName("playtime")
	private PlayTime playtime;
	@SerializedName("adinfo")
	private AdInfo adinfo;

	public V3aPlayAuth2ResponseShadow() {

	}

	public V3aPlayAuth2Response getResponse() {
		V3aPlayAuth2Response response = new V3aPlayAuth2Response();
		response.setDelaydeduct(getDelaydeduct());
		response.setDelaytime(getDelaytime());
		response.setPid(getPid());
		response.setPlayUrl(getPlayUrl());
		response.setStatus(getStatus());
		if (getPlaytime() != null) {
			response.setEtime(getPlaytime().getEtime());
			response.setTime(getPlaytime().getTime());
			response.setStime(getPlaytime().getStime());
		}

		return response;
	}

	public String getStatus() {
		return status;
	}

	public String getPlayUrl() {
		return playUrl;
	}

	public Integer getDelaydeduct() {
		return delaydeduct;
	}

	public Integer getDelaytime() {
		return delaytime;
	}

	public String getPid() {
		return pid;
	}

	public PlayTime getPlaytime() {
		return playtime;
	}

	public AdInfo getAdinfo() {
		return adinfo;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}

	public void setDelaydeduct(Integer delaydeduct) {
		this.delaydeduct = delaydeduct;
	}

	public void setDelaytime(Integer delaytime) {
		this.delaytime = delaytime;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setPlaytime(PlayTime playtime) {
		this.playtime = playtime;
	}

	public void setAdinfo(AdInfo adinfo) {
		this.adinfo = adinfo;
	}

	public static class AdInfo {
		private List<V3aPlayAuth2TxtAd> txtlist = new ArrayList<V3aPlayAuth2TxtAd>();
		private List<V3aPlayAuth2PicAd> piclist = new ArrayList<V3aPlayAuth2PicAd>();
		private List<V3aPlayAuth2VideoAd> videolist = new ArrayList<V3aPlayAuth2VideoAd>();

		public List<V3aPlayAuth2TxtAd> getTxtlist() {
			return txtlist;
		}

		public List<V3aPlayAuth2PicAd> getPiclist() {
			return piclist;
		}

		public List<V3aPlayAuth2VideoAd> getVideolist() {
			return videolist;
		}

		public void setTxtlist(List<V3aPlayAuth2TxtAd> txtlist) {
			this.txtlist = txtlist;
		}

		public void setPiclist(List<V3aPlayAuth2PicAd> piclist) {
			this.piclist = piclist;
		}

		public void setVideolist(List<V3aPlayAuth2VideoAd> videolist) {
			this.videolist = videolist;
		}
	}

	public static class PlayTime {
		private Long time;
		private Long stime;
		private Long etime;

		public Long getTime() {
			return time;
		}

		public Long getStime() {
			return stime;
		}

		public Long getEtime() {
			return etime;
		}

		public void setTime(Long time) {
			this.time = time;
		}

		public void setStime(Long stime) {
			this.stime = stime;
		}

		public void setEtime(Long etime) {
			this.etime = etime;
		}
	}
}
