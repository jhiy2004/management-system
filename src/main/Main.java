package main;

import javax.swing.*;

import controller.ActionController;
import controller.DepartmentController;
import controller.ReviewController;
import controller.UserController;
import view.*;

public class Main {
    public static void main(String args[]) {
        UserController userController = new UserController();
        DepartmentController departmentController = new DepartmentController();
        ActionController actionController = new ActionController();
        ReviewController reviewController = new ReviewController();

        boolean firstLogin = userController.getCompany() == null;

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Management System");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.out.println("Salvando dados...");
                    userController.saveCompany();
                    userController.saveUsers();
                    departmentController.saveUsers();
                    actionController.saveActions();
                    reviewController.saveReviews();

                    frame.dispose();
                    System.exit(0);
                }
            });

            if (!firstLogin) {
                LoginForm form = new LoginForm(frame, userController, departmentController, actionController, reviewController);
                frame.setContentPane(form.getPanel());
            }else {
                FirstLoginForm form = new FirstLoginForm(frame, userController, departmentController, actionController, reviewController);
                frame.setContentPane(form.getPanel());
            }

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
