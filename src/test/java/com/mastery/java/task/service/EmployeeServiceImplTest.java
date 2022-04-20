package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDaoImpl;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeServiceImpl;

    List<Employee> listOfEmployees = new ArrayList<>();
    Long id1 = 1L;
    Long id2 = 2L;
    Long id3 = 3L;
    Employee e1 = new Employee(id1, "Evgeniy", "Korzun",
            220, "developer", Gender.MALE, "1987-06-24");
    Employee e2 = new Employee(id2, "Alex", "Safronov",
            221, "developer", Gender.MALE, "1977-12-12");
    Employee e3 = new Employee(id3, "Anna", "Shine",
            222, "engineer", Gender.FEMALE, "2002-5-3");

    @Mock
    private EmployeeDaoImpl employeeDaoImpl;

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.openMocks(this);
        employeeServiceImpl = new EmployeeServiceImpl(employeeDaoImpl);
    }

    @Test
    public void getAllEmployees() {
        listOfEmployees.add(e1);
        listOfEmployees.add(e2);
        listOfEmployees.add(e3);

        when(employeeDaoImpl.getAllEmployees()).thenReturn(listOfEmployees);
        List<Employee> actualList = employeeServiceImpl.getAllEmployees();

        assertEquals(listOfEmployees, actualList);
        assertEquals(3, actualList.size());
        assertNotEquals(1, listOfEmployees.size());
        verify(employeeDaoImpl).getAllEmployees();
    }

    @Test
    public void getEmployeeById() throws EmployeeNotFoundException {
        when(employeeDaoImpl.getEmployeeById(id1)).thenReturn(e1);

        assertEquals(employeeServiceImpl.getEmployeeById(id1), e1);
        assertNotEquals(employeeServiceImpl.getEmployeeById(id2), e1);
        verify(employeeDaoImpl).getEmployeeById(id1);
    }

    @Test
    public void getEmployeeByIdExpectedException() throws EmployeeNotFoundException {
        Long id4 = 4L;
        when(employeeDaoImpl.getEmployeeById(id4)).thenThrow(EmployeeNotFoundException.class);

        Throwable exception;
        exception = assertThrows(EmployeeNotFoundException.class,
                () -> employeeServiceImpl.getEmployeeById(id4));
        assertEquals("Employee with id " + id4 + " not found!", exception.getMessage());
        verify(employeeDaoImpl).getEmployeeById(id4);
    }

    @Test
    public void createNewEmployee() {
        when(employeeDaoImpl.createNewEmployee(e2)).thenReturn(e2);

        assertEquals(employeeServiceImpl.createNewEmployee(e2), e2);
        verify(employeeDaoImpl).createNewEmployee(e2);
    }

    @Test
    void updateEmployee() throws EmployeeNotFoundException {
        when(employeeDaoImpl.updateEmployee(id2, e3)).thenReturn(e3);

        assertEquals(employeeServiceImpl.updateEmployee(id2, e3), e3);
        verify(employeeDaoImpl).updateEmployee(id2, e3);
    }

    @Test
    public void updateEmployeeExpectedException() throws EmployeeNotFoundException{
        Long id5 = 5L;
        when(employeeDaoImpl.updateEmployee(id5, e3)).thenThrow(EmployeeNotFoundException.class);

        Throwable exception;
        exception = assertThrows(EmployeeNotFoundException.class,
                () -> employeeServiceImpl.updateEmployee(id5, e3));
        assertEquals("Employee with id " + id5 + " not found!", exception.getMessage());
        verify(employeeDaoImpl).updateEmployee(id5, e3);
    }

    @Test
    public void deleteEmployeeById() throws EmployeeNotFoundException {
        doNothing().
                when(employeeDaoImpl).deleteEmployeeById(id3);
        employeeServiceImpl.deleteEmployeeById(id3);
        verify(employeeDaoImpl).deleteEmployeeById(id3);
    }

    @Test
    public void deleteEmployeeExpectedException() throws EmployeeNotFoundException {
        long id6 = 6L;
        doNothing().doThrow(EmployeeNotFoundException.class).
                when(employeeDaoImpl).deleteEmployeeById(id6);
        employeeServiceImpl.deleteEmployeeById(id6);

        Throwable exception;
        exception = assertThrows(EmployeeNotFoundException.class,
                () -> employeeServiceImpl.deleteEmployeeById(id6));
        assertEquals("Employee with id " + id6 + " not found!", exception.getMessage());
        verify(employeeDaoImpl, times(2)).deleteEmployeeById(id6);
   }
}