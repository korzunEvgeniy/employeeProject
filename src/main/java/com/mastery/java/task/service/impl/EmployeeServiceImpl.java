package com.mastery.java.task.service.impl;

import com.mastery.java.task.dao.EmployeeRepository;
import com.mastery.java.task.dao.entity.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

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
        logger.info("Start method getAll");
        return employeeRepository.findAll();
    }

    @Override
    public Employee get(Long id) {
        logger.info("Start method getById");
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public Employee create(Employee newEmployee) {
        logger.info("Start method createEmployee");
        return employeeRepository.save(newEmployee);
    }

    @Override
    public Employee update(Employee updatedEmployee) {
        logger.info("Start method updateEmployee");
        return employeeRepository.save(updatedEmployee);
    }

    @Override
    public void delete(Long id) {
        logger.info("Start method deleteEmployee");
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }
}