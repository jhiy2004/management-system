package model;

import java.time.LocalDate;

public class Company {
    private String name;
    private String cnpj;
    private String email;
    private LocalDate date;

    public Company(String name, String cnpj, String email, LocalDate date) {
        this.name = name;
        this.cnpj = cnpj;
        this.email = email;
        this.date = date;
    }

    public String getName() {
        return this.name;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getEmail() {
        return this.email;
    }

    public LocalDate getDate() {
        return this.date;
    }
}