package view;

import controller.ActionController;
import controller.DepartmentController;
import controller.ReviewController;
import controller.UserController;
import model.Department;
import model.LoginUser;
import model.Session;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddOwnerForm {
    private JPanel panel;
    private JPanel formPanel;
    private JLabel userNameLabel;
    private JTextField userNameField;
    private JLabel userCpfLabel;
    private JTextField userCpfField;
    private JTextField numberOfTuitionField;
    private JLabel numberOfTuitionLabel;
    private JLabel birthdateLabel;
    private JTextField birthdateField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton adicionarOwnerButton;

    public AddOwnerForm(JFrame frame, UserController userController, DepartmentController departmentController, ActionController actionController, ReviewController reviewController) {
        adicionarOwnerButton.addActionListener(e -> {
            String cpf = userCpfField.getText().trim();
            String name = userNameField.getText().trim();
            String numberOfTuition = numberOfTuitionField.getText().trim();
            String birthdateText = birthdateField.getText().trim();

            // Define o formatter para dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthdate = null;

            try {
                birthdate = LocalDate.parse(birthdateText, formatter);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(panel,
                        "Data inválida! Use o formato dd/MM/yyyy.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String password = null;
            password = new String(passwordField.getPassword());

            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(panel,
                        "Preecha a senha!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                    return;
            }

            if(userController.addOwner(cpf, name, numberOfTuition, birthdate, password)){
                JOptionPane.showMessageDialog(panel,
                        "Owner adicionado: " + name + " (" + cpf + ")");

                LoginUser owner = userController.login(cpf, password);
                Session.setCurrentUser(owner);

                UserPanelForm userPanelForm = new UserPanelForm(userController, departmentController, actionController, reviewController);

                frame.setContentPane(userPanelForm.getPanel());
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(panel,
                        "Falha ao inserir Owner",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }


        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
