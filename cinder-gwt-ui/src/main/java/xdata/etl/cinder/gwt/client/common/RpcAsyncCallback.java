package xdata.etl.cinder.gwt.client.common;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import xdata.etl.cinder.shared.exception.NoLoginException;
import xdata.etl.cinder.shared.exception.PermissionException;
import xdata.etl.cinder.shared.exception.SharedException;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.info.Info;

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
		if (caught instanceof PermissionException) {
			AlertMessageBox d = new AlertMessageBox("操作失败", "权限不足");
			d.show();
		} else if (caught instanceof ConstraintViolationException) {
			Set<ConstraintViolation<?>> sets = ((ConstraintViolationException) caught)
					.getConstraintViolations();
			String sb = "";
			for (ConstraintViolation<?> cv : sets) {
				if (!"".equals(sb)) {
					sb += "<br />";
				}
				sb += cv.getPropertyPath() + " " + cv.getMessage();
			}
			Info.display("操作失败", sb);
		} else if (caught instanceof NoLoginException) {
			Info.display("操作失败", "登录超时");
		} else if (caught instanceof SharedException) {
			Info.display("操作失败", caught.getMessage());
		} else {
			Info.display("操作失败", "未知异常!!");
		}
	}

	public abstract void _onSuccess(T t);

}
