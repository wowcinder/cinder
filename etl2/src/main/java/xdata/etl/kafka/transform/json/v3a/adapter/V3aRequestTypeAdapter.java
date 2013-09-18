package xdata.etl.kafka.transform.json.v3a.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import xdata.etl.hbase.entity.v3a.V3aRequest;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class V3aRequestTypeAdapter extends TypeAdapter<V3aRequest> {

	public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
		@SuppressWarnings("unchecked")
		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
			if (V3aRequest.class.isAssignableFrom(type.getRawType())) {
				return (TypeAdapter<T>) new V3aRequestTypeAdapter(gson,
						type.getRawType());
			}
			return null;
		}
	};

	private Gson gson;
	private Class<?> clazz;

	public V3aRequestTypeAdapter(Gson gson, Class<?> clazz) {
		this.gson = gson;
		this.clazz = clazz;
	}

	@Override
	public void write(JsonWriter out, V3aRequest value) throws IOException {

	}

	@Override
	public V3aRequest read(JsonReader in) throws IOException {
		JsonToken token = in.peek();
		if (token.equals(JsonToken.STRING)) {
			String url = in.nextString();
			String[] strs = url.split("&");
			Map<String, String> map = new HashMap<String, String>();
			for (String str : strs) {
				String[] strs2 = str.split("=");
				if (strs2.length == 2) {
					if (strs2[1].trim().equals("null")) {
						map.put(strs2[0].trim(), null);
					} else {
						map.put(strs2[0].trim(), strs2[1].trim());
					}
				} else if (strs2.length == 1) {
					map.put(strs2[0].trim(), null);
				}
			}
			String json = getGson().toJson(map);
			@SuppressWarnings("unchecked")
			TypeAdapter<V3aRequest> typeAdapter = (TypeAdapter<V3aRequest>) getGson()
					.getDelegateAdapter(V3aRequestTypeAdapter.FACTORY,
							TypeToken.get(clazz));
			return typeAdapter.fromJson(json);
		}
		return null;
	}

	public Gson getGson() {
		return gson;
	}

}
