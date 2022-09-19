package com.mastery.java.task.mqactive;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class JmsConsumer {

    @Value("${spring.activemq.queue}")
    String queue;

    @JmsListener(destination = "${spring.activemq.queue}")
    @SendTo("myQueue2")
    public String receiveAndForwardMessageFromQueue(final Message jsonMessage) {
        String messageData = null;
        System.out.println("Received message " + jsonMessage);
        if (jsonMessage instanceof TextMessage textMessage) {
            try {
                messageData = textMessage.getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            System.out.println("messageData: " + messageData);
        }
        return messageData;
    }
}
