package com.epam.webapp.command;

import javax.servlet.http.HttpServletRequest;


public class CommandFactory {
    public static Command defineCommand(HttpServletRequest request){
        String action = request.getParameter("command");
        return CommandType.valueOf(action.toUpperCase()).getCommand();
    }
}
