package com.mastery.java.task.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;


@Entity
@Component
public class Employee {

    private @Id
    @GeneratedValue
    Long employeeId;
    //private Long employeeId;

//    @NotEmpty(message = "Name should not be empty")
//    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String firstName;

//    @NotEmpty(message = "Name should not be empty")
//    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String lastName;

    private int departmentId;

//    @NotEmpty(message = "job title should not be empty")
    private String jobTitle;

    private Gender gender;
    private String dateOfBirth;

    public Employee(long employeeId, String firstName, String lastName, int departmentId, String jobTitle,
                    Gender gender, String dateOfBirth) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee() {
    }

    public Long getEmployeeId(Long id) {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Gender getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.employeeId, employee.employeeId) &&
                Objects.equals(this.firstName, employee.firstName) &&
                Objects.equals(this.lastName, employee.lastName) &&
                Objects.equals(this.departmentId, employee.departmentId) &&
                Objects.equals(this.jobTitle, employee.jobTitle) &&
                this.gender == employee.gender &&
                Objects.equals(this.dateOfBirth, employee.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.employeeId, this.firstName, this.lastName, this.departmentId,
                this.jobTitle, this.gender, this.dateOfBirth);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departmentId=" + departmentId +
                ", jobTitle='" + jobTitle + '\'' +
                ", gender=" + gender +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
