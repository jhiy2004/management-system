package model;

import java.time.LocalDate;

public class Admin extends LoginUser{
    private Department department;

    public Admin(String cpf, String name, String numberOfTuition, LocalDate birthdate, String password, Department department) {
        super(cpf, name, numberOfTuition, birthdate, password);
        this.department = department;
    }

    public Admin(String cpf, String name, String numberOfTuition, LocalDate birthdate, String password) {
        super(cpf, name, numberOfTuition, birthdate, password);
        this.department = null;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public boolean hasDepartment() {
        return this.department != null;
    }
}
