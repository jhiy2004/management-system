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

        boolean firstLogin = false;

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Management System");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.out.println("Salvando dados...");
                    userController.saveUsers();
                    departmentController.saveUsers();

                    frame.dispose();
                    System.exit(0);
                }
            });

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
