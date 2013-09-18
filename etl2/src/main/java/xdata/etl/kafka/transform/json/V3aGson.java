package xdata.etl.kafka.transform.json;

import java.util.Date;

import xdata.etl.entity.v3a.V3aOrderPriceQuery;
import xdata.etl.entity.v3a.V3aPlayAuth2;
import xdata.etl.entity.v3a.response.V3aOrderPriceQueryResponse;
import xdata.etl.entity.v3a.response.V3aPlayAuth2Response;
import xdata.etl.entity.v3a.response.V3aUserAuthResponse;
import xdata.etl.kafka.transform.json.v3a.adapter.AdInfoTypeAdapter;
import xdata.etl.kafka.transform.json.v3a.adapter.FilePriceInfoTypeAdapter;
import xdata.etl.kafka.transform.json.v3a.adapter.V3aRequestTypeAdapter;
import xdata.etl.kafka.transform.json.v3a.deserializer.V3aDateJsonDeserializer;
import xdata.etl.kafka.transform.json.v3a.deserializer.V3aOrderPriceQueryJsonDeserializer;
import xdata.etl.kafka.transform.json.v3a.deserializer.V3aOrderPriceQueryResponseJsonDeserializer;
import xdata.etl.kafka.transform.json.v3a.deserializer.V3aPlayAuth2JsonDeserializer;
import xdata.etl.kafka.transform.json.v3a.deserializer.V3aPlayAuth2ResponseJsonDeserializer;
import xdata.etl.kafka.transform.json.v3a.deserializer.V3aUserAuthResponseJsonDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class V3aGson {
	private static V3aGson v3aGson;

	private Gson gson;

	private static Gson simpleGson;

	private V3aGson(Gson gson) {
		this.gson = gson;
	}

	public <T> T fromJson(String json, Class<T> classOfT)
			throws JsonSyntaxException {
		json = json.replaceAll("\\[\\]", "null");
		return gson.fromJson(json, classOfT);
	}

	public static Gson getSimpleInstance() {
		if (simpleGson == null) {
			createSimpleGson();
		}
		return simpleGson;
	}

	private synchronized static void createSimpleGson() {
		if (simpleGson != null) {
			return;
		}
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Date.class, new V3aDateJsonDeserializer());
		simpleGson = gb.create();

	}

	public static V3aGson getInstance() {
		if (v3aGson == null) {
			initV3aGson();
		}
		return v3aGson;
	}

	private static synchronized void initV3aGson() {
		if (v3aGson != null) {
			return;
		}
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Date.class, new V3aDateJsonDeserializer());
		gb.registerTypeAdapter(V3aUserAuthResponse.class,
				new V3aUserAuthResponseJsonDeserializer());
		gb.registerTypeAdapter(V3aOrderPriceQueryResponse.class,
				new V3aOrderPriceQueryResponseJsonDeserializer());
		gb.registerTypeAdapter(V3aPlayAuth2Response.class,
				new V3aPlayAuth2ResponseJsonDeserializer());
		gb.registerTypeAdapter(V3aOrderPriceQuery.class,
				new V3aOrderPriceQueryJsonDeserializer());
		gb.registerTypeAdapter(V3aPlayAuth2.class,
				new V3aPlayAuth2JsonDeserializer());

		gb.registerTypeAdapterFactory(V3aRequestTypeAdapter.FACTORY);
		gb.registerTypeAdapterFactory(AdInfoTypeAdapter.FACTORY);
		gb.registerTypeAdapterFactory(FilePriceInfoTypeAdapter.FACTORY);
		v3aGson = new V3aGson(gb.create());
	}

	public V3aGson() {
	}

}
