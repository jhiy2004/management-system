package model;

import java.time.LocalDate;

public class Owner extends LoginUser {
    public Owner(String cpf, String name, String numberOfTuition, LocalDate birthdate, String password) {
        super(cpf, name, numberOfTuition, birthdate, password);
    }

}
