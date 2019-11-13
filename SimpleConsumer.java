/*
 * @FC
 * 
 */

package helloproducer.klab;

import java.util.Arrays;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SimpleConsumer {

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println("supernico");
			return;
		}

		// Consumer configuration settings
		String topicName = args[0].toString();
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "supernico");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		@SuppressWarnings("resource")
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

		// Consumer subscribes list of topics here.
		consumer.subscribe(Arrays.asList(topicName));

		// print the topic name
		System.out.println("Subscribed to topic " + topicName);
		// var inutilizzata: int i = 0;

		while (true) {
			@SuppressWarnings("deprecation")
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records)

				// print the offset,key and value for the consumer records.
				System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
		}
	}
}