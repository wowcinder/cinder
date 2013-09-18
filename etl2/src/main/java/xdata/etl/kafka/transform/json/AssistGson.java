package xdata.etl.kafka.transform.json;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

import xdata.etl.entity.assist.AssistUpdateStatistics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class AssistGson {
	private static AssistGson assistGson;

	private Gson gson;

	private AssistGson(Gson gson) {
		this.gson = gson;
	}

	public <T> T fromJson(String json, Class<T> classOfT)
			throws JsonSyntaxException {
		json = json.replaceAll("\\[\\]", "null");
		return gson.fromJson(json, classOfT);
	}

	public <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
		json = json.replaceAll("\\[\\]", "null");
		return gson.fromJson(json, typeOfT);
	}

	public static AssistGson getInstance() {
		if (assistGson == null) {
			initAssistGson();
		}
		return assistGson;
	}

	private static synchronized void initAssistGson() {
		if (assistGson != null) {
			return;
		}
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			@Override
			public Date deserialize(JsonElement json, Type typeOfT,
					JsonDeserializationContext context)
					throws JsonParseException {
				long l = json.getAsLong();
				Date now = new Date();
				now.setTime(l);
				return now;
			}

		});

		gb.registerTypeAdapterFactory(AssistUpdateStatisticsTypeAdapter.FACTORY);

		assistGson = new AssistGson(gb.create());
	}

	public static class AssistUpdateStatisticsTypeAdapter extends
			TypeAdapter<AssistUpdateStatistics> {

		public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
			@SuppressWarnings("unchecked")
			@Override
			public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
				if (type.getRawType() == AssistUpdateStatistics.class) {
					return (TypeAdapter<T>) new AssistUpdateStatisticsTypeAdapter(
							gson);
				}
				return null;
			}
		};

		private Gson gson;

		public AssistUpdateStatisticsTypeAdapter(Gson gson) {
			this.gson = gson;
		}

		@Override
		public void write(JsonWriter out, AssistUpdateStatistics value)
				throws IOException {
		}

		@Override
		public AssistUpdateStatistics read(JsonReader in) throws IOException {
			AssistUpdateStatistics result = gson.getDelegateAdapter(FACTORY,
					TypeToken.get(AssistUpdateStatistics.class)).read(in);
			if (result.getInfoShadow() != null
					&& result.getInfoShadow().size() > 0) {
				result.getInfo().addAll(result.getInfoShadow());
				result.setInfoShadow(null);
			}
			return result;
		}

	}

}
