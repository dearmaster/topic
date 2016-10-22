package com.master.jms.pubsub;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

public class JmsTopicSubscriber {

    private static final Logger logger = Logger.getLogger(JmsTopicSubscriber.class);

    private TopicConnection connection;
    private TopicSession session;
    private TopicSubscriber subscriber;

    public JmsTopicSubscriber(String brokerUrl, String topicName) {
        TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, brokerUrl);
        try {
            this.connection = factory.createTopicConnection();
            this.session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(topicName);
            this.subscriber = session.createSubscriber(topic, null, true);
            this.subscriber.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    try {
                        logger.debug("Subscribed message: " + ((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            connection.start();
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

    public static void main(String[] args) {
        JmsTopicSubscriber jmsSubscriber = new JmsTopicSubscriber("tcp://master:61616", "ToDear");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jmsSubscriber.close();
    }

}
