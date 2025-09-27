package main;

import javax.swing.*;

import catalog.Catalog;
import controller.DepartmentController;
import controller.UserController;
import model.Owner;
import view.AddDepartmentForm;
import view.UpdateOwnerForm;

import java.time.LocalDate;

public class Main {
    public static void main(String args[]) {
        UserController userController = new UserController();
        DepartmentController departmentController = new DepartmentController();

        // Simple test code
        userController.addOwner("47868853808", "jose", "123", LocalDate.parse("2004-04-17"), "abcd1234");

        Catalog catalog = Catalog.getInstance();
        Owner myOwner = catalog.getOwnerByCpf("47868853808");
        // End simple test

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            UpdateOwnerForm form = new UpdateOwnerForm(myOwner, userController);
            frame.setContentPane(form.getPanel());

            frame.pack();
            frame.setLocationRelativeTo(null); // center on screen
            frame.setVisible(true);
        });
    }
}
