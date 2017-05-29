package com.epam.webapp.dao;


import com.epam.webapp.entity.EnglishLevel;
import com.epam.webapp.entity.ItLevel;
import com.epam.webapp.entity.Vacancy;
import com.epam.webapp.exception.DAOException;

import java.math.BigDecimal;
import java.util.ArrayList;

public abstract class AbstractVacancyDAO extends AbstractDAO{

    protected static final String SQL_SELECT_ALL_VACANCIES = "SELECT id_vacancy, create_date, salary, job_title, description, english_level, it_level FROM vacancy WHERE complete = 0";
    protected static final String SQL_SELECT_VACANCIES_BY_HR = "SELECT id_vacancy, applicant_id, create_date, salary, job_title, description, english_level, it_level FROM vacancy WHERE complete = 0 and hr_id = ?";
    protected static final String SQL_SELECT_VACANCIES_BY_TITLE_AND_SALARY = "SELECT id_vacancy, create_date, salary, job_title, description, english_level, it_level FROM vacancy WHERE complete = 0 AND job_title LIKE '' AND salary > ? "; //todo like ?
    protected static final String SQL_INSERT_VACANCY = "INSERT INTO vacancy (hr_id, salary, job_title, description, english_level, it_level, create_date, complete) VALUES(?, ?, ?, ?, ?, ?, CURDATE(), 0)";

    protected static final String ID = "id_vacancy";
    protected static final String CREATE_DATE = "create_date";
    protected static final String SALARY = "salary";
    protected static final String JOB_TITLE = "job_title";
    protected static final String DESCRIPTION = "description";
    protected static final String ENGLISH_LEVEL = "english_level";
    protected static final String APPLICANT_ID = "applicant_id";
    protected static final String IT_LEVEL = "it_level";

    public abstract ArrayList<Vacancy> takeAllVacancies() throws DAOException;

    public abstract ArrayList<Vacancy> takeVacanciesByTitleAndSalary(String title, BigDecimal salary) throws DAOException;

    public abstract ArrayList<Vacancy> takeVacanciesByHR(long id) throws DAOException;

    public abstract boolean AddVacancy(long hrId, String jobTittle, String description, BigDecimal salary, ItLevel itLevel, EnglishLevel englishLevel) throws DAOException;
}

