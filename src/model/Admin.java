package model;

import java.time.LocalDate;

public class Admin extends User{
    public Admin(String cpf, String name, String numberOfTuition, LocalDate birthdate) {
        super(cpf, name, numberOfTuition, birthdate);
    }
}
