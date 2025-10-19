package controller;

import catalog.Catalog;
import model.Department;
import model.Owner;
import model.User;

import java.util.Collection;

public class DepartmentController {
    public Collection<Department> getDepartments() {
        Catalog catalog = Catalog.getInstance();

        return catalog.getDepartments();
    }

    public Department getDepartmentByName(String name) {
        Catalog catalog = Catalog.getInstance();

        return catalog.getDepartmentByName(name);
    }

    public void createDepartment(String name) {
        Catalog catalog = Catalog.getInstance();

        if (!validateDepartmentInfo(name)) {
            return;
        }

        catalog.insertDepartment(name);
    }

    public boolean removeDepartment(Department d, User user) {
        if (!getCredentialAccessLevel(user)) {
            return false;
        }

        Catalog catalog = Catalog.getInstance();

        if (catalog.departmentNameExists(d.getName())) {
            return false;
        }

        catalog.removeDepartment(d.getName());
        return true;
    }

    public boolean editDepartment(Department d, String name, User user) {
        if (!getCredentialAccessLevel(user)) {
            return false;
        }

        Catalog catalog = Catalog.getInstance();

        if (catalog.departmentNameExists(name)) {
            return false;
        }

        String tmpName = d.getName();

        catalog.removeDepartment(tmpName);
        d.setName(name);
        catalog.insertDepartment(name);

        return true;
    }

    private boolean getCredentialAccessLevel(User user) {
        return validOwner(user);
    }

    private boolean validOwner(User user) {
        return (user instanceof Owner);
    }

    private boolean validateDepartmentInfo(String name) {
        Catalog catalog = Catalog.getInstance();

        return !name.isBlank()
                && !catalog.departmentNameExists(name)
                && name.matches("[\\p{L} ]+");
    }
}
