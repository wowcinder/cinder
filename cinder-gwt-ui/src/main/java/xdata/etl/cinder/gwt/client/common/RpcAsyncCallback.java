package xdata.etl.cinder.gwt.client.common;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class RpcAsyncCallback<T> implements AsyncCallback<T> {

	public RpcAsyncCallback() {
		preSend();
	}

	public void preSend() {

	}

	public void preArrive() {

	}

	public void postArrive() {

	}

	public void preFailure(Throwable caught) {

	}

	public void postFailure(Throwable caught) {

	}

	public void preSuccess(T t) {

	}

	public void postSuccess(T t) {

	}

	@Override
	public void onFailure(Throwable caught) {
		preArrive();
		preFailure(caught);
		_onFailure(caught);
		postFailure(caught);
		postArrive();
	}

	@Override
	public void onSuccess(T t) {
		preArrive();
		preSuccess(t);
		_onSuccess(t);
		postSuccess(t);
		postArrive();
	}

	public void _onFailure(Throwable caught) {
	}

	public abstract void _onSuccess(T t);

}
