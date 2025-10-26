package controller;

import catalog.Catalog;
import model.*;

import java.time.LocalDate;
import java.util.Collection;

public class UserController {
    public void saveUsers() {
        Catalog catalog = Catalog.getInstance();
        catalog.saveUsers();
    }

    public LoginUser login(String cpf, String password) {
        if (!validateCpf(cpf)) {
            return null;
        }

        Catalog catalog = Catalog.getInstance();

        if (!catalog.cpfExists(cpf)) {
            return null;
        }

        User user = catalog.getUserByCpf(cpf);

        // Garante que o usuário é do tipo LoginUser
        if (!(user instanceof LoginUser loginUser)) {
            return null;
        }

        if (!password.equals(loginUser.getPassword())) {
            return null;
        }

        return loginUser;
    }

    public Collection<Member> getMembers() {
        Catalog catalog = Catalog.getInstance();

        return catalog.getMembers();
    }

    public boolean createCompany(String name, String cnpj, String email, LocalDate date) {
        Company company = new Company(name, cnpj, email, date);

        if (!validateCompanyInformation(company)) {
            return false;
        }

        Catalog catalog = Catalog.getInstance();

        catalog.addCompany(company);

        return true;
    }

    public boolean updateMember(Member member, String cpf, String name, String numberOfTuition, LocalDate birthdate){
        Catalog catalog = Catalog.getInstance();

        if (!validateCpf(cpf) || (catalog.cpfExists(cpf) && catalog.getUserByCpf(cpf) != member)) {
            return false;
        }

        member.setName(name);
        member.setCpf(cpf);
        member.setNumberOfTuition(numberOfTuition);
        member.setBirthdate(birthdate);

        return true;
    }

    public boolean updateOwner(Owner owner, String cpf, String name, LocalDate birthdate, String numberOfTuition, String password) {
        Catalog catalog = Catalog.getInstance();

        if (!validateCpf(cpf) || (catalog.cpfExists(cpf) && catalog.getUserByCpf(cpf) != owner)) {
            return false;
        }

        owner.setCpf(cpf);
        owner.setName(name);
        owner.setBirthdate(birthdate);
        owner.setNumberOfTuition(numberOfTuition);
        owner.setPassword(password);

        return true;
    }

    public boolean updateAdmin(Admin admin, String cpf, String name, LocalDate birthdate, String numberOfTuition, String password, Department department) {
        Catalog catalog = Catalog.getInstance();

        if (!validateCpf(cpf) || (catalog.cpfExists(cpf) && catalog.getUserByCpf(cpf) != admin)) {
            return false;
        }

        admin.setCpf(cpf);
        admin.setName(name);
        admin.setBirthdate(birthdate);
        admin.setNumberOfTuition(numberOfTuition);
        admin.setPassword(password);
        admin.setDepartment(department);

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

    public void addAdmin(String cpf, String name, String numberOfTuition, LocalDate birthdate, String password, Department department) {
        Catalog catalog = Catalog.getInstance();

        if(!validateCpf(cpf) || catalog.cpfExists(cpf)) {
            return;
        }

        catalog.insertAdmin(cpf, name, numberOfTuition, birthdate, password, department);
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

    private boolean validateCompanyInformation(Company company) {
        return (company.getName().length() > 8 && validateEmail(company.getEmail()) && validateCnpj(company.getCnpj()));
    }

    // Potentially unnecessary — consider refactoring or deleting
    private boolean isAdmin(User user) {
        return (user instanceof Admin);
    }

    public static boolean validateCnpj(String cnpj) {
        if (cnpj == null) return false;

        cnpj = cnpj.replaceAll("\\D", "");

        if (cnpj.length() != 14) return false;

        // Verifica se todos os dígitos são iguais
        if (cnpj.matches("(\\d)\\1{13}")) return false;

        try {
            int soma = 0, resto;
            int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            // Primeiro dígito verificador
            for (int i = 0; i < 12; i++) {
                soma += (cnpj.charAt(i) - '0') * peso1[i];
            }
            resto = soma % 11;
            int digito1 = (resto < 2) ? 0 : 11 - resto;

            // Segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 13; i++) {
                soma += (cnpj.charAt(i) - '0') * peso2[i];
            }
            resto = soma % 11;
            int digito2 = (resto < 2) ? 0 : 11 - resto;

            return (digito1 == (cnpj.charAt(12) - '0')) &&
                    (digito2 == (cnpj.charAt(13) - '0'));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateEmail(String email) {
        if (email == null || email.isBlank()) return false;

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
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
