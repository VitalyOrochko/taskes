package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.service.PersonService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class RegistrationApplicantCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationApplicantCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        String email = request.getParameter("email");
        try {
            if(!PersonService.isExistPersonByEmail(email)) {
                String name = request.getParameter("first-name");
                String surname = request.getParameter("last-name");
                String birthDate = request.getParameter("birth-date");
                String phone = request.getParameter("phone");
                String password = request.getParameter("password");
                String education = request.getParameter("education");
                PersonService.registerApplicant(email, password, name, surname, education, birthDate, phone);
                request.setAttribute("registrationMessage", "Registration completed successfully.");
            }else{
                request.setAttribute("registrationMessage", "Person with this Email already registred!");
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
            request.setAttribute("registrationMessage", "Something went wrong, try to register again..");
        }
        return page;
    }
}
