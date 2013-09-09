package xdata.etl.cinder.gwt.client.ui.menu.editor;

import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.shared.entity.menu.Menu;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class MenuEditor extends CinderEditor<Menu> {
	private static final Driver DRIVER = GWT.create(Driver.class);

	public interface Driver extends SimpleBeanEditorDriver<Menu, MenuEditor> {

	}

	TextField name;

	public MenuEditor() {
		super(DRIVER, "菜单");
	}

	@Override
	protected void _update(Menu t) {
		RpcServiceUtils.MenuRpcService.updateMenu(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(Menu t) {
		RpcServiceUtils.MenuRpcService.saveMenu(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _initView() {
		name = new TextField();
		layoutContainer.add(new FieldLabel(name, "name"), vd);
	}

}
