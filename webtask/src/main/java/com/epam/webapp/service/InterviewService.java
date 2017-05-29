package com.epam.webapp.service;


import com.epam.webapp.dao.impl.InterviewDAO;
import com.epam.webapp.exception.DAOException;
import com.epam.webapp.exception.ServiceException;

import java.sql.SQLException;

public class InterviewService {

    public static boolean registerInterview(long idApplicant, long idVacancy) throws ServiceException {
        boolean flag = false;
        try(InterviewDAO dao = new InterviewDAO()) {
            flag = dao.addInterview(idApplicant, idVacancy);
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    public static boolean checkInterview(long idApplicant, long idVacancy) throws ServiceException{
        boolean flag = false;
        try(InterviewDAO dao = new InterviewDAO()) {
            flag = dao.checkInterview(idApplicant, idVacancy);
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }
}
