package com.mastery.java.task.mqactive;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mastery.java.task.dao.entity.Employee;
import com.mastery.java.task.service.impl.EmployeeServiceImpl;
import com.mastery.java.task.util.LocalDateDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        return gson.fromJson(message, Employee.class);
    }
}
