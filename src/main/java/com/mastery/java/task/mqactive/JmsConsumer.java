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
    public Employee receive(String message) {

        logger.debug("received: " + message);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        Employee employee = gson.fromJson(message, Employee.class);

        logger.debug(employee.toString());

        return employeeService.create(employee);
    }
}
