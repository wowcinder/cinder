/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author XuehuiHe
 * @date 2013年10月17日
 */
public class GsonUtil {
	private static Gson gson;
	static {
		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();
		gson = gb.create();
	}

	public static String toJson(Object o) {
		return gson.toJson(o);
	}
}
