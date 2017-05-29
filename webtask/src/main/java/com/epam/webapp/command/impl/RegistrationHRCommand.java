package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.entity.Person;
import com.epam.webapp.entity.Role;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.service.PersonService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;


public class RegistrationHRCommand implements Command{
    private static final Logger LOGGER = LogManager.getLogger(RegistrationHRCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.PERSON_LIST_PATH);
        Person admin = (Person) request.getSession().getAttribute("person");
        if(admin != null && admin.getRole() == Role.ADMIN) {
            String email = request.getParameter("email");
            try {
                if (!PersonService.isExistPersonByEmail(email)) {
                    String name = request.getParameter("first-name");
                    String surname = request.getParameter("last-name");
                    String birthDate = request.getParameter("birth-date");
                    String phone = request.getParameter("phone");
                    String password = request.getParameter("password");
                    String education = request.getParameter("education");
                    BigDecimal salary = BigDecimal.valueOf(Long.parseLong(request.getParameter("salary")));
                    PersonService.registerHR(email, password, name, surname, education, birthDate, phone, salary);
                    request.setAttribute("registrationMessage", "Registration completed successfully.");
                } else {
                    request.setAttribute("registrationMessage", "Person with this Email already registred!");
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
                request.setAttribute("registrationMessage", "Something went wrong, try to register again..");
            }
        }else{
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        }
        return page;
    }
}
