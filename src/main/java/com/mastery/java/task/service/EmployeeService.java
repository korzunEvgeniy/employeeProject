package com.mastery.java.task.service;

import com.mastery.java.task.service.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService extends Service<EmployeeDto, Long> {

    @Override
    EmployeeDto get(Long key);

    @Override
    List<EmployeeDto> getAll();

    @Override
    EmployeeDto create(EmployeeDto dto);

    @Override
    EmployeeDto update(EmployeeDto dto);

    @Override
    void delete(Long key);
}