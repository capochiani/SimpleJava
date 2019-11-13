/*
 * @FC
 * 
 */

package helloproducer.klab;

//import util.properties packages
import java.util.Properties;
//import simple producer packages
import org.apache.kafka.clients.producer.Producer;
//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;
//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {

	public static void main(String[] args) throws Exception {
		// Check arguments length value
		String topicName = "";
		if (args.length == 0) {
			System.out.println("==== CIAO NICOLA! ====");
			System.out.println("Enter topic name");
			topicName = "supernico";
		} else {
			// Assegna topicName to string
			topicName = args[0].toString();
		}

		// Crea instance
		Properties props = new Properties();

		// Assign localhost id
		props.put("bootstrap.servers", "localhost:9092");

		// Set ack for producer.
		props.put("acks", "all");

		// If the request fails, retry
		props.put("retries", 0);

		// Specify buffer size in config
		props.put("batch.size", 16384);

		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		for (int i = 0; i < 10; i++)
			producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(i), Integer.toString(i)));

		System.out.println("Message sent successfully");
		producer.close();
	}
}