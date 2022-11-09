package com.example.modelmapper.demo.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ManagerDto {

    private String firstName;

    private String lastName;

    private List<EmployeeDTO> employeeDTOS;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<EmployeeDTO> getEmployeeDTOS() {
        return employeeDTOS;
    }

    public void setEmployeeDTOS(List<EmployeeDTO> employeeDTOS) {
        this.employeeDTOS = employeeDTOS;
    }

    @Override
    public String toString() {
        List<String> employees = this.employeeDTOS
                .stream().map(EmployeeDTO::toString)
                .map(s -> "    - " + s)
                .collect(Collectors.toList());

        return String.format("%s %s | Employees: %d%n%s%n",
                this.firstName, this.lastName, this.employeeDTOS.size(), employees);
    }
}
