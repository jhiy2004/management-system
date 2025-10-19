package view;

import controller.UserController;
import model.Department;
import model.LoginUser;
import model.Session;
import model.User;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LoginForm {
    private JButton loginButton;
    private JTextField cpfField;
    private JPasswordField passwordField;
    private JLabel cpfLabel;
    private JLabel passwordLabel;
    private JPanel panel;

    public LoginForm(UserController controller) {
        loginButton.addActionListener(e -> {
            String cpf = cpfField.getText().trim();
            String password = passwordField.getText().trim();

            // Basic validation
            if (cpf.isBlank() || password.isBlank()) {
                JOptionPane.showMessageDialog(panel,
                        "Please fill in all required fields.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Call controller
            LoginUser user = controller.login(cpf, password);

            if (user != null) {
                JOptionPane.showMessageDialog(panel,
                        "Login realizado com sucesso: " + user.getName());
                Session.setCurrentUser(user);
            } else {
                JOptionPane.showMessageDialog(panel,
                        "Falha no login",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
