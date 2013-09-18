package xdata.etl.kafka.transform;

import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.kafka.exception.KafkaTransformException;
import xdata.etl.kafka.transform.handler.KafkaTransformHandler;
import xdata.etl.kafka.transform.json.AssistGson;
import xdata.etl.kafka.transform.result.DefaultTransformResult;
import xdata.etl.kafka.transform.result.KafkaTransformResult;

import com.google.gson.JsonSyntaxException;

public class AssistTransformer implements KafkaTransformer {

	private AssistGson gson;

	public AssistTransformer() {
		gson = AssistGson.getInstance();
	}

	@Override
	public KafkaTransformResult transform(Class<? extends HbaseEntity> clazz,
			String raw, KafkaTransformHandler... handlers)
			throws KafkaTransformException {
		try {
			HbaseEntity entity = getGson().fromJson(raw, clazz);
			entity.setRaw(raw);
			
			KafkaTransformResult result = new DefaultTransformResult();
			result.add(entity);
			for (KafkaTransformHandler handler : handlers) {
				handler.handler(result);
			}
			return result;
		} catch (JsonSyntaxException e) {
			throw new KafkaTransformException(clazz, raw);
		}
	}

	public AssistGson getGson() {
		return gson;
	}

}
