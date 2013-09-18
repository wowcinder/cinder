package xdata.etl.hbase.row;

import xdata.etl.entity.cdn.CdnCacheInfo;
import xdata.etl.hbase.entity.HbaseEntity;

public class CdnCacheInfoRowGenerator implements HbaseRowGenerator {

	@Override
	public String generate(HbaseEntity entity) {
		CdnCacheInfo info = (CdnCacheInfo) entity;
		StringBuffer sb = new StringBuffer();
		sb.append(info.getDayStr()).append("_");
		sb.append(info.getFid()).append("_");
		sb.append(info.getBlockId()).append("_");
		sb.append(info.getIp());
		return sb.toString();
	}

}
