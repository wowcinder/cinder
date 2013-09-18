package xdata.etl.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

	private static Map<String, Gson> gsonMap = new ConcurrentHashMap<String, Gson>();

	private static synchronized void initSimpleGson() {
		String name = "simple";
		if (gsonMap.containsKey(name)) {
			return;
		}
		GsonBuilder gb = new GsonBuilder();
		gsonMap.put(name, gb.create());
	}

	public static Gson getSimpleGson() {
		String name = "simple";
		if (!gsonMap.containsKey(name)) {
			initSimpleGson();
		}
		return gsonMap.get(name);
	}

}
