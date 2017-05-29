package com.epam.webapp.dao;


import com.epam.webapp.entity.Applicant;
import com.epam.webapp.entity.Employee;
import com.epam.webapp.entity.Role;
import com.epam.webapp.exception.DAOException;

import java.math.BigDecimal;

public abstract class AbstractPersonDAO extends AbstractDAO{

    protected static final String SQL_SELECT_APPLICANT_BY_EMAIL = "SELECT name, surname, birth_date, education, phone_number, id_person, active FROM person JOIN applicant ON person.id_person = applicant.id_applicant WHERE email =?";
    protected static final String SQL_SELECT_EMPLOYEE_BY_EMAIL = "SELECT name, surname, birth_date, education, phone_number, id_person, salary FROM person JOIN employee ON person.id_person = employee.id_employee WHERE email =?";
    protected static final String SQL_SELECT_ROLE_BY_EMAIL = "SELECT role FROM person WHERE email = ?";
    protected static final String SQL_SELECT_PASSWORD_BY_EMAIL = "SELECT password FROM person WHERE email = ?";
    protected static final String SQL_SELECT_PASSWORD_BY_ID = "SELECT password FROM person WHERE id_person = ?";
    protected static final String SQL_SELECT_PERSON_BY_EMAIL = "SELECT id_person FROM person WHERE email = ?";
    protected static final String SQL_UPDATE_PASSWORD_BY_EMAIL = "UPDATE person SET password = ? WHERE id = ?";
    protected static final String SQL_INSERT_APPLICANT = "INSERT INTO applicant (`id_applicant`, `active`) VALUES (?, DEFAULT)";
    protected static final String SQL_INSERT_HR = "INSERT INTO employee (`id_employee`, `salary`) VALUES (?, ?)";
    protected static final String SQL_INSERT_PERSON = "INSERT INTO person (`name`, `surname`, `email`, `password`, `phone_number`, `education`, `birth_date`, `role`) VALUES (?, ?, ?, ?, ?, ?, ?, 'applicant')";

    protected static final String NAME = "name";
    protected static final String SURNAME = "surname";
    protected static final String BIRTH_DATE = "birth_date";
    protected static final String PHONE_NUMBER = "phone_number";
    protected static final String ID = "id_person";
    protected static final String ROLE = "role";
    protected static final String PASSWORD = "password";
    protected static final String EDUCATION = "education";
    protected static final String SALARY = "salary";
    protected static final String ACTIVE = "active";

    public abstract Applicant findApplicantByEmail(String email) throws DAOException;

    public abstract Employee findEmployeeByEmail(String email) throws DAOException;

    public abstract Role takeRoleByEmail(String email) throws DAOException;

    public abstract String takePasswordByEmail(String email) throws DAOException;

    public abstract boolean checkPersonByEmail(String email) throws DAOException;

    public abstract String takeIdPersonByEmail(String email) throws DAOException;

    public abstract boolean addPerson(String email, String password, String name, String surname, String education, String birth_date, String phone) throws DAOException;

    public abstract boolean addApplicant(String id) throws DAOException;

    public abstract boolean addHR(String id, BigDecimal salary) throws DAOException;

    public abstract String takePasswordById(long id) throws DAOException;

    public abstract void updatePasswordById(long id, String password) throws DAOException;

}
