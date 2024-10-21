create topic kaka container

docker exec -it kafka kafka-topics --create --topic record-job --bootstrap-server localhost:29092 --replication-factor 1 --partitions 5

docker exec -it kafka kafka-topics --create --topic purchase_order --bootstrap-server localhost:29092 --replication-factor 1 --partitions 5

docker exec -it kafka kafka-topics --create --topic rfp_approval --bootstrap-server localhost:29092 --replication-factor 1 --partitions 5

consume topic
docker exec consumer kafkacat -b kafka:9092 -C -t record-job

docker exec consumer kafkacat -b kafka:9092 -C -t purchase_order

docker exec consumer kafkacat -b kafka:9092 -C -t rfp_approval



docker exec kafka kafka-topics --list --bootstrap-server localhost:9092

docker exec kafka bash -c "kafka-topics --list --bootstrap-server localhost:9092 | grep -v __consumer_offsets | xargs -n 1 kafka-topics --bootstrap-server localhost:9092 --delete --purchase_order"
