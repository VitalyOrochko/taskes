package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.entity.Vacancy;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.service.VacancyService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;


public class SelectVacanciesCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SelectVacanciesCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.log(Level.INFO, "test");
        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.VACANCIES_PAGE_PATH);
        try {
            String title = request.getParameter("title");
            BigDecimal salary = BigDecimal.valueOf(Long.parseLong(request.getParameter("salary")));
            ArrayList<Vacancy> vacancies = VacancyService.selectVacancies(title, salary);
            if(!vacancies.isEmpty()){
                request.getSession().setAttribute("vacancies", vacancies);
                request.setAttribute("message", "All selected vacancies");
            }else{
                request.setAttribute("message", "There are no current vacancies at the moment.");
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
            request.setAttribute("message", "Something went wrong, try again later.");
        }
        return page;
    }
}
