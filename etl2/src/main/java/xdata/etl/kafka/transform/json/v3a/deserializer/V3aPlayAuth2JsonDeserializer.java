package xdata.etl.kafka.transform.json.v3a.deserializer;

import java.lang.reflect.Type;

import xdata.etl.entity.v3a.V3aPlayAuth2;
import xdata.etl.kafka.transform.json.v3a.shadow.V3aPlayAuth2Shadow;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class V3aPlayAuth2JsonDeserializer implements
		JsonDeserializer<V3aPlayAuth2> {

	@Override
	public V3aPlayAuth2 deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		V3aPlayAuth2Shadow shadow = context.deserialize(json,
				V3aPlayAuth2Shadow.class);
		if (shadow != null) {
			return shadow.getEntity();
		}
		return null;
	}

}
