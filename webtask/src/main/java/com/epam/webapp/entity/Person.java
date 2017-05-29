package com.epam.webapp.entity;


import java.util.GregorianCalendar;

public abstract class Person extends Entity{
    private String name;
    private String surname;
    private GregorianCalendar birthDate;
    private Education education;
    private String email;
    private String phone;
    private Role role;

    public Person(long id, String name, String surname, GregorianCalendar birthDate, Education education, String email, String phone) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.education = education;
        this.email = email;
        this.phone = phone;
    }

    public Person(long id){
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public GregorianCalendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(GregorianCalendar birthDate) {
        this.birthDate = birthDate;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
