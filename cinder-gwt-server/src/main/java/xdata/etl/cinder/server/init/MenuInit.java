package xdata.etl.cinder.server.init;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xdata.etl.cinder.common.util.ClassScaner;
import xdata.etl.cinder.service.MenuService;
import xdata.etl.cinder.shared.annotations.MenuToken;

@Component
public class MenuInit implements InitializingBean {
	private ClassScaner scanner;
	@Autowired
	private MenuService menuService;

	public MenuInit() {
		try {
			scanner = new ClassScaner("xdata.etl.cinder.gwt.client.ui");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		List<MenuToken> list = new ArrayList<MenuToken>();
		for (Class<?> clazz : scanner.getClazzes()) {
			if (clazz.isAnnotationPresent(MenuToken.class)) {
				list.add(clazz.getAnnotation(MenuToken.class));
			}
		}
		menuService.insertMenuConfig(list);
	}

}
