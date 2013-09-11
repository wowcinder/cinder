package xdata.etl.entity.cdn;

import java.io.Serializable;

import xdata.etl.hbase.annotatins.HbaseExclude;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "snap_cdn_cache_info")
public class CdnCacheInfo extends HbaseEntity implements Serializable {
	private static final long serialVersionUID = 5960063118875615647L;
	@HbaseExclude
	private String ip;
	@HbaseExclude
	private String dayStr;
	@HbaseExclude
	private String fid;
	@HbaseExclude
	private String blockId;
	private Long accessTimes;
	private Long blockSize;

	public CdnCacheInfo() {
	}

	public String getIp() {
		return ip;
	}

	public String getDayStr() {
		return dayStr;
	}

	public String getFid() {
		return fid;
	}

	public String getBlockId() {
		return blockId;
	}

	public Long getAccessTimes() {
		return accessTimes;
	}

	public Long getBlockSize() {
		return blockSize;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setDayStr(String dayStr) {
		this.dayStr = dayStr;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public void setAccessTimes(Long accessTimes) {
		this.accessTimes = accessTimes;
	}

	public void setBlockSize(Long blockSize) {
		this.blockSize = blockSize;
	}

}
