package model;

import java.time.LocalDate;

public class Owner extends User {
    private String password;

    public Owner(String cpf, String name, String numberOfTuition, LocalDate birthdate, String password) {
        super(cpf, name, numberOfTuition, birthdate);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
