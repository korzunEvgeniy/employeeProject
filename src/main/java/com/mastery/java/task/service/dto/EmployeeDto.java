package com.mastery.java.task.service.dto;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EmployeeDto {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private int departmentId;
    private String jobTitle;
    private Gender gender;
    private String dateOfBirth;

    public enum Gender {
        MALE,
        FEMALE
    }

    public EmployeeDto(Long employeeId, String firstName, String lastName,
                       int departmentId, String jobTitle,
                       Gender gender, String dateOfBirth) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public EmployeeDto() {
    }

    public Long getEmployeeId() {
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
        if (!(o instanceof EmployeeDto))
            return false;
        EmployeeDto employeeDto = (EmployeeDto) o;
        return Objects.equals(this.employeeId, employeeDto.employeeId) &&
                Objects.equals(this.firstName, employeeDto.firstName) &&
                Objects.equals(this.lastName, employeeDto.lastName) &&
                Objects.equals(this.departmentId, employeeDto.departmentId) &&
                Objects.equals(this.jobTitle, employeeDto.jobTitle) &&
                this.gender == employeeDto.gender &&
                Objects.equals(this.dateOfBirth, employeeDto.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.employeeId, this.firstName, this.lastName,
                this.departmentId, this.jobTitle, this.gender, this.dateOfBirth);
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
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