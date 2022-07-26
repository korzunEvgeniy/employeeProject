//package com.mastery.java.task.service;
//
//import com.mastery.java.task.dao.EmployeeDaoImpl;
//import com.mastery.java.task.service.dto.EmployeeDto;
//import com.mastery.java.task.exception.EmployeeNotFoundException;
//import com.mastery.java.task.service.impl.EmployeeServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class EmployeeDtoServiceImplTest {
//
//    private EmployeeServiceImpl employeeServiceImpl;
//
//    List<EmployeeDto> listOfEmployeeDtos = new ArrayList<>();
//    Long id1 = 1L;
//    Long id2 = 2L;
//    Long id3 = 3L;
//    EmployeeDto e1 = new EmployeeDto(id1, "Evgeniy", "Korzun",
//            220, "developer", EmployeeDto.Gender.MALE, "1987-06-24");
//    EmployeeDto e2 = new EmployeeDto(id2, "Alex", "Safronov",
//            221, "developer", EmployeeDto.Gender.MALE, "1977-12-12");
//    EmployeeDto e3 = new EmployeeDto(id3, "Anna", "Shine",
//            222, "engineer", EmployeeDto.Gender.FEMALE, "2002-5-3");
//
//    @Mock
//    private EmployeeDaoImpl employeeDaoImpl;
//
//    @BeforeEach
//    public void setupMock() {
//        MockitoAnnotations.openMocks(this);
//        employeeServiceImpl = new EmployeeServiceImpl(employeeDaoImpl);
//    }
//
//    @Test
//    public void getAllEmployees() {
//        listOfEmployeeDtos.add(e1);
//        listOfEmployeeDtos.add(e2);
//        listOfEmployeeDtos.add(e3);
//
//        when(employeeDaoImpl.getAll()).thenReturn(listOfEmployeeDtos);
//        List<EmployeeDto> actualList = employeeServiceImpl.getAll();
//
//        assertEquals(listOfEmployeeDtos, actualList);
//        assertEquals(3, actualList.size());
//        assertNotEquals(1, listOfEmployeeDtos.size());
//        verify(employeeDaoImpl).getAll();
//    }
//
//    @Test
//    public void getEmployeeById() throws EmployeeNotFoundException {
//        when(employeeDaoImpl.get(id1)).thenReturn(e1);
//
//        assertEquals(employeeServiceImpl.get(id1), e1);
//        assertNotEquals(employeeServiceImpl.get(id2), e1);
//        verify(employeeDaoImpl).get(id1);
//    }
//
//    @Test
//    public void getEmployeeByIdExpectedException() throws EmployeeNotFoundException {
//        Long id4 = 4L;
//        when(employeeDaoImpl.get(id4)).thenThrow(EmployeeNotFoundException.class);
//
//        Throwable exception;
//        exception = assertThrows(EmployeeNotFoundException.class,
//                () -> employeeServiceImpl.get(id4));
//        assertEquals("Employee with id " + id4 + " not found!", exception.getMessage());
//        verify(employeeDaoImpl).get(id4);
//    }
//
//    @Test
//    public void createNewEmployee() {
//        when(employeeDaoImpl.create(e2)).thenReturn(e2);
//
//        assertEquals(employeeServiceImpl.create(e2), e2);
//        verify(employeeDaoImpl).create(e2);
//    }
//
//    @Test
//    void updateEmployee() throws EmployeeNotFoundException {
//        EmployeeDto updatedE3 = new EmployeeDto(e3.getEmployeeId(), e3.getFirstName(), e3.getLastName(),
//                e3.getDepartmentId(), "architect", e3.getGender(), e3.getDateOfBirth());
//        when(employeeDaoImpl.update(updatedE3)).thenReturn(updatedE3);
//
//        EmployeeDto updatedE3Actual = employeeServiceImpl.update(updatedE3);
//
//        assertEquals(updatedE3Actual, updatedE3);
//        assertNotEquals(updatedE3Actual.getJobTitle(), e3.getJobTitle());
//        verify(employeeDaoImpl).update(updatedE3);
//    }
//
//    @Test
//    public void updateEmployeeExpectedException() throws EmployeeNotFoundException{
//        Long notExistingId = 5L;
//        EmployeeDto updatedE3WithNotExistingId = new EmployeeDto(notExistingId, e3.getFirstName(), e3.getLastName(),
//                e3.getDepartmentId(), "architect", e3.getGender(), e3.getDateOfBirth());
//        when(employeeDaoImpl.update(updatedE3WithNotExistingId)).thenThrow(EmployeeNotFoundException.class);
//
//        Throwable exception;
//        exception = assertThrows(EmployeeNotFoundException.class,
//                () -> employeeServiceImpl.update(updatedE3WithNotExistingId));
//        assertEquals("Employee with id " + updatedE3WithNotExistingId.getEmployeeId() + " not found!",
//                exception.getMessage());
//        verify(employeeDaoImpl).update(updatedE3WithNotExistingId);
//    }
//
//    @Test
//    public void deleteEmployeeById() throws EmployeeNotFoundException {
//        doNothing().
//                when(employeeDaoImpl).delete(id3);
//        employeeServiceImpl.delete(id3);
//        verify(employeeDaoImpl).delete(id3);
//    }
//
//    @Test
//    public void deleteEmployeeExpectedException() throws EmployeeNotFoundException {
//        long id6 = 6L;
//        doNothing().doThrow(EmployeeNotFoundException.class).
//                when(employeeDaoImpl).delete(id6);
//        employeeServiceImpl.delete(id6);
//
//        Throwable exception;
//        exception = assertThrows(EmployeeNotFoundException.class,
//                () -> employeeServiceImpl.delete(id6));
//        assertEquals("Employee with id " + id6 + " not found!", exception.getMessage());
//        verify(employeeDaoImpl, times(2)).delete(id6);
//   }
//}