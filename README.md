# chat-server
Docker Commands


1) **Docker Start**: docker-compose up -d
2) **Docker Stop**: docker-compose down
3) **List Topic**: opt/kafka/bin/kafka-topics.sh --list --bootstrap-server localhost:9092
4) **Create Topic**: opt/kafka/bin/kafka-topics.sh --create --topic topic_name --bootstrap-server localhost:9092

/opt/kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic topic_name


/opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic topic_name --from-beginning