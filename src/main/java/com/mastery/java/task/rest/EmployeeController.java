package com.mastery.java.task.rest;

import com.mastery.java.task.service.dto.EmployeeDto;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public List<EmployeeDto> getAllEmployees(Pageable pageable) {
        return employeeServiceImpl.getAll(pageable);
    }

    @GetMapping("/{employeeId}")
    public EmployeeDto getEmployeeById(@PathVariable Long employeeId) {
        try {
            return employeeServiceImpl.get(employeeId);
        } catch (EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createNewEmployee(@RequestBody EmployeeDto newEmployeeDto) {
        return employeeServiceImpl.create(newEmployeeDto);
    }

    @PutMapping
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto updateEmployeeDto) {
        try {
            return employeeServiceImpl.update(updateEmployeeDto);
        } catch(EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable Long employeeId) {
        try {
            employeeServiceImpl.delete(employeeId);
        } catch(EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}