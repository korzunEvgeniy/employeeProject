package com.mastery.java.task.service.impl;

import com.mastery.java.task.dao.EmployeeRepository;
import com.mastery.java.task.dao.entity.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.exception.EmployeeNotValidException;
import com.mastery.java.task.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        logger.trace("Getting all employees from DB");
        return employeeRepository.findAll();
    }

    @Override
    public Employee get(Long id) {
        logger.trace("Getting employee with id {} from DB", id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public Employee create(Employee newEmployee) {
        logger.trace("Creating new employee in DB");
        if (checkValidAge(newEmployee)) {
            throw new EmployeeNotValidException(newEmployee.getLastName());
        }
        return employeeRepository.save(newEmployee);
    }

    @Override
    public Employee update(Employee updatedEmployee) {
        logger.trace("Updating employee with id {} in DB", updatedEmployee.getId());
        Employee expected = get(updatedEmployee.getId());
        if (!Objects.equals(updatedEmployee.getId(), expected.getId())) {
            throw new EmployeeNotFoundException(updatedEmployee.getId());
        } else if (checkValidAge(updatedEmployee)) {
            throw new EmployeeNotValidException(updatedEmployee.getLastName());
        } else {
            return employeeRepository.save(updatedEmployee);
        }
    }

    @Override
    public void delete(Long id) {
        logger.trace("Deleting employee with id {} from DB", id);
        employeeRepository.deleteById(id);
    }

    public boolean checkValidAge(Employee employee) {
        logger.info("Checking input data for validity");
        LocalDate minDateOfBirth = LocalDate.now().minusYears(18);
        return minDateOfBirth.compareTo(LocalDate.parse(employee.getDateOfBirth())) <= 0;
    }
}