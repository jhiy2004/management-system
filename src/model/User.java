package model;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    protected String cpf;
    protected String name;
    protected String numberOfTuition;
    protected LocalDate birthdate;

    public User(String cpf, String name, String numberOfTuition, LocalDate birthdate) {
        this.name = name;
        this.cpf = cpf;
        this.numberOfTuition = numberOfTuition;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNumberOfTuition() {
        return numberOfTuition;
    }

    public void setNumberOfTuition(String numberOfTuition) {
        this.numberOfTuition = numberOfTuition;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
