package kafka.javaapi.consumer;

import static scala.collection.JavaConversions.asList;
import static scala.collection.JavaConversions.asSet;
import static scala.collection.JavaConversions.mapAsScalaMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.I0Itec.zkclient.ZkClient;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.consumer.KafkaZookeeperConsumerConnector;
import kafka.consumer.TopicFilter;
import kafka.message.Message;
import kafka.serializer.Decoder;
import kafka.serializer.DefaultDecoder;

public class MyZookeeperConsumerConnector implements ConsumerConnector {
	private final KafkaZookeeperConsumerConnector underlying;

	public MyZookeeperConsumerConnector(ConsumerConfig config) {
		underlying = new KafkaZookeeperConsumerConnector(config);
	}

	@Override
	public <T> Map<String, List<KafkaStream<T>>> createMessageStreams(
			Map<String, Integer> topicCountMap, Decoder<T> decoder) {
		Map<String, Object> scalaTopicCountMap = new HashMap<String, Object>();
		scalaTopicCountMap.putAll(topicCountMap);
		scala.collection.Map<String, scala.collection.immutable.List<KafkaStream<T>>> scalaReturn = underlying
				.consume(mapAsScalaMap(scalaTopicCountMap), decoder);
		Map<String, List<KafkaStream<T>>> ret = new HashMap<String, List<KafkaStream<T>>>();

		for (String topic : asSet(scalaReturn.keySet())) {
			List<KafkaStream<T>> list = asList(scalaReturn.get(topic).get());
			ret.put(topic, list);
		}
		return ret;
	}

	@Override
	public Map<String, List<KafkaStream<Message>>> createMessageStreams(
			Map<String, Integer> topicCountMap) {
		return createMessageStreams(topicCountMap, new DefaultDecoder());
	}

	@Override
	public <T> List<KafkaStream<T>> createMessageStreamsByFilter(
			TopicFilter topicFilter, int numStreams, Decoder<T> decoder) {
		return null;
	}

	@Override
	public List<KafkaStream<Message>> createMessageStreamsByFilter(
			TopicFilter topicFilter, int numStreams) {
		return null;
	}

	@Override
	public List<KafkaStream<Message>> createMessageStreamsByFilter(
			TopicFilter topicFilter) {
		return null;
	}

	@Override
	public void commitOffsets() {
		underlying.commitOffsets();
	}

	@Override
	public void shutdown() {
		underlying.shutdown();
	}

	public void shutdownZkClient() {
		ZkClient zkClient = underlying.getZkClient();
		if (zkClient != null) {
			zkClient.close();
		}
	}

}
