package com.epam.webapp.command.impl;


import com.epam.webapp.command.Command;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String locale = request.getParameter("locale");
        HttpSession session = request.getSession();
        session.setAttribute("currentLocale", locale);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
    }
}
