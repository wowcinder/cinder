package xdata.etl.kafka.transform.json.v3a.deserializer;

import java.lang.reflect.Type;

import xdata.etl.entity.v3a.response.V3aUserAuthResponse;
import xdata.etl.kafka.transform.json.v3a.shadow.V3aUserAuthResponseShadow;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class V3aUserAuthResponseJsonDeserializer implements
		JsonDeserializer<V3aUserAuthResponse> {

	@Override
	public V3aUserAuthResponse deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		V3aUserAuthResponseShadow shadow = context.deserialize(json,
				V3aUserAuthResponseShadow.class);
		V3aUserAuthResponse response = new V3aUserAuthResponse();
		response.setStatus(shadow.status);
		if (shadow.userinfo != null) {
			response.setBalance(shadow.userinfo.balance);
			response.setEpgportal(shadow.userinfo.epgportal);
			response.setEpgportal2(shadow.userinfo.epgportal2);
			response.setHid(shadow.userinfo.hid);
			response.setIspid(shadow.userinfo.ispid);
			response.setOemid(shadow.userinfo.oemid);
			response.setUid(shadow.userinfo.uid);
		}
		return response;
	}

}
