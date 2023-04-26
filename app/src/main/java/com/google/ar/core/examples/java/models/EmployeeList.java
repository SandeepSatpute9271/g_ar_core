package com.google.ar.core.examples.java.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeList {

    @SerializedName("employee_list")
    private List<Employee> employeeList;

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
