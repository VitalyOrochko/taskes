package com.epam.webapp.command.impl;


import com.epam.webapp.command.Command;
import com.epam.webapp.entity.Person;
import com.epam.webapp.entity.Role;
import com.epam.webapp.entity.Vacancy;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.service.VacancyService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ShowHRVacanciesCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ShowHRVacanciesCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        if(person != null && person.getRole() == Role.HR){
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.VACANCIES_OF_HR_PAGE_PATH);
            try {
                ArrayList<Vacancy> vacancies = VacancyService.takeAllHRVacancies(person.getId());
                if(!vacancies.isEmpty()){
                    session.setAttribute("vacancies", vacancies);
                    request.setAttribute("message", "All actual vacancies");
                }else{
                    request.setAttribute("message", "There are no current vacancies at the moment.");
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
                request.setAttribute("message", "Something went wrong, try again later.");
            }
        }else{
           page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        }
        return page;
    }
}
