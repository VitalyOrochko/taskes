package com.epam.webapp.service;

import com.epam.webapp.dao.impl.PersonDAO;
import com.epam.webapp.entity.Person;
import com.epam.webapp.entity.Role;
import com.epam.webapp.exception.DAOException;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.pool.ProxyConnection;
import com.epam.webapp.util.MD5Hash;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * Created  on 20.03.2017.
 */
public class PersonService {
    public static Person findPersonByEmailAndPassword(String email, String password) throws ServiceException {
        Person person = null;
        password = MD5Hash.md5Custom(password);
        try(PersonDAO personDAO = new PersonDAO()) {
            Role role = personDAO.takeRoleByEmail(email);
            if(role != null) {
                String realPassword = personDAO.takePasswordByEmail(email);
                if(realPassword.equals(password)) {
                    switch (role) {
                        case APPLICANT:
                            person = personDAO.findApplicantByEmail(email);
                            break;
                        case ADMIN:
                        case HR:
                            person = personDAO.findEmployeeByEmail(email);
                            break;
                        default:
                            throw new ServiceException("Unknown role!");
                    }
                    person.setRole(role);
                }
            }
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
        return person;
    }

    public static boolean isExistPersonByEmail(String email) throws ServiceException {
        boolean res = false;
        try(PersonDAO personDAO = new PersonDAO()) {
            res =  personDAO.checkPersonByEmail(email);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
        return res;
    }

    public static void registerApplicant(String email, String password, String name, String surname, String education, String birthDate, String phone) throws ServiceException {

        password = MD5Hash.md5Custom(password);
        try(PersonDAO personDAO = new PersonDAO()) {
            ProxyConnection connection = personDAO.getConnection();
            connection.setAutoCommit(false);
            personDAO.addPerson(email, password, name, surname, education, birthDate, phone);
            String id = personDAO.takeIdPersonByEmail(email);
            personDAO.addApplicant(id);
            connection.commit();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public static void registerHR(String email, String password, String name, String surname, String education, String birthDate, String phone, BigDecimal salary) throws ServiceException {
        password = MD5Hash.md5Custom(password);
        try(PersonDAO personDAO = new PersonDAO()) {
            ProxyConnection connection = personDAO.getConnection();
            connection.setAutoCommit(false);
            personDAO.addPerson(email, password, name, surname, education, birthDate, phone);
            String id = personDAO.takeIdPersonByEmail(email);
            personDAO.addHR(id, salary);
            connection.commit();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public static boolean checkPasswordById(long id, String password) throws ServiceException {
        password = MD5Hash.md5Custom(password);
        boolean flag;
        try(PersonDAO dao = new PersonDAO()){
            String realPassword = dao.takePasswordById(id);
            if(realPassword != null) {
                flag = realPassword.equals(password);
            }else{
                throw new ServiceException("Illegal id of person.");
            }
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    public static void changePasswordById(long id, String newPassword) throws ServiceException {
        newPassword = MD5Hash.md5Custom(newPassword);
        try(PersonDAO dao = new PersonDAO()) {
            dao.updatePasswordById(id,newPassword);
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e);
        }
    }
}
