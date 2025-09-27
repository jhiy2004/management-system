package controller;

import catalog.Catalog;

public class DepartmentController {
    public void createDepartment(String name) {
        Catalog catalog = Catalog.getInstance();

        if (!validateDepartmentInfo(name)) {
            return;
        }

        catalog.insertDepartment(name);
    }

    private boolean validateDepartmentInfo(String name) {
        Catalog catalog = Catalog.getInstance();

        return !name.isBlank()
                && !catalog.departmentNameExists(name)
                && name.matches("[\\p{L} ]+");
    }
}
