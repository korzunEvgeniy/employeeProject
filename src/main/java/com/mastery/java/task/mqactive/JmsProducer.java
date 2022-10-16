package com.mastery.java.task.mqactive;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mastery.java.task.dao.entity.Employee;
import com.mastery.java.task.util.LocalDateSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class JmsProducer {

    private static final Logger logger = LoggerFactory.getLogger(JmsProducer.class);

    @Value("newEmployeesQueue")
    private String newEmployeesQueue;
    @Value("updateEmployeesQueue")
    private String updateEmployeesQueue;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendNewEmployeeToQueue(Employee employee) throws JmsException {
        logger.debug(employee.toString());
        String json = parsingEmployeeToMessage(employee);
        logger.debug(json);
        jmsTemplate.convertAndSend(newEmployeesQueue, json);
    }

    public void sendUpdateEmployeeToQueue(Employee employee) throws JmsException {
        logger.debug(employee.toString());
        String json = parsingEmployeeToMessage(employee);
        logger.debug(json);
        jmsTemplate.convertAndSend(updateEmployeesQueue, json);
    }

    private String parsingEmployeeToMessage(Employee employee) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        Gson gson = gsonBuilder.create();
        return gson.toJson(employee);
    }
}
