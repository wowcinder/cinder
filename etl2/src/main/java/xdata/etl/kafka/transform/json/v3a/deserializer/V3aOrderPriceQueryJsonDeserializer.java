package xdata.etl.kafka.transform.json.v3a.deserializer;

import java.lang.reflect.Type;

import xdata.etl.entity.v3a.V3aOrderPriceQuery;
import xdata.etl.kafka.transform.json.v3a.shadow.V3aOrderPriceQueryShadow;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class V3aOrderPriceQueryJsonDeserializer implements
		JsonDeserializer<V3aOrderPriceQuery> {

	@Override
	public V3aOrderPriceQuery deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		V3aOrderPriceQueryShadow shadow = context.deserialize(json,
				V3aOrderPriceQueryShadow.class);
		if (shadow != null) {
			return shadow.getEntity();
		}
		return null;
	}

}
