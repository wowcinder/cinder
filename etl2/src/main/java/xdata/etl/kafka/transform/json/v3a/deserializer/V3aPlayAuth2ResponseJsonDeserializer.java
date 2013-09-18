package xdata.etl.kafka.transform.json.v3a.deserializer;

import java.lang.reflect.Type;

import xdata.etl.entity.v3a.response.V3aPlayAuth2Response;
import xdata.etl.kafka.transform.json.v3a.shadow.V3aPlayAuth2ResponseShadow;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class V3aPlayAuth2ResponseJsonDeserializer implements
		JsonDeserializer<V3aPlayAuth2Response> {

	@Override
	public V3aPlayAuth2Response deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		V3aPlayAuth2ResponseShadow shadow = context.deserialize(json,
				V3aPlayAuth2ResponseShadow.class);
		if (shadow != null) {
			return shadow.getResponse();
		}
		return null;
	}

}
