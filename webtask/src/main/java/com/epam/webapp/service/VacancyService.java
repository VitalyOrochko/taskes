package com.epam.webapp.service;


import com.epam.webapp.dao.impl.VacancyDAO;
import com.epam.webapp.entity.EnglishLevel;
import com.epam.webapp.entity.ItLevel;
import com.epam.webapp.entity.Vacancy;
import com.epam.webapp.exception.DAOException;
import com.epam.webapp.exception.ServiceException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class VacancyService {

    public static ArrayList<Vacancy> takeAllVacancies() throws ServiceException {
        ArrayList<Vacancy> res;
        try (VacancyDAO vacancyDAO = new VacancyDAO()){
            res = vacancyDAO.takeAllVacancies();
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e);
        }
        return res;
    }

    public static ArrayList<Vacancy> selectVacancies(String title, BigDecimal salary) throws ServiceException {
        ArrayList<Vacancy> res;
        try (VacancyDAO vacancyDAO = new VacancyDAO()){
            res = vacancyDAO.takeVacanciesByTitleAndSalary(title, salary);
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e);
        }
        return res;
    }

    public static ArrayList<Vacancy> takeAllHRVacancies(long id) throws ServiceException {
        ArrayList<Vacancy> res;
        try (VacancyDAO vacancyDAO = new VacancyDAO()){
            res = vacancyDAO.takeVacanciesByHR(id);
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e);
        }
        return res;
    }

    public static void addVacancy(long hrId, String jobTittle, String description, BigDecimal salary, ItLevel itLevel, EnglishLevel englishLevel) throws ServiceException {
        try(VacancyDAO dao = new VacancyDAO()){
            dao.AddVacancy(hrId, jobTittle, description, salary, itLevel, englishLevel);
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e);
        }
    }

}
