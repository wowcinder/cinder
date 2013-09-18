package xdata.etl.util;

public class ClassNameUtil {

	public static String getClassName(String name) {
		name = getAttributeName(name);
		return getFirstCharUpper(name);
	}

	public static String getAttributeName(String name) {
		if (name.indexOf("_") == -1) {
			return getFirstCharLowerAttributeName(name);
		}
		String[] strs = name.split("_");
		StringBuffer sb = new StringBuffer();
		for (int i = 0, len = strs.length; i < len; i++) {
			if (i == 0) {
				sb.append(getFirstCharLowerAttributeName(strs[i]));
			} else {
				sb.append(getFirstCharUpperAttributeName(strs[i]));
			}
		}
		return sb.toString();
	}

	public static String getMethodSuffix(String name) {
		if (name.indexOf("_") == -1) {
			return getFirstCharUpperAttributeName(name);
		}
		String[] strs = name.split("_");
		StringBuffer sb = new StringBuffer();
		for (int i = 0, len = strs.length; i < len; i++) {
			sb.append(getFirstCharUpperAttributeName(strs[i]));
		}
		return sb.toString();
	}

	public static String getGetMethodName(String name) {
		return "get" + getMethodSuffix(name);
	}

	public static String getSetMethodName(String name) {
		return "set" + getMethodSuffix(name);
	}

	public static String getFirstCharLowerAttributeName(String name) {
		if (isAllUpperCase(name)) {
			return name;
		}
		return getFirstCharLower(name);
	}

	public static String getFirstCharUpperAttributeName(String name) {
		if (isAllUpperCase(name)) {
			return name;
		}
		return getFirstCharUpper(name);
	}

	public static String getFirstCharLower(String string) {
		return string.substring(0, 1).toLowerCase() + string.substring(1);
	}

	public static String getFirstCharUpper(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	public static boolean isAllUpperCase(String str) {
		if (str == null) {
			return true;
		}
		return str.toUpperCase().equals(str);
	}
}
