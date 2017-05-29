package com.epam.webapp.entity;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Vacancy extends Entity{
    private GregorianCalendar createDate;
    private long applicantID;
    private String jobTitle;
    private BigDecimal salary;
    private String description;
    private EnglishLevel englishLevel;
    private ItLevel itLevel;

    public Vacancy(long id){
        super(id);
    }

    public GregorianCalendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(GregorianCalendar createDate) {
        this.createDate = createDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnglishLevel getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(EnglishLevel englishLevel) {
        this.englishLevel = englishLevel;
    }

    public ItLevel getItLevel() {
        return itLevel;
    }

    public void setItLevel(ItLevel itLevel) {
        this.itLevel = itLevel;
    }

    public long getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(long applicantID) {
        this.applicantID = applicantID;
    }

    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1,7,5,3);
        stream = stream.map(p -> -p);
        System.out.println(stream.collect(Collectors.toSet()));
    }
}

