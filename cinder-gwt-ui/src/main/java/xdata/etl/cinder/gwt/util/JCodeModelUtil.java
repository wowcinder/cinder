package xdata.etl.cinder.gwt.util;

import java.io.File;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JType;

public class JCodeModelUtil {
	private final JCodeModel jCodeModel;

	public JCodeModelUtil() {
		jCodeModel = new JCodeModel();
	}

	public JCodeModel getjCodeModel() {
		return jCodeModel;
	}

	public File getMainJavaPath() {
		String path = JCodeModelUtil.class.getClassLoader().getResource("")
				.getFile();
		File file = new File(path);
		file = file.getParentFile().getParentFile();
		path = file.getAbsolutePath() + File.separator + "src" + File.separator
				+ "main" + File.separator + "java";
		file = new File(path);
		return file;
	}

	public JType getJType(Type type) throws ClassNotFoundException {
		if (type instanceof ParameterizedType) {
			Type[] parameters = ((ParameterizedType) type)
					.getActualTypeArguments();
			JClass jClass = jCodeModel
					.ref((Class<?>) ((ParameterizedType) type).getRawType());
			for (Type type2 : parameters) {
				jClass = jClass.narrow((JClass) getJType(type2));
			}
			return jClass;
		} else if (type instanceof GenericArrayType) {
			GenericArrayType arrayType = (GenericArrayType) type;
			return getJType(arrayType.getGenericComponentType()).array();
		} else if (type instanceof WildcardType) {
			return jCodeModel.ref(Object.class);
		} else {
			return jCodeModel.parseType(((Class<?>) type).getName());
		}

	}

}
