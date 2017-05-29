package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.entity.Person;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.service.PersonService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Андрей on 12.04.2017.
 */
public class ChangePasswordCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ChangePasswordCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.PROFILE_PATH);
        String oldPassword = request.getParameter("old_password");
        String newPassword = request.getParameter("new_password");
        Person person = (Person) request.getSession().getAttribute("person");
        if(person != null){
            try {
                if(PersonService.checkPasswordById(person.getId(),oldPassword)){
                    PersonService.changePasswordById(person.getId(), newPassword);
                    request.setAttribute("message", "Password was changed successfully.");
                } else {
                    request.setAttribute("message", "Incorrect old password");
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        }else{
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        }
        return page;
    }
}
