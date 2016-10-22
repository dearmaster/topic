package com.master.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

public class Sender {

    private static final Logger logger = Logger.getLogger(Sender.class);

    private static final String queueName = "TEST_QUEUE";
    private static final String brokerUrl = "tcp://master:61616";

    public static void main(String[] args) {
        new Sender().send("Hello World");
    }

    private void send(String msg) {
        ConnectionFactory factory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageProducer producer;

        factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, brokerUrl);

        try {
            connection = factory.createConnection();
            connection.start();

            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(queueName);

            producer = session.createProducer(destination);
            //set the delivery mode to be persistent to ensure the message won't be lost when the active mq server shutdown
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            sendMessage(session, producer, msg);
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendMessage(Session session, MessageProducer producer, String msg) throws JMSException {
        TextMessage textMsg = session.createTextMessage(msg);
        logger.debug("发送消息：" + msg);
        producer.send(textMsg);
    }

}
