package xdata.etl.kafka.transform;

import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.kafka.exception.KafkaTransformException;
import xdata.etl.kafka.transform.handler.KafkaTransformHandler;
import xdata.etl.kafka.transform.json.V3aGson;
import xdata.etl.kafka.transform.result.DefaultTransformResult;
import xdata.etl.kafka.transform.result.KafkaTransformResult;

import com.google.gson.JsonSyntaxException;

public class V3aTransformer implements KafkaTransformer {
	private V3aGson gson;

	public V3aTransformer() {
		gson = V3aGson.getInstance();
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
			throw new KafkaTransformException(clazz, raw, e);
		}
	}

	public V3aGson getGson() {
		return gson;
	}

}
