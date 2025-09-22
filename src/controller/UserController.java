package controller;

import catalog.Catalog;
import model.Admin;

public class UserController {
    public void addUser(String cpf, String name){
        Catalog catalog = Catalog.getInstance();

        if (!validateCpf(cpf) || catalog.cpfExists(cpf)) {
            return;
        }

        catalog.insertUser(cpf, name);
    }

    public void removeUser(String cpf, Admin admin) {
        Catalog catalog = Catalog.getInstance();

        if (!isAdmin(admin)) {
            return;
        }

        if (!validateCpf(cpf)) {
            return;
        }

        if (!catalog.cpfExists(cpf)) {
            return;
        }

        catalog.removeUser(cpf);
    }

    // Potentially unnecessary — consider refactoring or deleting
    private boolean isAdmin(Admin admin) {
        return true;
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
