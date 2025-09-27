package controller;

import catalog.Catalog;
import model.Admin;
import model.Owner;
import model.User;

import java.time.LocalDate;

public class UserController {
    public boolean updateOwner(Owner owner, String cpf, String name, LocalDate birthdate, String numberOfTuition, String password) {
        Catalog catalog = Catalog.getInstance();

        if (!validateCpf(cpf) && catalog.cpfExists(cpf)){
            return false;
        }

        owner.setCpf(cpf);
        owner.setName(name);
        owner.setBirthdate(birthdate);
        owner.setNumberOfTuition(numberOfTuition);
        owner.setPassword(password);

        return true;
    }

    public void addMember(String cpf, String name, String numberOfTuition, LocalDate birthdate){
        Catalog catalog = Catalog.getInstance();

        if (!validateCpf(cpf) || catalog.cpfExists(cpf)) {
            return;
        }

        catalog.insertMember(cpf, name, numberOfTuition, birthdate);
    }

    public void addOwner(String cpf, String name, String numberOfTuition, LocalDate birthdate, String password) {
        Catalog catalog = Catalog.getInstance();

        if(!validateCpf(cpf) || catalog.cpfExists(cpf)) {
            return;
        }

        catalog.insertOwner(cpf, name, numberOfTuition, birthdate, password);
    }

    public void removeMember(String cpf, User user) {
        Catalog catalog = Catalog.getInstance();

        if (!isAdmin(user)) {
            return;
        }

        if (!validateCpf(cpf)) {
            return;
        }

        if (!catalog.cpfExists(cpf)) {
            return;
        }

        catalog.removeMember(cpf);
    }

    // Potentially unnecessary — consider refactoring or deleting
    private boolean isAdmin(User user) {
        return (user instanceof Admin);
    }

    private boolean validateCpf(String cpf) {
        if (cpf == null) return false;

        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) return false;

        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma = 0, resto;

            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }
            resto = 11 - (soma % 11);
            int digito1 = (resto == 10 || resto == 11) ? 0 : resto;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }
            resto = 11 - (soma % 11);
            int digito2 = (resto == 10 || resto == 11) ? 0 : resto;

            return (digito1 == (cpf.charAt(9) - '0')) &&
                    (digito2 == (cpf.charAt(10) - '0'));
        } catch (Exception e) {
            return false;
        }
    }
}
