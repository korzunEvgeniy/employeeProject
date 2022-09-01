package com.mastery.java.task.rest;

import com.mastery.java.task.dao.entity.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
@Api(tags = "Controller for Employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeServiceImpl;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.employeeServiceImpl = service;
    }

    @ApiOperation(value = "Get all employees", notes = "Returns list of employees from DB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - employees are not found")
    })
    @GetMapping
    public List<Employee> getAllEmployees() {
        logger.info("Get-request to receive all employees");
        return employeeServiceImpl.getAll();
    }

    @ApiOperation(value = "Get employee by id", notes = "Returns employee as per id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - employee is not found")
    })
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        logger.info("Get-request to receive employee with id {}", id);
        return employeeServiceImpl.get(id);
    }

    @ApiOperation(value = "Create new employee", notes = "Save new employee in DB")
    @ApiResponses(value = @ApiResponse(code = 201, message = "Successfully created"))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createNewEmployee(@Valid @RequestBody Employee newEmployee) {
        logger.info("Post-request to creating new entity: " + newEmployee);
        return employeeServiceImpl.create(newEmployee);
    }

    @ApiOperation(value = "Update employee by id", notes = "Updating existed employee as per id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Not found - employee is not found")
    })
    @PutMapping
    public Employee updateEmployee(@Valid @RequestBody Employee updateEmployee) {
        logger.info("Put-request to updating {}", updateEmployee);
        return employeeServiceImpl.update(updateEmployee);
    }

    @ApiOperation(value = "Delete employee by id", notes = "Delete record from DB")
    @ApiResponses(value = @ApiResponse(code = 204, message = "Successfully deleted"))
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable Long id) {
        logger.info("Delete-request to deleting employee with id {}", id);
        employeeServiceImpl.delete(id);
    }
}