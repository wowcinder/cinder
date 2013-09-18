package xdata.etl;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public enum RunableCLI implements IRunableCLI {
	/**
	 * 本地使用测试服务器环境
	 */
	LOCAL_TEST() {

		@Override
		protected void initPath() {
			setConfPath(RunableCLIConfiUtil.findConfPath(""));
			setLogPath(RunableCLIConfiUtil.findLogPath());
		}
	},
	/**
	 * 本地使用生产服务器环境
	 */
	LOCAL_PRODUTION() {
		protected AbstractXmlApplicationContext createSpringCxt() {
			File file = new File(getConfPath());
			return new FileSystemXmlApplicationContext(file.getParentFile()
					.getAbsolutePath()
					+ File.separator
					+ "applicationContext-common.xml");
		}

		@Override
		protected void initPath() {
			setConfPath(RunableCLIConfiUtil.findConfPath("production"));
			setLogPath(RunableCLIConfiUtil.findLogPath());
		}
	},
	/**
	 * 测试服务器
	 */
	TEST_SERVER() {

	},
	/**
	 * 生产服务器
	 */
	PRODUTION() {
	};

	private String logPath;
	private String confPath;

	public String getLogPath() {
		return logPath;
	}

	public String getConfPath() {
		return confPath;
	}

	public void setLogPath(String logPath) {
		if (isLinux()) {
			this.logPath = "/" + logPath;
		} else {
			this.logPath = logPath;
		}

	}

	public void setConfPath(String confPath) {
		if (isLinux()) {
			this.confPath = "/" + confPath;
		} else {
			this.confPath = confPath;
		}
	}

	private AbstractXmlApplicationContext ctx;

	public AbstractXmlApplicationContext getSpringCxt() {
		if (ctx != null) {
			return ctx;
		}
		init();
		ctx = createSpringCxt();
		return ctx;
	}

	protected AbstractXmlApplicationContext createSpringCxt() {
		return new FileSystemXmlApplicationContext(getConfPath()
				+ File.separator + "applicationContext-common.xml");
	}

	private Boolean isLinux;

	private boolean isLinux() {
		if (isLinux == null) {
			isLinux = System.getProperty("os.name").toLowerCase()
					.indexOf("linux") >= 0;
		}
		return isLinux;
	}

	protected void initPath() {
		setConfPath(RunableCLIConfiUtil.findConfPath());
		setLogPath(RunableCLIConfiUtil.findLogPath());
	}

	protected void init() {
		initPath();
		System.setProperty("etl.log", getLogPath());
		System.setProperty("etl.conf", getConfPath());
		PropertyConfigurator.configure(System.getProperty(
				"log4j.configuration", getConfPath() + File.separator
						+ "log4j.properties"));
	}

	public static class RunableCLIConfiUtil {
		public RunableCLIConfiUtil() {
		}

		public static String findConfPath() {
			return findConfPath("conf");
		}

		public static String findConfPath(String folderName) {
			String confPath = System.getProperty("etl.conf");
			if (confPath != null) {
				return confPath;
			}
			String projectPath = findProjectPath();
			if (!isRunInAJar()) {
				projectPath = projectPath + File.separator + "src"
						+ File.separator + "main" + File.separator
						+ "resources";
			}
			if (folderName == null || folderName.equals("")) {
				return projectPath;
			}
			return projectPath + File.separator + folderName;
		}

		public static String findLogPath() {
			return findLogPath("log");
		}

		public static String findLogPath(String folderName) {
			String logPath = System.getProperty("etl.log");
			if (logPath != null) {
				return logPath;
			}
			String projectPath = findProjectPath();
			return projectPath + File.separator + folderName;
		}

		private static String projectPath;

		protected static String findProjectPath() {
			if (projectPath == null) {
				String path = getClazz().getClassLoader().getResource("")
						.getFile();
				if (isRunInAJar()) {
					projectPath = path;
				} else {
					File file = new File(path);
					projectPath = file.getParentFile().getParentFile()
							.getAbsolutePath();
				}
			}
			return projectPath;
		}

		private static Boolean isRunInAJar;

		protected static boolean isRunInAJar() {
			if (isRunInAJar == null) {
				String className = getClazz().getName().replace('.', '/');
				String classJar = getClazz().getResource(
						"/" + className + ".class").toString();
				if (classJar.startsWith("jar:")) {
					isRunInAJar = true;
				} else {
					isRunInAJar = false;
				}
			}
			return isRunInAJar;
		}

		private static Class<?> getClazz() {
			return RunableCLIConfiUtil.class;
		}
	}
}
