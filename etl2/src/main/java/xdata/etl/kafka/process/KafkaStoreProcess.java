package xdata.etl.kafka.process;

import xdata.etl.hbase.HbaseContext;
import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.hbase.exception.HbaseORMException;
import xdata.etl.hbase.lazy.IHbaseLazySaveContainer;
import xdata.etl.kafka.exception.KafkaTopicProcessException;
import xdata.etl.kafka.exception.KafkaTransformException;
import xdata.etl.kafka.transform.handler.KafkaTransformHandler;
import xdata.etl.kafka.transform.result.KafkaTransformResult;

public class KafkaStoreProcess extends KafkaTopicProcess {
	private IHbaseLazySaveContainer lazySaveContainer;

	public KafkaStoreProcess() {
	}

	protected void beforePorcess(String raw) throws KafkaTopicProcessException {
	}

	@Override
	public void process(String raw) throws KafkaTopicProcessException,
			KafkaTransformException {
		beforePorcess(raw);
		KafkaTransformResult result = transform(raw);
		afterProcess(result);
		save(result);
	}

	public KafkaTransformResult transform(String raw)
			throws KafkaTopicProcessException, KafkaTransformException {
		KafkaTransformResult result = getTransformer().transform(getClazz(),
				raw, KafkaTransformHandler.EMPTY_HANDLER);
		return result;
	}

	protected void save(KafkaTransformResult result)
			throws KafkaTopicProcessException {
		try {
			for (HbaseEntity entity : result.get()) {
				getLazySaveContainer().lazySave(entity);
			}
		} catch (HbaseORMException e) {
			throw new KafkaTopicProcessException(e);
		}
	}

	protected void afterProcess(KafkaTransformResult result) {

	}

	public void setHbaseCtx(HbaseContext hbaseCtx) {
		super.setHbaseCtx(hbaseCtx);
		setLazySaveContainer(getHbaseCtx().getLazySaveContainerFactory()
				.createLazySaveContainer());
	}

	public IHbaseLazySaveContainer getLazySaveContainer() {
		return lazySaveContainer;
	}

	public void setLazySaveContainer(IHbaseLazySaveContainer lazySaveContainer) {
		this.lazySaveContainer = lazySaveContainer;
	}

	public void shutdown() {
		getLazySaveContainer().interrupt();
	}

}
