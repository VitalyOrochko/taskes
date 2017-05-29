package com.epam.webapp.dao.impl;


import com.epam.webapp.dao.AbstractPersonDAO;
import com.epam.webapp.entity.Applicant;
import com.epam.webapp.entity.Education;
import com.epam.webapp.entity.Employee;
import com.epam.webapp.entity.Role;
import com.epam.webapp.exception.DAOException;
import com.epam.webapp.util.DateUtil;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDAO extends AbstractPersonDAO {


    public Applicant findApplicantByEmail(String email) throws DAOException {
        Applicant applicant = null;
        try(PreparedStatement st = connection.prepareStatement(SQL_SELECT_APPLICANT_BY_EMAIL)) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                applicant = new Applicant(Long.parseLong(rs.getString(ID)));
                applicant.setName(rs.getString(NAME));
                applicant.setSurname(rs.getString(SURNAME));
                applicant.setEmail(email);
                applicant.setPhone(rs.getString(PHONE_NUMBER));
                applicant.setEducation(Education.valueOf(rs.getString(EDUCATION).toUpperCase()));
                applicant.setBirthDate(DateUtil.stringToDate(rs.getString(BIRTH_DATE)));
                applicant.setActive(rs.getBoolean(ACTIVE));
            }
        } catch (SQLException e) {
           throw new DAOException(e);
        }
        return applicant;
    }

    public Employee findEmployeeByEmail(String email) throws DAOException {
        Employee employee = null;
        try(PreparedStatement st = connection.prepareStatement(SQL_SELECT_EMPLOYEE_BY_EMAIL)) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                employee = new Employee(Long.parseLong(rs.getString(ID)));
                employee.setName(rs.getString(NAME));
                employee.setSurname(rs.getString(SURNAME));
                employee.setEmail(email);
                employee.setPhone(rs.getString(PHONE_NUMBER));
                employee.setEducation(Education.valueOf(rs.getString(EDUCATION).toUpperCase()));
                employee.setBirthDate(DateUtil.stringToDate(rs.getString(BIRTH_DATE)));
                BigDecimal bd = rs.getBigDecimal(SALARY);
                employee.setSalary(bd);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return employee;
    }

    public Role takeRoleByEmail(String email) throws DAOException {
        Role res = null;
        try (PreparedStatement st = connection.prepareStatement(SQL_SELECT_ROLE_BY_EMAIL)){
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
               res = Role.valueOf(rs.getString(ROLE).toUpperCase());
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return res;
    }

    public String takePasswordByEmail(String email) throws DAOException {
        String res = null;
        try (PreparedStatement st = connection.prepareStatement(SQL_SELECT_PASSWORD_BY_EMAIL)){
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                res = rs.getString(PASSWORD);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return res;
    }

    public boolean checkPersonByEmail(String email) throws DAOException {
        boolean res = false;
        try (PreparedStatement st = connection.prepareStatement(SQL_SELECT_PERSON_BY_EMAIL)){
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            res = rs.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return res;
    }

    public String takeIdPersonByEmail(String email) throws DAOException {
        String res = null;
        try (PreparedStatement st = connection.prepareStatement(SQL_SELECT_PERSON_BY_EMAIL)){
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                res = rs.getString(ID);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return res;
    }

    public boolean addPerson(String email, String password, String name, String surname, String education, String birth_date, String phone) throws DAOException {
        boolean flag = false;
        try (PreparedStatement st = connection.prepareStatement(SQL_INSERT_PERSON)) {
            st.setString(1, name);
            st.setString(2, surname);
            st.setString(3, email);
            st.setString(4, password);
            st.setString(5, phone);
            st.setString(6, education);
            st.setString(7, birth_date);
            st.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return flag;
    }

    public boolean addApplicant(String id) throws DAOException {
        boolean flag = false;
        try (PreparedStatement st = connection.prepareStatement(SQL_INSERT_APPLICANT)) {
            st.setString(1, id);
            st.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return flag;
    }

    @Override
    public boolean addHR(String id, BigDecimal salary) throws DAOException {
        boolean flag = false;
        try (PreparedStatement st = connection.prepareStatement(SQL_INSERT_HR)) {
            st.setString(1, id);
            st.setBigDecimal(2, salary);
            st.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return flag;
    }

    @Override
    public String takePasswordById(long id) throws DAOException {
        String res = null;
        try(PreparedStatement st = connection.prepareStatement(SQL_SELECT_PASSWORD_BY_ID)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                res = rs.getString(PASSWORD);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return res;
    }

    @Override
    public void updatePasswordById(long id, String password) throws DAOException {
        try(PreparedStatement st = connection.prepareStatement(SQL_UPDATE_PASSWORD_BY_EMAIL)) {
            st.setString(1, password);
            st.setLong(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}

