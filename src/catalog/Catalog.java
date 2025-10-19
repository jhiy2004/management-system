package catalog;

import model.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalog {
    private static Catalog instance = null;
    private Company company;
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

    public Collection<Department> getDepartments() {
        return departments.values();
    }

    public User getUserByCpf(String cpf) {
        if (this.members.containsKey(cpf)) {
            return this.members.get(cpf);
        } else if (this.managers.containsKey(cpf)) {
            return this.managers.get(cpf);
        } else if (this.owners.containsKey(cpf)) {
            return this.owners.get(cpf);
        } else if (this.admins.containsKey(cpf)) {
            return this.admins.get(cpf);
        }
        return null;
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

    public Department getDepartmentByName(String name) {
        return this.departments.get(name);
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

    public void insertAdmin(String cpf, String name, String numberOfTuition, LocalDate birthdate, String password, Department department) {
        Admin newAdmin = new Admin(cpf, name, numberOfTuition, birthdate, password, department);

        this.admins.put(cpf, newAdmin);
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

    public void removeDepartment(String name) {
        this.departments.remove(name);
    }

    public void addCompany(Company company) {
        this.company = company;
    }
}
