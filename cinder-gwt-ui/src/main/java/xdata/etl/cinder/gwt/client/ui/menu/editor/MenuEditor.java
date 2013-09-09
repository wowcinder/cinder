package xdata.etl.cinder.gwt.client.ui.menu.editor;

import xdata.etl.cinder.gwt.client.common.GwtCallBack;
import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.ui.menu.grid.AuthorizeGridWindow;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.shared.entity.authorize.Authorize;
import xdata.etl.cinder.shared.entity.menu.Menu;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.cell.core.client.form.TextInputCell.TextFieldAppearance;
import com.sencha.gxt.cell.core.client.form.ValueBaseInputCell;
import com.sencha.gxt.cell.core.client.form.ViewData;
import com.sencha.gxt.widget.core.client.event.FocusEvent;
import com.sencha.gxt.widget.core.client.event.FocusEvent.FocusHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;

public class MenuEditor extends CinderEditor<Menu> {
	private static final Driver DRIVER = GWT.create(Driver.class);

	public interface Driver extends SimpleBeanEditorDriver<Menu, MenuEditor> {

	}

	TextField name;
	TextField token;
	ValueBaseField<Authorize> requireAuthorize;
	@Ignore
	private AuthorizeGridWindow authorizeGridWindow;

	public MenuEditor() {
		super(DRIVER, "菜单");
		authorizeGridWindow = new AuthorizeGridWindow(
				new GwtCallBack<Authorize>() {

					@Override
					protected void _call(Authorize t) {
						requireAuthorize.setValue(t);
					}
				});
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

		token = new TextField();
		layoutContainer.add(new FieldLabel(token, "token"), vd);

		requireAuthorize = new ValueBaseField<Authorize>(
				new ValueBaseInputCell<Authorize>(
						GWT.<TextFieldAppearance> create(TextFieldAppearance.class)) {
					@Override
					public void render(
							com.google.gwt.cell.client.Cell.Context context,
							Authorize value, SafeHtmlBuilder sb) {
						String str = "";
						if (value != null) {
							str = value.getGroup().getName() + "_"
									+ value.getName();
						}
						ViewData viewData = checkViewData(context, str);
						String s = (viewData != null) ? viewData
								.getCurrentValue() : str;

						FieldAppearanceOptions options = new FieldAppearanceOptions(
								getWidth(), getHeight(), isReadOnly(),
								getEmptyText());
						options.setName(getName());
						options.setDisabled(isDisabled());
						((TextFieldAppearance) getAppearance()).render(sb,
								"text", s == null ? "" : s, options);
					}
				}) {

		};
		layoutContainer.add(
				new FieldLabel(requireAuthorize, "requireAuthorize"), vd);

		requireAuthorize.addFocusHandler(new FocusHandler() {
			@Override
			public void onFocus(FocusEvent event) {
				authorizeGridWindow.show();
			}
		});
	}
}
