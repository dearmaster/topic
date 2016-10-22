package com.master.jms.queue.consumer;

import org.apache.log4j.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class QueueMessageListener implements MessageListener {

    private static final Logger logger = Logger.getLogger(QueueMessageListener.class);

    @Override
    public void onMessage(Message message) {
        try {
            logger.info("收到消息: " + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
