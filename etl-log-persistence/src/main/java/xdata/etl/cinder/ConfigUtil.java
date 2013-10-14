/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;

/**
 * @author XuehuiHe
 * @date 2013年10月14日
 */
public class ConfigUtil {
	private static Boolean isRunInAJar;
	private static String projectPath;

	public static void init() {
		if (System.getProperty("etl.conf") == null) {
			System.setProperty("etl.conf", getConfPath());
		}
		if (System.getProperty("etl.log") == null) {
			System.setProperty("etl.log", getLogPath());
		}
		PropertyConfigurator.configure(System.getProperty(
				"log4j.configuration", getConfPath() + File.separator
						+ "log4j.properties"));
	}

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

	public static String getConfPath() {
		if (isRunInAJar()) {
			return findProjectPath() + File.separator + "conf";
		} else {
			return findProjectPath() + File.separator + "src" + File.separator
					+ "main" + File.separator + "resources";

		}
	}

	public static String getLogPath() {
		if (isRunInAJar()) {
			return findProjectPath() + File.separator + "logs";
		} else {
			return findProjectPath() + File.separator + "target"
					+ File.separator + "logs";
		}
	}

	private static Class<?> getClazz() {
		return ConfigUtil.class;
	}

	public static void main(String[] args) {
		System.out.println(isRunInAJar());
		System.out.println(findProjectPath());
		System.out.println(getConfPath());
		System.out.println(getLogPath());
	}
}
