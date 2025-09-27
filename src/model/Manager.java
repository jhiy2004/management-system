package model;

import java.time.LocalDate;

public class Manager extends User{
    public Manager(String cpf, String name, String numberOfTuition, LocalDate birthdate) {
        super(cpf, name, numberOfTuition, birthdate);
    }
}
