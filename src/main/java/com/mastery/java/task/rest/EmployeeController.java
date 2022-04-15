package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/")
public class EmployeeController {

    private final EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    public EmployeeController(EmployeeServiceImpl service) {
        this.employeeServiceImpl = service;
    }

    @GetMapping("getAll")
    public List<Employee> getAllEmployees() {
        return employeeServiceImpl.getAllEmployees();
    }

    @GetMapping("getOne/{employeeId}")
    public Employee getEmployeeById(@PathVariable Long employeeId) throws EmployeeNotFoundException {
        try {
            return employeeServiceImpl.getEmployeeById(employeeId);
        } catch (EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("createEmployee")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createNewEmployee(@RequestBody Employee newEmployee) {
        return employeeServiceImpl.createNewEmployee(newEmployee);
    }

    @PutMapping("update/{employeeId}")
    public Employee updateEmployee(@PathVariable Long employeeId,
    @RequestBody Employee updateEmployee) throws EmployeeNotFoundException{
        try {
            return employeeServiceImpl.updateEmployee(employeeId, updateEmployee);
        } catch(EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("delete/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable Long employeeId) throws EmployeeNotFoundException{
        try {
            employeeServiceImpl.deleteEmployeeById(employeeId);
        } catch(EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}