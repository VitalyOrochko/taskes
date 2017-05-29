package com.epam.webapp.manager;


import java.util.ResourceBundle;

public class ConfigurationManager {
    /** Field instance */
    private static ConfigurationManager instance;

    /** Field resourceBundle */
    private ResourceBundle resourceBundle;

    /** Field BUNDLE_NAME */
    private static final String BUNDLE_NAME = "config";

    /** Field DB_ADDRESS */
    public static final String DB_ADDRESS = "database.url";

    /** Field LOGIN */
    public static final String LOGIN = "database.login";

    /** Field PASSWORD */
    public static final String PASSWORD = "database.password";

    /** Field POOL_SIZE */
    public static final String POOL_SIZE = "database.pool.size";

    /** Field LOGIN_PAGE_PATH */
    public static final String LOGIN_PAGE_PATH = "LOGIN_JSP";
    public static final String MAIN_PAGE_PATH = "MAIN_JSP";
    public static final String VACANCIES_PAGE_PATH = "VACANCIES_JSP";
    public static final String VACANCIES_OF_HR_PAGE_PATH = "VACANCIES_OF_HR_JSP";
    public static final String PERSON_LIST_PATH = "PERSON_LIST_JSP";
    public static final String PROFILE_PATH = "PROFILE_JSP";

    private ConfigurationManager(){
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    }

    /**
     * Method getInstance
     *
     * @return ConfigurationManager
     */
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    /**
     * Method getProperty
     *
     * @param key of type String
     * @return String
     */
    public String getProperty(String key) {
        return (String)resourceBundle.getObject(key);
    }
}


