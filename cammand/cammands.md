confluent 7.6
Install kafka confluent manually
/Volumes/Development/Development/alucor-it/softwares/confluent-7.6.0/bin
sumit@192 bin % ./confluent-hub install confluentinc/kafka-connect-datagen:0.1.0
Do you want to install this into /Volumes/Development/Development/alucor-it/softwares/confluent-7.6.0/share/confluent-hub-components? (yN) y
I agree to the software license agreement (yN) y
Do you want to update all detected configs? (yN) y
Adding installation directory to plugin path in the following files:
/Volumes/Development/Development/alucor-it/softwares/confluent-7.6.0/etc/kafka/connect-distributed.properties
/Volumes/Development/Development/alucor-it/softwares/confluent-7.6.0/etc/kafka/connect-standalone.properties
/Volumes/Development/Development/alucor-it/softwares/confluent-7.6.0/etc/schema-registry/connect-avro-distributed.properties
/Volumes/Development/Development/alucor-it/softwares/confluent-7.6.0/etc/schema-registry/connect-avro-standalone.properties

Set environment variable
sumit@192 bin % nano ~/.bash_profile
export CONFLUENT_HOME=/Volumes/Development/Development/alucor-it/softwares/confluent-7.6.0
export PATH=$PATH:$JBOSS_HOME/bin:$JAVA_HOME/bin:$MAVEN_HOME/bin:$ANT_HOME/bin:$CONFLUENT_HOME/bin:$ES_HOME/bin:$MAIL_CONFIG/bin

Start kafka confluent
sumit@192 bin % ./confluent local services start

The local commands are intended for a single-node development environment only, NOT for production usage. See more: https://docs.confluent.io/current/cli/index.html
As of Confluent Platform 8.0, Java 8 will no longer be supported.

Using CONFLUENT_CURRENT: /var/folders/7p/l3tb_1lx3d336fg_qynx35r80000gn/T/confluent.QaChIfmm
Starting ZooKeeper
ZooKeeper is [UP]
Starting Kafka
Kafka is [UP]
Starting Schema Registry
Schema Registry is [UP]
Starting Kafka REST
Kafka REST is [UP]
Starting Connect
Connect is [UP]
Starting ksqlDB Server
ksqlDB Server is [UP]
Starting Control Center
Control Center is [UP]
sumit@192 bin %

Stop kafka confluent
sumit@192 bin % ./confluent local services stop

The local commands are intended for a single-node development environment only, NOT for production usage. See more: https://docs.confluent.io/current/cli/index.html
As of Confluent Platform 8.0, Java 8 will no longer be supported.

Using CONFLUENT_CURRENT: /var/folders/7p/l3tb_1lx3d336fg_qynx35r80000gn/T/confluent.QaChIfmm
Stopping Control Center
Control Center is [DOWN]
Stopping ksqlDB Server
ksqlDB Server is [DOWN]
Stopping Connect
Connect is [DOWN]
Stopping Kafka REST
Kafka REST is [DOWN]
Stopping Schema Registry
Schema Registry is [DOWN]
Stopping Kafka
Kafka is [DOWN]
Stopping ZooKeeper
ZooKeeper is [DOWN]
sumit@192 bin %

To Connect REST API
Add the following configuration to your Connect worker properties file

Step 1:
Update /Volumes/Development/Development/alucor-it/softwares/confluent-7.6.0/etc/kafka/connect-distributed.propertes by adding

rest.extension.classes=org.apache.kafka.connect.rest.basic.auth.extension.BasicAuthSecurityRestExtension

Step 2: Create a JAAS configuration file. Your authentication realm is hardcoded to KafkaConnect, so your JAAS must look like this:
Create /Volumes/Development/Development/alucor-it/softwares/confluent-7.6.0/etc/kafka/jaas-config.config
Add
KafkaConnect {
org.apache.kafka.connect.rest.basic.auth.extension.PropertyFileLoginModule required
file="${CONFLUENT_HOME}/etc/kafka/connect-password";
};

Step 3: Export KAFKA_OPTS with the path to the JAAS configuration file:

export KAFKA_OPTS="-Djava.security.auth.login.config=/Volumes/Development/Development/alucor-it/softwares/confluent-7.6.0/etc/kafka/jaas-config.config"

Step 4: Create a password properties file (CONFLUENT_HOME/etc/kafka/connect.password).
/Volumes/Development/Development/alucor-it/softwares/confluent-7.6.0/etc/kafka/connect.password
admin=admin

Create topic
sumit@192 bin % confluent kafka topic create purchase_order --url http://localhost:8090/kafka --partitions=5

No session token found, please enter user credentials. To avoid being prompted, run `confluent login`.
Username: admin
Password: **\***
Created topic "purchase_order".

List topics
sumit@192 bin % confluent kafka topic list --url http://localhost:8090/kafka

No session token found, please enter user credentials. To avoid being prompted, run `confluent login`.
Username: admin
Password: **\***
Name | Internal | Replication Factor | Partition Count  
------------------------------------------------------------------------------------------------+----------+--------------------+------------------
purchase_order | false | 1 | 5  
 rfp_approval | false | 1 | 5
