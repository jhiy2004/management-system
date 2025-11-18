package model;

import java.time.LocalDate;

public class Member extends User{
    private Department department;

    public Member(String cpf, String name, String numberOfTuition, LocalDate birthdate, Department department) {
        super(cpf, name, numberOfTuition, birthdate);
        this.department = department;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
