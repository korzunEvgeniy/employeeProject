package com.mastery.java.task.rest;

import com.mastery.java.task.dao.entity.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeServiceImpl;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.employeeServiceImpl = service;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeServiceImpl.getAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeServiceImpl.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createNewEmployee(@RequestBody Employee newEmployee) {
        return employeeServiceImpl.create(newEmployee);
    }

    @PutMapping
    public Employee updateEmployee(@RequestBody Employee updateEmployee) {
        return employeeServiceImpl.update(updateEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable Long id) {
        employeeServiceImpl.delete(id);
    }
}