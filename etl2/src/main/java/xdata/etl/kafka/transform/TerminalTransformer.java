package xdata.etl.kafka.transform;

import xdata.etl.kafka.transform.cclient.AbstractCClientTransformer;
import xdata.etl.kafka.transform.cclient.data.AbstractCClientDataSwapper;
import xdata.etl.kafka.transform.cclient.data.TerminalDataSwapper;
import xdata.etl.util.ClassScaner;

public class TerminalTransformer extends AbstractCClientTransformer {

	public TerminalTransformer(ClassScaner entityClassScaner) {
		super(entityClassScaner);
	}

	@Override
	protected AbstractCClientDataSwapper newDataSwapper(String raw) {
		return new TerminalDataSwapper(raw);
	}

}
