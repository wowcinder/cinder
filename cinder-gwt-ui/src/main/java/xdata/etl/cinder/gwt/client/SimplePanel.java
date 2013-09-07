package xdata.etl.cinder.gwt.client;

import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class SimplePanel extends ContentPanel {
	public SimplePanel() {
		TextButton tb = new TextButton("测试");
		setHeadingText("测试");
		add(tb);
	}
}
