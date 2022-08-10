package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeRepository;
import com.mastery.java.task.dao.entity.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeServiceImpl;
    private Long id1, id2, id3, notExistingId;
    private Employee e1, e2, e3;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.openMocks(this);
        employeeServiceImpl = new EmployeeServiceImpl(employeeRepository);
    }

    @BeforeEach
    public void init() {
        id1 = 1L;
        id2 = 2L;
        id3 = 3L;
        notExistingId = 4L;
        e1 = new Employee(id1, "Evgeniy", "Korzun",
                1, "developer", Employee.Gender.MALE, "1987-06-24");
        e2 = new Employee(id2, "Alex", "Safronov",
                1, "developer", Employee.Gender.MALE, "1977-12-12");
        e3 = new Employee(id3, "Anna", "Shine",
                1, "engineer", Employee.Gender.FEMALE, "2002-5-3");
    }

    @Test
    public void getAllEmployees() {
        List<Employee> listOfEmployee = new ArrayList<>();
        listOfEmployee.add(e1);
        listOfEmployee.add(e2);
        listOfEmployee.add(e3);

        when(employeeRepository.findAll()).thenReturn(listOfEmployee);
        List<Employee> actualList = employeeServiceImpl.getAll();

        assertEquals(listOfEmployee, actualList);
        assertEquals(3, actualList.size());
        assertNotEquals(1, listOfEmployee.size());
        verify(employeeRepository).findAll();
    }

    @Test
    public void getEmployeeById() {
        when(employeeRepository.findById(id1)).thenReturn(Optional.ofNullable(e1));

        assertEquals(employeeServiceImpl.get(id1), e1);
        verify(employeeRepository).findById(id1);
    }

    @Test
    public void getEmployeeByIdExpectedException() {
        when(employeeRepository.findById(notExistingId)).thenThrow(EmployeeNotFoundException.class);

        Throwable exception = new EmployeeNotFoundException(notExistingId);
        assertEquals("Employee with id " + notExistingId + " not found!", exception.getMessage());
    }

    @Test
    public void createNewEmployee() {
        when(employeeRepository.save(e2)).thenReturn(e2);

        assertEquals(employeeServiceImpl.create(e2), e2);
        verify(employeeRepository).save(e2);
    }

    @Test
    void updateEmployee() {
        Employee updatedE3 = new Employee(e3.getId(), e3.getFirstName(), e3.getLastName(),
                e3.getDepartmentId(), "architect", e3.getGender(), e3.getDateOfBirth());
        when(employeeRepository.save(updatedE3)).thenReturn(updatedE3);

        Employee updatedE3Actual = employeeServiceImpl.update(updatedE3);

        assertEquals(updatedE3Actual, updatedE3);
        assertNotEquals(updatedE3Actual.getJobTitle(), e3.getJobTitle());
        verify(employeeRepository).save(updatedE3);
    }

    @Test
    public void updateEmployeeExpectedException() {
        Employee updatedE3WithNotExistingId = new Employee(notExistingId, e3.getFirstName(), e3.getLastName(),
                e3.getDepartmentId(), "architect", e3.getGender(), e3.getDateOfBirth());
        when(employeeRepository.save(updatedE3WithNotExistingId)).thenThrow(EmployeeNotFoundException.class);

        Throwable exception = new EmployeeNotFoundException(notExistingId);
        assertEquals("Employee with id " + updatedE3WithNotExistingId.getId() + " not found!",
                exception.getMessage());
    }

    @Test
    public void deleteEmployeeById() {
        doNothing().
                when(employeeRepository).deleteById(e3.getId());
        employeeRepository.deleteById(e3.getId());
        verify(employeeRepository).deleteById(e3.getId());
    }

    @Test
    public void deleteEmployeeExpectedException() {
        doNothing().doThrow(EmployeeNotFoundException.class).
                when(employeeRepository).delete(e3);

        Throwable exception = new EmployeeNotFoundException(id3);
        assertEquals("Employee with id " + id3 + " not found!", exception.getMessage());
    }
}