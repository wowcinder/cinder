/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.user.editor;

import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.shared.entity.user.UserGroup;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public class UserGroupEditor extends CinderEditor<UserGroup> {
	private static final Driver DRIVER = GWT.create(Driver.class);

	public interface Driver extends
			SimpleBeanEditorDriver<UserGroup, UserGroupEditor> {

	}

	TextField name;

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public UserGroupEditor() {
		super(DRIVER, "用户组");
	}

	@Override
	protected void _update(UserGroup t) {
		RpcServiceUtils.UserRpcService.updateUserGroup(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(UserGroup t) {
		RpcServiceUtils.UserRpcService.saveUserGroup(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _initView() {
		name = new TextField();
		layoutContainer.add(new FieldLabel(name, "name"), vd);

	}

}
