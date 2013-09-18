package xdata.etl.kafka.transform;

import xdata.etl.kafka.transform.cclient.AbstractCClientTransformer;
import xdata.etl.kafka.transform.cclient.data.AbstractCClientDataSwapper;
import xdata.etl.kafka.transform.cclient.data.CdnDataSwapper;
import xdata.etl.util.ClassScaner;

public class CdnTransformer extends AbstractCClientTransformer {

	public CdnTransformer(ClassScaner entityClassScaner) {
		super(entityClassScaner);
	}

	@Override
	protected AbstractCClientDataSwapper newDataSwapper(String raw) {
		return new CdnDataSwapper(raw);
	}

}
