package com.master.jms.p2p;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

public class QueueReceiver {

    private static final Logger logger = Logger.getLogger(QueueReceiver.class);

    private static final String queueName = "TEST_QUEUE";
    private static final String brokerUrl = "tcp://master:61616";

    public static void main(String[] args) {
        new QueueReceiver().receive();
    }

    private void receive() {
        ConnectionFactory factory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageConsumer consumer;

        factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, brokerUrl);

        try {
            connection = factory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(queueName);
            consumer = session.createConsumer(destination);

            while (true) {
                TextMessage msg = (TextMessage) consumer.receive(10000);
                if(null != msg) {
                    logger.debug("收到消息：" + msg.getText());
                } else {
                    logger.debug("没有收到消息");
                    break;
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if(null != connection) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
