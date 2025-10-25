package main;

import javax.swing.*;

import catalog.Catalog;
import controller.DepartmentController;
import controller.UserController;
import model.Admin;
import model.Department;
import model.Owner;
import model.Session;
import view.*;

import java.time.LocalDate;

public class Main {
    public static void main(String args[]) {
        UserController userController = new UserController();
        DepartmentController departmentController = new DepartmentController();

        Catalog catalog = Catalog.getInstance();


        // Simple test code
        departmentController.createDepartment("Marketing");
        departmentController.createDepartment("Math");

        Department dpt1 = catalog.getDepartmentByName("Marketing");

        userController.addAdmin("63965689002", "jose", "123", LocalDate.parse("2004-04-17"), "abcd1234", dpt1);
        userController.addOwner("47868853808", "joao", "123", LocalDate.parse("2004-04-17"), "abcd1234");

        Admin myAdmin = catalog.getAdminByCpf("63965689002");
        Owner myOwner = catalog.getOwnerByCpf("47868853808");

        Department dpt = catalog.getDepartmentByName("Marketing");
        Session.setCurrentUser(myOwner);
        // End simple test

        boolean firstLogin = false;

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            /*
            UpdateOwnerForm form = new UpdateOwnerForm(myOwner, userController);
            frame.setContentPane(form.getPanel());
             */

            /*
            firstLoginForm form = new firstLoginForm(userController);
            frame.setContentPane(form.getPanel());
             */

            /*
            EditDepartmentForm form = new EditDepartmentForm(dpt, departmentController, Session.getCurrentUser());
            frame.setContentPane(form.getPanel());
             */

            /*
            UpdateAdminForm form = new UpdateAdminForm(myAdmin, userController, departmentController);
            frame.setContentPane(form.getPanel());
            */

            if (!firstLogin) {
                LoginForm form = new LoginForm(frame, userController, departmentController);
                frame.setContentPane(form.getPanel());
            }else {
                firstLoginForm form = new firstLoginForm(userController);
                frame.setContentPane(form.getPanel());
            }

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
