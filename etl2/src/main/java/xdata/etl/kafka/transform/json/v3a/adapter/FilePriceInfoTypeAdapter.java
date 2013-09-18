package xdata.etl.kafka.transform.json.v3a.adapter;

import java.io.IOException;

import xdata.etl.entity.v3a.srv.V3aOrderPriceQueryFilmPrice;
import xdata.etl.kafka.transform.json.v3a.shadow.V3aOrderPriceQueryResponseShadow.FilePriceInfo;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class FilePriceInfoTypeAdapter extends TypeAdapter<FilePriceInfo> {
	public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
		@SuppressWarnings("unchecked")
		@Override
		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
			if (type.getRawType().equals(FilePriceInfo.class)) {
				return (TypeAdapter<T>) new FilePriceInfoTypeAdapter(gson);
			}
			return null;
		}
	};

	private Gson gson;

	public FilePriceInfoTypeAdapter(Gson gson) {
		setGson(gson);
	}

	@Override
	public void write(JsonWriter out, FilePriceInfo value) throws IOException {

	}

	@Override
	public FilePriceInfo read(JsonReader in) throws IOException {
		JsonToken token = in.peek();
		if (token.equals(JsonToken.BEGIN_OBJECT)) {
			FilePriceInfo list = new FilePriceInfo();
			V3aOrderPriceQueryFilmPrice one = gson.fromJson(in,
					V3aOrderPriceQueryFilmPrice.class);
			list.add(one);
			return list;
		} else if (token.equals(JsonToken.BEGIN_ARRAY)) {
			return gson.getDelegateAdapter(FACTORY,
					TypeToken.get(FilePriceInfo.class)).read(in);
		}
		return null;
	}

	public Gson getGson() {
		return gson;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

}
