package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("getOne{employeeId}")
    public Employee getEmployeeById(@PathVariable Long employeeId) throws EmployeeNotFoundException {
        return employeeServiceImpl.getEmployeeById(employeeId);
    }

    @PostMapping("createEmployee")
    public Employee createNewEmployee(@RequestBody Employee newEmployee) {
        return employeeServiceImpl.createNewEmployee(newEmployee);
    }

    @PutMapping("update{employeeId}")
    public Employee updateEmployee(@PathVariable Long employeeId,
    @RequestBody Employee updateEmployee) throws EmployeeNotFoundException{
        return employeeServiceImpl.updateEmployee(employeeId, updateEmployee);
    }

    @DeleteMapping("delete{employeeId}")
    public void deleteEmployeeById(@PathVariable Long employeeId) throws EmployeeNotFoundException{
        employeeServiceImpl.deleteEmployeeById(employeeId);
    }
}