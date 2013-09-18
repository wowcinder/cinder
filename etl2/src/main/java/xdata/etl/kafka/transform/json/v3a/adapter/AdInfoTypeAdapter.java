package xdata.etl.kafka.transform.json.v3a.adapter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import xdata.etl.entity.v3a.srv.V3aPlayAuth2PicAd;
import xdata.etl.entity.v3a.srv.V3aPlayAuth2TxtAd;
import xdata.etl.entity.v3a.srv.V3aPlayAuth2VideoAd;
import xdata.etl.kafka.transform.json.v3a.shadow.V3aPlayAuth2ResponseShadow.AdInfo;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.StringMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class AdInfoTypeAdapter extends TypeAdapter<AdInfo> {
	public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
		@SuppressWarnings("unchecked")
		@Override
		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
			if (type.getRawType().equals(AdInfo.class)) {
				return (TypeAdapter<T>) new AdInfoTypeAdapter(gson);
			}
			return null;
		}
	};

	private Gson gson;

	public AdInfoTypeAdapter(Gson gson) {
		this.gson = gson;
	}

	@Override
	public void write(JsonWriter out, AdInfo value) throws IOException {
	}

	@SuppressWarnings("unchecked")
	@Override
	public AdInfo read(JsonReader in) throws IOException {
		JsonToken token = in.peek();
		if (token.equals(JsonToken.BEGIN_OBJECT)) {
			return gson
					.getDelegateAdapter(FACTORY, TypeToken.get(AdInfo.class))
					.read(in);
		} else if (token.equals(JsonToken.BEGIN_ARRAY)) {
			Type objectType = new TypeToken<List<List<StringMap<Object>>>>() {
			}.getType();
			List<List<StringMap<Object>>> objectList = gson.fromJson(in,
					objectType);
			if (objectList.size() > 0 && objectList.get(0).size() > 0) {
				StringMap<Object> map = objectList.get(0).get(0);
				Type type = null;
				if (map.containsKey("txt")) {
					type = new TypeToken<List<V3aPlayAuth2TxtAd>>() {
					}.getType();
				} else if (map.containsKey("pic")) {
					type = new TypeToken<List<V3aPlayAuth2PicAd>>() {
					}.getType();
				} else if (map.containsKey("video")) {
					type = new TypeToken<List<V3aPlayAuth2VideoAd>>() {
					}.getType();
				} else {
					return null;
				}
				List<?> list = gson.fromJson(gson.toJson(objectList.get(0)),
						type);
				AdInfo adInfo = new AdInfo();
				if (map.containsKey("txt")) {
					adInfo.getTxtlist().addAll(
							(Collection<? extends V3aPlayAuth2TxtAd>) list);
				} else if (map.containsKey("pic")) {
					adInfo.getPiclist().addAll(
							(Collection<? extends V3aPlayAuth2PicAd>) list);
				} else if (map.containsKey("video")) {
					adInfo.getVideolist().addAll(
							(Collection<? extends V3aPlayAuth2VideoAd>) list);
				}
				return adInfo;
			}
		}
		return null;
	}
}
