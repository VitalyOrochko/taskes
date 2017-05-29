package com.epam.webapp.dao.impl;


import com.epam.webapp.dao.AbstractInterviewDAO;
import com.epam.webapp.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InterviewDAO extends AbstractInterviewDAO{

    @Override
    public boolean addInterview(long idApplicant, long idVacancy) throws DAOException {
        boolean flag = false;
        try(PreparedStatement st = connection.prepareStatement(SQL_INSERT_INTERVIEW)){
            st.setString(1, String.valueOf(idApplicant));
            st.setString(2, String.valueOf(idVacancy));
            st.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return flag;
    }

    @Override
    public boolean checkInterview(long idApplicant, long idVacancy) throws DAOException {
        boolean flag = false;
        try(PreparedStatement st = connection.prepareStatement(SQL_SELECT_INTERVIEW)){
            st.setString(1, String.valueOf(idApplicant));
            st.setString(2, String.valueOf(idVacancy));
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                flag = true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return flag;
    }
}
