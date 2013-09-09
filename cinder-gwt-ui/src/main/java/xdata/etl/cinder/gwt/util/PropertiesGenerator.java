package xdata.etl.cinder.gwt.util;

import java.lang.reflect.Method;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import xdata.etl.cinder.common.util.ClassScaner;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sun.codemodel.ClassType;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;

public class PropertiesGenerator {
	private static JCodeModelUtil jCodeModelUtil;
	private static JDefinedClass propertyUtils;

	public static void main(String[] args) throws Exception {
		jCodeModelUtil = new JCodeModelUtil();
		propertyUtils = getjCodeModel()._class(PropertyUtils.class.getName());

		ClassScaner scaner = new ClassScaner("xdata.etl.cinder.shared.entity",
				"xdata.etl.cinder.hbasemeta.shared.entity",
				"xdata.etl.cinder.businessmeta.shared.entity");
		for (Class<?> clazz : scaner.getClazzes()) {
			if (clazz.isAnnotationPresent(Entity.class)) {
				generatePropertyClass(clazz);
			}
		}
		getjCodeModel().build(jCodeModelUtil.getMainJavaPath());
	}

	private static void generatePropertyClass(Class<?> clazz)
			throws JClassAlreadyExistsException, ClassNotFoundException {
		JDefinedClass dc = getjCodeModel()._class(
				"xdata.etl.cinder.gwt.client.property." + clazz.getSimpleName()
						+ "Property", ClassType.INTERFACE);
		dc._extends(getjCodeModel().ref(PropertyAccess.class).narrow(
				jCodeModelUtil.getJType(clazz)));
		for (Method method : clazz.getMethods()) {
			if (isDoGeneratePropertyClass(method)) {
				generatePropertyMethod(dc, method, clazz);
			}
		}
		writeGwtInstance(dc);
	}

	private static void writeGwtInstance(JDefinedClass dc)
			throws JClassAlreadyExistsException {
		propertyUtils.field(JMod.PUBLIC + JMod.STATIC + JMod.FINAL, dc,
				dc.name(), getjCodeModel().ref(GWT.class)
						.staticInvoke("create").arg(JExpr.dotclass(dc)));
	}

	public static void generatePropertyMethod(JDefinedClass dc, Method method,
			Class<?> clazz) throws ClassNotFoundException {
		String name = method.getName().replace("get", "");
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		if (method.isAnnotationPresent(Id.class)) {
			JMethod jMethod = dc.method(
					JMod.PUBLIC,
					getjCodeModel().ref(ModelKeyProvider.class).narrow(
							jCodeModelUtil.getJType(clazz)), "key");
			jMethod.annotate(Path.class).param("value", name);
		}
		dc.method(
				JMod.PUBLIC,
				getjCodeModel()
						.ref(ValueProvider.class)
						.narrow(jCodeModelUtil.getJType(clazz))
						.narrow(jCodeModelUtil.getJType(method
								.getGenericReturnType())), name);
	}

	private static boolean isDoGeneratePropertyClass(Method method) {
		Class<?> clazz = method.getDeclaringClass();
		return method.getName().startsWith("get")
				&& (clazz.isAnnotationPresent(Entity.class) || clazz
						.isAnnotationPresent(MappedSuperclass.class));
	}

	public static JCodeModel getjCodeModel() {
		return jCodeModelUtil.getjCodeModel();
	}
}
