package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.entity.EnglishLevel;
import com.epam.webapp.entity.ItLevel;
import com.epam.webapp.entity.Person;
import com.epam.webapp.entity.Role;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.service.VacancyService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;


public class AddVacancyCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddVacancyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        Person person = (Person) request.getSession().getAttribute("person");
        if(person != null && person.getRole() == Role.HR){
            long  hrId = person.getId();
            String jobTittle = request.getParameter("job_title");
            String description = request.getParameter("description");
            BigDecimal salary = BigDecimal.valueOf(Long.parseLong(request.getParameter("salary")));
            ItLevel itLevel = ItLevel.valueOf(request.getParameter("it_level").toUpperCase());
            EnglishLevel englishLevel = EnglishLevel.valueOf(request.getParameter("english_level").toUpperCase());
            try {
                VacancyService.addVacancy(hrId, jobTittle, description, salary, itLevel, englishLevel);
                request.setAttribute("message", "Vacancy added successfully.");
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
                request.setAttribute("message", "Something went wrong, try again later.");
            }
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.VACANCIES_OF_HR_PAGE_PATH);
        }else {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        }
        return page;
    }
}
