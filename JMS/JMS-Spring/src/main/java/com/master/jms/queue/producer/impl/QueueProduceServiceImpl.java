package com.master.jms.queue.producer.impl;

import com.master.jms.queue.producer.QueueProduceService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class QueueProduceServiceImpl implements QueueProduceService {

    private JmsTemplate jmsTemplate;
    private Destination destination;

    @Override
    public void send(String msg) {
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

}
