package com.master.jms.pubsub;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

public class JmsTopicPublisher {

    private static final Logger logger = Logger.getLogger(JmsTopicPublisher.class);

    private TopicConnection connection;
    private TopicSession session;
    private TopicPublisher publisher;

    public static void main(String[] args) {
        JmsTopicPublisher jmsTopicPublisher = new JmsTopicPublisher("tcp://master:61616", "ToDear");
        for(int i=0; i< Integer.MAX_VALUE; i++) {
            jmsTopicPublisher.publish("This is pub and sub pattern");
        }
        jmsTopicPublisher.close();
    }

    public JmsTopicPublisher(String brokerUrl, String topicName) {
        TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, brokerUrl);
        try {
            this.connection = factory.createTopicConnection();
            this.session = (TopicSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(topicName);
            this.publisher = session.createPublisher(topic);
            connection.start();
            //session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void publish(String msg) {
        try {
            TextMessage txtMsg = session.createTextMessage();
            txtMsg.setText(msg);
            publisher.publish(txtMsg);
            logger.debug("published message: " + msg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if(null != connection) {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

}
