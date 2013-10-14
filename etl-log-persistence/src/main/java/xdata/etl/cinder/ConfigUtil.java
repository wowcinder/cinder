/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder;

import java.io.File;

/**
 * @author XuehuiHe
 * @date 2013年10月14日
 */
public class ConfigUtil {
	private static Boolean isRunInAJar;
	private static String projectPath;

	public static boolean isRunInAJar() {
		if (isRunInAJar == null) {
			String className = getClazz().getName().replace('.', '/');
			String classJar = getClazz()
					.getResource("/" + className + ".class").toString();
			if (classJar.startsWith("jar:")) {
				isRunInAJar = true;
			} else {
				isRunInAJar = false;
			}
		}
		return isRunInAJar;
	}

	public static String findProjectPath() {
		if (projectPath == null) {
			if (isRunInAJar()) {
				String path = getClazz().getProtectionDomain().getCodeSource()
						.getLocation().getPath();
				File file = new File(path);
				projectPath = file.getParentFile().getAbsolutePath();
			} else {
				String path = getClazz().getClassLoader().getResource("")
						.getFile();
				File file = new File(path);
				projectPath = file.getParentFile().getParentFile()
						.getAbsolutePath();
			}
		}
		return projectPath;
	}

	private static Class<?> getClazz() {
		return ConfigUtil.class;
	}

	public static void main(String[] args) {
		System.out.println(isRunInAJar());
		System.out.println(findProjectPath());
	}
}
