package com.mastery.java.task.rest;

import com.mastery.java.task.dao.entity.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeServiceImpl;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.employeeServiceImpl = service;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        logger.info("Start method getAllEmployees");
        return employeeServiceImpl.getAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        logger.info("Start method getAllEmployees {}", id);
        return employeeServiceImpl.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createNewEmployee(@RequestBody Employee newEmployee) {
        logger.info("Start method createNewEmployee");
        return employeeServiceImpl.create(newEmployee);
    }

    @PutMapping
    public Employee updateEmployee(@RequestBody Employee updateEmployee) {
        logger.info("Start method updateEmployee {}", updateEmployee.getId());
        return employeeServiceImpl.update(updateEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable Long id) {
        logger.info("Start method deleteEmployeeById {}", id);
        employeeServiceImpl.delete(id);
    }
}