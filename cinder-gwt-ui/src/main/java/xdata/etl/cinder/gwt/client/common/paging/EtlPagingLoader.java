package xdata.etl.cinder.gwt.client.common.paging;

import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;
import xdata.etl.cinder.shared.paging.PagingCondition;

import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;

public class EtlPagingLoader<T> extends
		PagingLoader<EtlPagingLoadConfigBean, PagingLoadResult<T>> {
	private PagingCondition condition;

	public void setCondition(PagingCondition condition) {
		this.condition = condition;
	}

	public PagingCondition getCondition() {
		return condition;
	}

	public EtlPagingLoader(
			DataProxy<EtlPagingLoadConfigBean, PagingLoadResult<T>> proxy) {
		super(proxy);
	}

	public boolean load(EtlPagingLoadConfigBean loadConfig) {
		loadConfig.setCondition(getCondition());
		return super.load(loadConfig);
	}

	protected EtlPagingLoadConfigBean newLoadConfig() {
		return new EtlPagingLoadConfigBean();
	}
}
