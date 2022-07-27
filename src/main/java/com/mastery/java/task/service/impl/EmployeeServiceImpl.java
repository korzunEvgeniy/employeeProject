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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDto> getAll(Pageable pageable) {
        logger.debug("Start method getAll");
        return employeeRepository.findAll(pageable)
                .stream().filter((e) -> !e.isDeleted())
                .map(employeeMapper::toDto)
                .toList();
    }

    @Override
    public EmployeeDto get(Long employeeId) {
        logger.debug("Start method getById");
        Employee employee = employeeRepository.findById(employeeId).filter((e) -> !e.isDeleted())
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
            return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto create(EmployeeDto newEmployeeDto) {
        logger.debug("Start method createEmployee");
        return employeeMapper.toDto(employeeRepository.save(employeeMapper.toEntity(newEmployeeDto)));
    }

    @Override
    public EmployeeDto update(EmployeeDto updatedEmployeeDto) {
        logger.debug("Start method updateEmployee");
        Employee employee = employeeRepository.findById(updatedEmployeeDto.getEmployeeId())
                .filter((e) -> !e.isDeleted())
                .orElseThrow(() -> new EmployeeNotFoundException(updatedEmployeeDto.getEmployeeId()));
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public void delete(Long employeeId) {
        logger.debug("Start method deleteEmployee");
        Employee employee = employeeRepository.findById(employeeId)
                .filter((e) -> !e.isDeleted())
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        if (!employee.isDeleted()) {
            employee.setDeleted(true);
            employeeRepository.save(employee);
        }
    }

    @Override
    public Long countAll() {
        logger.debug("Start method countEmployee");
        return employeeRepository.count();
    }
}
