package xdata.etl.cinder.gwt.client.common;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class RpcAsyncCallback<T> implements AsyncCallback<T> {

	public static <T> RpcAsyncCallback<T> dealWith(final GwtCallBack<T> deal) {
		return new RpcAsyncCallback<T>() {
			@Override
			public void _onSuccess(T t) {
				deal.call(t);
			}

			@Override
			public void _onFailure(Throwable caught) {
				super._onFailure(caught);
				deal.fail();
			}
		};
	}

	public RpcAsyncCallback() {
		pre();
	}

	public void pre() {

	}

	public void post() {

	}

	@Override
	public void onFailure(Throwable caught) {
		pre();
		_onFailure(caught);
		post();
	}

	@Override
	public void onSuccess(T t) {
		pre();
		_onSuccess(t);
		post();
	}

	public void _onFailure(Throwable caught) {
	}

	public abstract void _onSuccess(T t);

}
