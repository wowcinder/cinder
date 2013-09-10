/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.user.editor;

import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.ui.user.combox.UserGroupCombox;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.shared.entity.user.User;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public class UserEditor extends CinderEditor<User> {
	private static final Driver DRIVER = GWT.create(Driver.class);

	public interface Driver extends SimpleBeanEditorDriver<User, UserEditor> {

	}

	TextField email;

	TextField password;

	UserGroupCombox userGroup;

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public UserEditor() {
		super(DRIVER, "用户");
	}

	@Override
	protected void _update(User t) {
		RpcServiceUtils.UserRpcService.updateUser(t, t.getPassword(),
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(User t) {
		RpcServiceUtils.UserRpcService.saveUser(t, t.getPassword(),
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _initView() {
		email = new TextField();
		layoutContainer.add(new FieldLabel(email, "email"), vd);
		password = new TextField();
		layoutContainer.add(new FieldLabel(password, "password"), vd);
		userGroup = new UserGroupCombox();
		layoutContainer.add(new FieldLabel(userGroup, "group"), vd);
	}

}
