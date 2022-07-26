package com.mastery.java.task.service.impl;

import com.mastery.java.task.dao.EmployeeRepository;
import com.mastery.java.task.dao.entity.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.service.EmployeeMapper;
import com.mastery.java.task.service.EmployeeService;
import com.mastery.java.task.service.dto.EmployeeDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepositoryImpl;
    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);
    private static final EmployeeMapper MAPPER = new EmployeeMapper();

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepositoryImpl) {
        this.employeeRepositoryImpl = employeeRepositoryImpl;
    }

    @Override
    public List<EmployeeDto> getAll() {
        logger.debug("Start method getAll");
        Iterable<Employee> employees = employeeRepositoryImpl.getAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDto employeeDto = MAPPER.toDto(employee);
            employeeDtos.add(employeeDto);
        }
        return employeeDtos;
    }

    @Override
    public EmployeeDto get(Long employeeId) {
        logger.debug("Start method getById");
        try {
            return MAPPER.toDto(employeeRepositoryImpl.get(employeeId));
        } catch (Exception e) {
            throw new EmployeeNotFoundException(employeeId);
        }
    }

    @Override
    public EmployeeDto create(EmployeeDto newEmployeeDto) {
        logger.debug("Start method createBook");
//        Employee existing = employeeRepositoryImpl.get(newEmployeeDto.getEmployeeId());
//        if (existing != null) {
//            throw new RuntimeException("Employee with id " + newEmployeeDto.getEmployeeId() + " exist");
//        } else
            return MAPPER.toDto(employeeRepositoryImpl.create(MAPPER.toEntity(newEmployeeDto)));
    }

    @Override
    public EmployeeDto update(EmployeeDto updatedEmployeeDto) {
        logger.debug("Start method updateEmployee");
        try {
            return MAPPER.toDto(employeeRepositoryImpl.update(MAPPER.toEntity(updatedEmployeeDto)));
        } catch (Exception e) {
            throw new EmployeeNotFoundException(updatedEmployeeDto.getEmployeeId());
        }
    }

    @Override
    public void delete(Long employeeId) {
        try {
            employeeRepositoryImpl.delete(employeeId);
        } catch (Exception e) {
            throw new EmployeeNotFoundException(employeeId);
        }
    }
}
