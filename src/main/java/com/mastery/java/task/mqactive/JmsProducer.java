package com.mastery.java.task.mqactive;

import com.mastery.java.task.dao.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {

    @Value("${spring.activemq.queue}")
    private String queue;
    private static final Logger logger = LoggerFactory.getLogger(JmsProducer.class);
    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendToQueue(Employee employee) {
        try {
            jmsTemplate.convertAndSend(queue, employee);
        } catch (Exception ex) {
            logger.error("Message was not sent to queue: " + queue, ex);
        }
    }
}
