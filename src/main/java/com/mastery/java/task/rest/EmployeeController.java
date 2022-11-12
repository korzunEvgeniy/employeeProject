package com.mastery.java.task.rest;

import com.mastery.java.task.dao.entity.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(name = "Controller for Employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeServiceImpl;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.employeeServiceImpl = service;
    }

    @Operation(summary = "Get all employees", description = "Returns list of employees from DB",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "404", description = "Not found - employees are not found")
            })
    @GetMapping
    public List<Employee> getAllEmployees() {
        logger.info("Get-request to receive all employees");
        return employeeServiceImpl.getAll();
    }

    @Operation(summary = "Get employee by id", description = "Returns employee as per id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "404", description = "Not found - employee is not found")
            })
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        logger.info("Get-request to receive employee with id {}", id);
        return employeeServiceImpl.get(id);
    }

    @Operation(summary = "Create new employee", description = "Save new employee in DB",
            responses = @ApiResponse(responseCode = "201", description = "Successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class))))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createNewEmployee(@Valid @RequestBody Employee newEmployee) {
        logger.info("Post-request to creating new entity: " + newEmployee);
        return employeeServiceImpl.create(newEmployee);
    }

    @Operation(summary = "Update employee by id", description = "Updating existed employee as per id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "404", description = "Not found - employee is not found")
            })
    @PutMapping
    public Employee updateEmployee(@Valid @RequestBody Employee updateEmployee) {
        logger.info("Put-request to updating {}", updateEmployee);
        return employeeServiceImpl.update(updateEmployee);
    }

    @Operation(summary = "Delete employee by id", description = "Delete record from DB",
            responses = @ApiResponse(responseCode = "204", description = "Successfully deleted"))
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable Long id) {
        logger.info("Delete-request to deleting employee with id {}", id);
        employeeServiceImpl.delete(id);
    }
}
