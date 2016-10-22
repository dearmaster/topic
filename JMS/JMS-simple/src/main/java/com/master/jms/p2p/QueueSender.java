package com.master.jms.p2p;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class QueueSender {

    private static final Logger logger = Logger.getLogger(QueueSender.class);

    private Connection connection;
    private Session session;
    private MessageProducer producer;

    public static void main(String[] args) {
//        sendBySingleThread();
        sendByMultiThread();
    }

    public static void sendBySingleThread() {
        int MAX_MESSAGE_COUNT = 20000;
        java.util.Queue<String> queue = new ArrayBlockingQueue(MAX_MESSAGE_COUNT);
        for(int i=1; i<=MAX_MESSAGE_COUNT; i++) {
            String msg = "Message " + i;
            queue.add(msg);
        }

        long startTime = System.currentTimeMillis();
        QueueSender queueSender = new QueueSender("tcp://master:61616", "TEST_QUEUE");
        try {
            String msg;
            while((msg = queue.poll()) != null) {
                queueSender.send(msg);
            }
            queueSender.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time needed for single thread: " + (endTime - startTime)/1000 + " seconds"); //32 second for 2w records
    }

    /**
     * if send the message by multi thread, the sequence of the messages
     * that sent to the queue will not be a certain one
     */
    public static void sendByMultiThread() {
        int MAX_MESSAGE_COUNT = 20000;
        java.util.Queue<String> queue = new ArrayBlockingQueue(MAX_MESSAGE_COUNT);
        for(int i=1; i<=MAX_MESSAGE_COUNT; i++) {
            String msg = "Message " + i;
            queue.add(msg);
        }

        long startTime = System.currentTimeMillis();

        int THREAD_NUMBER = 50; //9 seconds if 50 threads execute at the same time
        List<Future<String>> retList = new ArrayList<>();
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i=1; i<THREAD_NUMBER; i++) {
            QueueSender sender = new QueueSender("tcp://master:61616", "TEST_QUEUE");
            Future<String> future = service.submit(new MessageTask(sender, queue));
            retList.add(future);
        }
        service.shutdown();
        for(Future<String> future : retList) {
            try {
                logger.debug("Thread [" + future.get() + "] finished");
            } catch (InterruptedException|ExecutionException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time need for multi thread: " + (endTime - startTime) / 1000 + " seconds");
    }

    public QueueSender(String brokerUrl, String queueName) {
        ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, brokerUrl);
        try {
//            connection = factory.createConnection();
            connection = factory.createConnection("future", "bright");
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void send(String msg) throws JMSException {
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

class MessageTask implements Callable<String> {

    private static final Logger logger = Logger.getLogger(MessageTask.class);

    private QueueSender sender;
    private java.util.Queue<String> queue;

    public MessageTask(QueueSender sender, java.util.Queue<String> queue) {
        this.sender = sender;
        this.queue = queue;
    }

    public String call() throws Exception {
        logger.debug("Thread[" + Thread.currentThread().getName() + "]: starts");
        String msg;
        while((msg = queue.poll()) != null) {
            sender.send(msg);
            logger.debug("Thread [" + Thread.currentThread().getName() + "] sent message: " + msg);
        }
        sender.close();
        return Thread.currentThread().getName();
    }

}