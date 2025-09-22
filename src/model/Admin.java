package model;

public class Admin {
    private String name;
    private String cpf;

    public Admin(String name, String cpf){
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
