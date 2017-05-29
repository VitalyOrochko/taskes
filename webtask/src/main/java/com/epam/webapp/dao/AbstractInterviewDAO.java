package com.epam.webapp.dao;


import com.epam.webapp.exception.DAOException;

public abstract class AbstractInterviewDAO extends AbstractDAO{
    protected static final String SQL_INSERT_INTERVIEW = "INSERT INTO interview (applicant_id, vacancy_id, create_date) VALUES (?,?,CURDATE())";
    protected static final String SQL_SELECT_INTERVIEW = "SELECT id_interview FROM interview WHERE applicant_id = ? AND vacancy_id = ?";

    public abstract boolean addInterview(long idApplicant, long idVacancy) throws DAOException;

    public abstract boolean checkInterview(long idApplicant, long idVacancy) throws DAOException;
}
