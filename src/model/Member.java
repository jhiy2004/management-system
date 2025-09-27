package model;

import java.time.LocalDate;

public class Member extends User{
    public Member(String cpf, String name, String numberOfTuition, LocalDate birthdate) {
        super(cpf, name, numberOfTuition, birthdate);
    }
}
