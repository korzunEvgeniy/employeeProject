package com.mastery.java.task.service;

import com.mastery.java.task.service.dto.EmployeeDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService extends Service<EmployeeDto, Long> {

    @Override
    EmployeeDto get(Long key);

    @Override
    List<EmployeeDto> getAll(Pageable pageable);

    @Override
    EmployeeDto create(EmployeeDto dto);

    @Override
    EmployeeDto update(EmployeeDto dto);

    @Override
    void delete(Long key);

    @Override
    Long countAll();
}