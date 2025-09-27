package catalog;

import model.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Catalog {
    private static Catalog instance = null;
    private final Map<String, Admin> admins;
    private final Map<String, Member> members;
    private final Map<String, Owner> owners;
    private final Map<String, Manager> managers;
    private final Map<String, Department> departments;

    public static Catalog getInstance() {
        if (instance == null) {
            instance = new Catalog();
        }
        return instance;
    }

    private Catalog() {
        this.admins = new HashMap<>();
        this.members = new HashMap<>();
        this.owners = new HashMap<>();
        this.managers = new HashMap<>();

        this.departments = new HashMap<>();
    }

    public Map<String, Admin> getAdmins() {
        return admins;
    }

    public Map<String, Member> getMembers() {
        return members;
    }

    public Map<String, Owner> getOwners() {
        return owners;
    }

    public Map<String, Manager> getManagers() {
        return managers;
    }

    public Admin getAdminByCpf(String cpf) {
        return this.admins.get(cpf);
    }

    public Member getMemberByCpf(String cpf) {
        return this.members.get(cpf);
    }

    public Owner getOwnerByCpf(String cpf) {
        return this.owners.get(cpf);
    }

    public Manager getManagerByCpf(String cpf) {
        return this.managers.get(cpf);
    }

    public boolean cpfExists(String cpf) {
        return this.members.containsKey(cpf) ||
                this.admins.containsKey(cpf) ||
                this.owners.containsKey(cpf) ||
                this.managers.containsKey(cpf);
    }

    public void insertMember(String cpf, String name, String numberOfTuition, LocalDate birthdate) {
        Member newMember = new Member(cpf, name, numberOfTuition, birthdate);

        this.members.put(cpf, newMember);
    }

    public void insertOwner(String cpf, String name, String numberOfTuition, LocalDate birthdate, String password) {
        Owner newOwner = new Owner(cpf, name, numberOfTuition, birthdate, password);

        this.owners.put(cpf, newOwner);
    }

    public void removeMember(String cpf) {
        this.members.remove(cpf);
    }

    public boolean departmentNameExists(String name) {
        return this.departments.containsKey(name);
    }

    public void insertDepartment(String name) {
        Department newDepartment = new Department(name);

        this.departments.put(name, newDepartment);
    }
}
