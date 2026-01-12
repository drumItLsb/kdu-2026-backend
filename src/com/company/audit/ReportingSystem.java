package com.company.audit;

import java.util.List;
import java.util.function.Predicate;


public class ReportingSystem {
    public static void main(String[] args) {
        List<Employee> employeeList = Employee.getSampleData();

        getHighEarningEngineers(
                employeeList,
                employee -> employee.getSalary() > 70000 && employee.getDepartment().equals("ENGINEERING"))
                .forEach(emp -> System.out.println(emp.getName()));

        System.out.println(getStandardEmployeeNameReport(employeeList));
        System.out.println("Annual Salary Budget: "+getCompanyAnnualSalaryBudget(employeeList));
    }

    public static List<Employee> getHighEarningEngineers(List<Employee> employeeList, Predicate<Employee> filter) {
        return employeeList.stream().filter(filter).toList();
    }

    public static List<String> getStandardEmployeeNameReport(List<Employee> employeeList) {
        return employeeList.stream().map(emp -> emp.getName().toUpperCase()).toList();
    }

    public static double getCompanyAnnualSalaryBudget(List<Employee> employeeList) {
        return employeeList.stream().map(Employee::getSalary).reduce(0.0, Double::sum);
    }

}