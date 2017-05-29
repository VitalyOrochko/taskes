package com.epam.webapp.command.impl;


import com.epam.webapp.command.Command;
import com.epam.webapp.entity.Person;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.service.InterviewService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class RegisterInterviewCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RegisterInterviewCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.VACANCIES_PAGE_PATH);
        Person person = (Person) request.getSession().getAttribute("person");
        if(person != null){
            long idPerson = person.getId();
            long idVacancy = Long.parseLong(request.getParameter("id"));
            try {
                if(!InterviewService.checkInterview(idPerson, idVacancy)) {
                    InterviewService.registerInterview(idPerson, idVacancy);
                }else {
                    request.setAttribute("registerInterviewMessage", "You already register on this vacancy");
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
                request.setAttribute("registerInterviewMessage", "Something went wrong, try to register again..");
            }
        }else{
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
            request.setAttribute("LogInMessage", "To register for an interview, you must sign in.");
        }
        return page;
    }
}
