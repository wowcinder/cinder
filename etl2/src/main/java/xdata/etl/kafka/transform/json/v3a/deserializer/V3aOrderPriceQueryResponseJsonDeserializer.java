package xdata.etl.kafka.transform.json.v3a.deserializer;

import java.lang.reflect.Type;

import xdata.etl.entity.v3a.response.V3aOrderPriceQueryResponse;
import xdata.etl.kafka.transform.json.v3a.shadow.V3aOrderPriceQueryResponseShadow;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class V3aOrderPriceQueryResponseJsonDeserializer implements
		JsonDeserializer<V3aOrderPriceQueryResponse> {

	@Override
	public V3aOrderPriceQueryResponse deserialize(JsonElement json,
			Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		V3aOrderPriceQueryResponseShadow shadow = context.deserialize(json,
				V3aOrderPriceQueryResponseShadow.class);
		if (shadow != null) {
			return shadow.getResponse();
		}
		return null;
	}

}
