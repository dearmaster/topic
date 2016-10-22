package com.master.jms.p2p;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

public class QueueSender {

    private static final Logger logger = Logger.getLogger(QueueSender.class);

    private Connection connection;
    private Session session;
    private MessageProducer producer;

    public static void main(String[] args) {
        QueueSender queueSender = new QueueSender("tcp://master:61616", "TEST_QUEUE");
        try {
            queueSender.send("This message is send by jms queue sender");
            queueSender.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public QueueSender(String brokerUrl, String queueName) {
        ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, brokerUrl);
        try {
            connection = factory.createConnection();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void send(String msg) throws JMSException {
        TextMessage textMsg = session.createTextMessage(msg);
        producer.send(textMsg);
        session.commit();
        logger.debug("Send message to Queue: " + msg);
    }

    public void close() throws JMSException {
        if(null != connection) {
            connection.close();
        }
    }

}
