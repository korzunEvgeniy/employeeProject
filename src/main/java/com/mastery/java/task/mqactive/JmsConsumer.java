package com.mastery.java.task.mqactive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastery.java.task.dao.entity.Employee;
import com.mastery.java.task.service.impl.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    private static final Logger logger = LoggerFactory.getLogger(JmsConsumer.class);

    private final EmployeeServiceImpl employeeService;

    @Autowired
    public JmsConsumer(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @JmsListener(destination = "newEmployeesQueue")
    public void receiveNewEmployee(String message) {
        logger.debug("message with new employee received");
        Employee employee = parsingMessageToEmployee(message);
        employeeService.create(employee);
    }

    @JmsListener(destination = "updateEmployeesQueue")
    public void receiveUpdateEmployee(String message) {
        logger.debug("message with updated employee received");
        Employee employee = parsingMessageToEmployee(message);
        employeeService.update(employee);
    }

    private Employee parsingMessageToEmployee(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(message, Employee.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
