package com.epam.webapp.entity;


import java.util.GregorianCalendar;

public class Applicant extends Person{
    private boolean active;


    public Applicant(long id, String name, String surname, GregorianCalendar birthDate, Education education, String email, String phone, boolean active) {
        super(id, name, surname, birthDate, education, email, phone);
        this.active = active;
    }

    public Applicant(long id) {
        super(id);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
