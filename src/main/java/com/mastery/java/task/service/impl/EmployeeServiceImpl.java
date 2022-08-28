package com.mastery.java.task.service.impl;

import com.mastery.java.task.dao.EmployeeRepository;
import com.mastery.java.task.dao.entity.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAll() {
        logger.info("Getting all employees from DB");
        return employeeRepository.findAll();
    }

    @Override
    public Employee get(Long id) {
        logger.info("Getting employee with id {} from DB", id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public Employee create(Employee newEmployee) {
        logger.info("Creating new employee in DB");
        return employeeRepository.save(newEmployee);
    }

    @Override
    public Employee update(Employee updatedEmployee) {
        logger.info("Updating employee with id {} in DB", updatedEmployee.getId());
        Employee expected = get(updatedEmployee.getId());
        if (!Objects.equals(updatedEmployee.getId(), expected.getId())) {
            throw new EmployeeNotFoundException(updatedEmployee.getId());
        } else {
            return employeeRepository.save(updatedEmployee);
        }
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleting employee with id {} from DB", id);
        employeeRepository.deleteById(id);
    }
}