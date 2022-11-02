package com.mastery.java.task.mqactive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastery.java.task.dao.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {

    private static final Logger logger = LoggerFactory.getLogger(JmsProducer.class);
    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendNewEmployeeToQueue(Employee employee) throws JmsException {
        logger.debug("message with new employee sent");
        String json = parsingEmployeeToMessage(employee);
        String newEmployeesQueue = "newEmployeesQueue";
        jmsTemplate.convertAndSend(newEmployeesQueue, json);
    }

    public void sendUpdateEmployeeToQueue(Employee employee) throws JmsException {
        logger.debug("message with updated employee sent");
        String json = parsingEmployeeToMessage(employee);
        String updateEmployeesQueue = "updateEmployeesQueue";
        jmsTemplate.convertAndSend(updateEmployeesQueue, json);
    }

    private String parsingEmployeeToMessage(Employee employee) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(employee);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
