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

public class LogInCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LogInCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        ConfigurationManager cm = ConfigurationManager.getInstance();
        try {
            Person person = PersonService.findPersonByEmailAndPassword(email, password);
            if(person != null){
                page = cm.getProperty(ConfigurationManager.MAIN_PAGE_PATH);
                request.getSession().setAttribute("person", person);
            }else{
                page = cm.getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
                request.setAttribute("LogInMessage", "Incorrect email or password!");
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
            request.setAttribute("LogInMessage", "Something went wrong, try to login again..");
            page = cm.getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        }
        return page;
    }
}
