package xdata.etl.cinder.gwt.client.ui.menu.editor;

import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.shared.entity.menu.MenuGroup;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class MenuGroupEditor extends CinderEditor<MenuGroup> {
	private static final Driver DRIVER = GWT
			.create(Driver.class);

	public interface Driver extends
			SimpleBeanEditorDriver<MenuGroup, MenuGroupEditor> {

	}

	TextField name;

	public MenuGroupEditor() {
		super(DRIVER, "菜单组");
	}

	@Override
	protected void _update(MenuGroup t) {
		RpcServiceUtils.MenuRpcService.updateMenuGroup(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(MenuGroup t) {
		RpcServiceUtils.MenuRpcService.saveMenuGroup(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _initView() {
		name = new TextField();
		layoutContainer.add(new FieldLabel(name, "name"), vd);

	}

}
