package com.example.demo;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerDemo {
    // Topic
    private static final String topic = "testTopic";

    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29092");
        // Установить метод сериализации ключа и значения
        props.put("acks", "0");
        props.put("group.id", "1111");
        props.put("retries", "0");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // экземпляр производителя
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        int i = 1;

        // отправить сообщение
        // Чтение файла, чтение базы данных памяти, чтение порта сокета
        while (i < 10) {
            producer.send(new ProducerRecord<String, String>(topic,  "value:" + i));
            producer.flush();
            System.out.println("key:" + i + " " + "value:" + i);
            i++;
        }
    }
}