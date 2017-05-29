package com.epam.webapp.dao.impl;


import com.epam.webapp.dao.AbstractVacancyDAO;
import com.epam.webapp.entity.EnglishLevel;
import com.epam.webapp.entity.ItLevel;
import com.epam.webapp.entity.Vacancy;
import com.epam.webapp.exception.DAOException;
import com.epam.webapp.util.DateUtil;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VacancyDAO extends AbstractVacancyDAO{


    @Override
    public ArrayList<Vacancy> takeAllVacancies() throws DAOException {
        ArrayList<Vacancy> res = new ArrayList<>();
        Vacancy vacancy;
        try(PreparedStatement st = connection.prepareStatement(SQL_SELECT_ALL_VACANCIES)){
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                vacancy = new Vacancy(Long.parseLong(rs.getString(ID)));
                vacancy.setSalary(rs.getBigDecimal(SALARY));
                vacancy.setCreateDate(DateUtil.stringToDate(rs.getString(CREATE_DATE)));
                vacancy.setDescription(rs.getString(DESCRIPTION));
                vacancy.setJobTitle(rs.getString(JOB_TITLE));
                vacancy.setEnglishLevel(EnglishLevel.valueOf(rs.getString(ENGLISH_LEVEL).replace("-","_").toUpperCase()));
                vacancy.setItLevel(ItLevel.valueOf(rs.getString(IT_LEVEL).replace("-","_").toUpperCase()));
                res.add(vacancy);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return res;
    }

    @Override
    public ArrayList<Vacancy> takeVacanciesByTitleAndSalary(String title, BigDecimal salary) throws DAOException {
        ArrayList<Vacancy> res = new ArrayList<>();
        Vacancy vacancy;
        try(PreparedStatement st = connection.prepareStatement(SQL_SELECT_VACANCIES_BY_TITLE_AND_SALARY)){
            st.setString(1, "'%"+ title + "%'");
            st.setBigDecimal(2, salary);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                vacancy = new Vacancy(Long.parseLong(rs.getString(ID)));
                vacancy.setSalary(rs.getBigDecimal(SALARY));
                vacancy.setCreateDate(DateUtil.stringToDate(rs.getString(CREATE_DATE)));
                vacancy.setDescription(rs.getString(DESCRIPTION));
                vacancy.setJobTitle(rs.getString(JOB_TITLE));
                vacancy.setEnglishLevel(EnglishLevel.valueOf(rs.getString(ENGLISH_LEVEL).replace("-","_").toUpperCase()));
                vacancy.setItLevel(ItLevel.valueOf(rs.getString(IT_LEVEL).replace("-","_").toUpperCase()));
                res.add(vacancy);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return res;
    }

    @Override
    public ArrayList<Vacancy> takeVacanciesByHR(long id) throws DAOException {
        ArrayList<Vacancy> res = new ArrayList<>();
        Vacancy vacancy;
        try(PreparedStatement st = connection.prepareStatement(SQL_SELECT_VACANCIES_BY_HR)){
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                vacancy = new Vacancy(Long.parseLong(rs.getString(ID)));
                vacancy.setApplicantID(rs.getLong(APPLICANT_ID));
                vacancy.setSalary(rs.getBigDecimal(SALARY));
                vacancy.setCreateDate(DateUtil.stringToDate(rs.getString(CREATE_DATE)));
                vacancy.setDescription(rs.getString(DESCRIPTION));
                vacancy.setJobTitle(rs.getString(JOB_TITLE));
                vacancy.setEnglishLevel(EnglishLevel.valueOf(rs.getString(ENGLISH_LEVEL).replace("-","_").toUpperCase()));
                vacancy.setItLevel(ItLevel.valueOf(rs.getString(IT_LEVEL).replace("-","_").toUpperCase()));
                res.add(vacancy);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return res;
    }

    @Override
    public boolean AddVacancy(long hrId, String jobTittle, String description, BigDecimal salary, ItLevel itLevel, EnglishLevel englishLevel) throws DAOException {
        boolean flag = false;
        try(PreparedStatement st = connection.prepareStatement(SQL_INSERT_VACANCY)){
            st.setLong(1, hrId);
            st.setBigDecimal(2, salary);
            st.setString(3, jobTittle);
            st.setString(4, description);
            st.setString(5, String.valueOf(itLevel));
            st.setString(6, String.valueOf(englishLevel));
            st.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return flag;
    }

}
