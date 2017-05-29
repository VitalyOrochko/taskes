package com.epam.webapp.command.impl;


import com.epam.webapp.command.Command;
import com.epam.webapp.entity.Person;
import com.epam.webapp.manager.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class UploadFileCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(UploadFileCommand.class);

    private static final String SAVE_DIR = "upload";

    @Override
    public String execute(HttpServletRequest request) {
        Person person = (Person) request.getSession().getAttribute("person");
        if(person != null) {
            String appPath = request.getServletContext().getRealPath("");
            String savePath = appPath + File.separator + SAVE_DIR;
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
            try {
                Part part = request.getPart("file");
                String fileName = extractFileName(part);
                if(!fileName.isEmpty() && isJPG(fileName)){
                    int index = fileName.indexOf('.');
                    String name = fileName.substring(0, index);
                    fileName = fileName.replace(name, String.valueOf(person.getId()));
                    part.write(savePath + File.separator + fileName);
                }
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "IOException", e);
            } catch (ServletException e) {
                LOGGER.log(Level.ERROR, "ServletException", e);
            }
        }
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.PROFILE_PATH);
    }


    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

    private boolean isJPG(String fileName){
        int index = fileName.indexOf('.');
        String name = fileName.substring(index + 1);
        return name.equalsIgnoreCase("jpg");
    }
}
