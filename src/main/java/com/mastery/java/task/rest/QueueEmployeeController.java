package com.mastery.java.task.rest;

import com.mastery.java.task.dao.entity.Employee;
import com.mastery.java.task.mqactive.JmsProducer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/queue/employees")
@Tag(name = "Controller for Employees using ActiveMQ")
public class QueueEmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final JmsProducer jmsProducer;

    @Autowired
    public QueueEmployeeController(JmsProducer jmsProducer) {
        this.jmsProducer = jmsProducer;
    }

    @Operation(summary = "Create new employee", description = "Save new employee in DB",
            responses = @ApiResponse(responseCode = "201", description = "Successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class))))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewEmployee(@Valid @RequestBody Employee newEmployee) {
        logger.info("Post-request to creating new entity: " + newEmployee);
        jmsProducer.sendNewEmployeeToQueue(newEmployee);
    }

    @Operation(summary = "Update employee by id", description = "Updating existed employee as per id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "404", description = "Not found - employee is not found")
            })
    @PutMapping
    public void updateEmployee(@Valid @RequestBody Employee updateEmployee) {
        logger.info("Put-request to updating {}", updateEmployee);
        jmsProducer.sendUpdateEmployeeToQueue(updateEmployee);
    }
}
