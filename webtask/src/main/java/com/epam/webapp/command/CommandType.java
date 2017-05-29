package com.epam.webapp.command;


import com.epam.webapp.command.impl.*;

public enum CommandType {
    LOG_IN(new LogInCommand()),
    UPLOAD_FILE(new UploadFileCommand()),
    REGISTRATION_APPLICANT(new RegistrationApplicantCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    SHOW_VACANCIES(new ShowVacanciesCommand()),
    REGISTER_INTERVIEW(new RegisterInterviewCommand()),
    SELECT_VACANCIES(new SelectVacanciesCommand()),
    SHOW_HR_VACANCIES(new ShowHRVacanciesCommand()),
    ADD_VACANCY(new AddVacancyCommand()),
    LOGOUT(new LogoutCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
