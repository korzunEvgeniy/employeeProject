package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("getAll")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("getOne{employeeId}")
    public Employee getEmployeeById(@PathVariable Long employeeId) throws EmployeeNotFoundException {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("createEmployee")
    public Employee createNewEmployee(@RequestBody Employee newEmployee) {
        return employeeService.createNewEmployee(newEmployee);
    }

    @PutMapping("update{employeeId}")
    public Employee updateEmployee(@PathVariable Long employeeId,
    @RequestBody Employee updateEmployee) throws EmployeeNotFoundException{
        return employeeService.updateEmployee(employeeId, updateEmployee);
    }

    @DeleteMapping("delete{employeeId}")
    public void deleteEmployeeById(@PathVariable Long employeeId) throws EmployeeNotFoundException{
        employeeService.deleteEmployeeById(employeeId);
    }
}