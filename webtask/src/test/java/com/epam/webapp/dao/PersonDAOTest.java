package com.epam.webapp.dao;


import com.epam.webapp.dao.impl.PersonDAO;
import com.epam.webapp.exception.DAOException;
import org.junit.Before;
import org.junit.Test;

public class PersonDAOTest {
    private PersonDAO personDAO;
    @Before
    public void init(){
        personDAO = new PersonDAO();
    }

    @Test
    public void takeRoleByEmailTest() throws DAOException {

    }

    @Test
    public void findApplicantByEmailTest() throws DAOException {
        personDAO.findApplicantByEmail("luizun@mail.ru");
    }
}
