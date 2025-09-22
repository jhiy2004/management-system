package main;

import javax.swing.*;

import controller.UserController;
import view.addUserForm;

public class Main {
    public static void main(String args[]) {
        UserController userController = new UserController();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            addUserForm form = new addUserForm(userController);
            frame.setContentPane(form.getPanel());

            frame.pack();
            frame.setLocationRelativeTo(null); // center on screen
            frame.setVisible(true);
        });
    }
}
