package com.epam.webapp.entity;


import java.math.BigDecimal;
import java.util.GregorianCalendar;

public class Employee extends Person{
    private BigDecimal salary;

    public Employee(long id, String name, String surname, GregorianCalendar birthDate, Education education, String email, String phone, BigDecimal salary) {
        super(id, name, surname, birthDate, education, email, phone);
        this.salary = salary;
    }

    public Employee(long id) {
        super(id);
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
