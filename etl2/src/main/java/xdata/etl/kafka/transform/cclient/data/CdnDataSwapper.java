package xdata.etl.kafka.transform.cclient.data;

public class CdnDataSwapper extends AbstractCClientDataSwapper {

	public CdnDataSwapper(String raw) {
		super(raw);
	}

	@Override
	protected void preRaw() {
		this.strs = this.raw.split("\\t");
	}
}
