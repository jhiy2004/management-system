package model;

public class User {
    private String cpf;
    private String name;

    public User(String cpf, String name) {
        this.name = name;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }
}
