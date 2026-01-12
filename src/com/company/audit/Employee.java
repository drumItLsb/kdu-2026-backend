package com.company.audit;

import java.util.List;

public class Employee {
    private final String name;
    private final int age;
    private final double salary;
    private final String department;

    public Employee(String name, int age, double salary, String department) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    // Getters (needed for compliance/reporting)
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return String.format("Employee{Name='%s', Dept='%s', Salary=$%.2f, Age=%d}", name, department, salary, age);
    }
    // Test data method
    public static List<Employee> getSampleData() {
        return List.of(
                new Employee("emp1",20,75000,"ENGINEERING"),
                new Employee("emp2",21,67000,"TESTING"),
                new Employee("emp3",22,79000,"ENGINEERING"),
                new Employee("emp4",24,71000,"TESTING"),
                new Employee("emp5",25,65000,"ENGINEERING"),
                new Employee("emp6",26,60000,"TESTING")
        );
    }
}

