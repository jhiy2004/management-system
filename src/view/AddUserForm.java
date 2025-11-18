package view;

import controller.ActionController;
import controller.DepartmentController;
import controller.ReviewController;
import controller.UserController;
import model.Department;
import model.Session;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;

public class AddUserForm {
    private JPanel panel;
    private JButton addUserButton;
    private JTextField userNameField;
    private JTextField userCpfField;
    private JLabel userCpfLabel;
    private JPanel formPanel;
    private JPanel buttonPanel;
    private JLabel userNameLabel;
    private JTextField numberOfTuitionField;
    private JLabel numberOfTuitionLabel;
    private JLabel birthdateLabel;
    private JTextField birthdateField;
    private JComboBox typeCombo;
    private JLabel typeLabel;
    private JComboBox departmentCombo;
    private JLabel departmentLabel;
    private JPasswordField passwordField;
    private JLabel passwordLabel;

    public AddUserForm(UserController userController, DepartmentController departmentController) {
        Collection<Department> departments = departmentController.getDepartments();
        for (Department d : departments) {
            departmentCombo.addItem(d);
        }

        typeCombo.addItemListener(event -> {
            if (event.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                String selected = event.getItem().toString();

                if (selected.equals("Admin")) {
                    passwordLabel.setVisible(true);
                    passwordField.setVisible(true);
                } else {
                    passwordLabel.setVisible(false);
                    passwordField.setVisible(false);
                }

                formPanel.revalidate();
                formPanel.repaint();
            }
        });


        // Add click event to button
        addUserButton.addActionListener(e -> {
            String cpf = userCpfField.getText().trim();
            String name = userNameField.getText().trim();
            String numberOfTuition = numberOfTuitionField.getText().trim();
            String birthdateText = birthdateField.getText().trim();
            String type = typeCombo.getSelectedItem().toString();
            Department department = (Department) departmentCombo.getSelectedItem();

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
            if (type.equals("Admin")) {
                password = new String(passwordField.getPassword());

                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(panel,
                            "Admins precisam de senha!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                userController.addAdmin(cpf, name, numberOfTuition, birthdate, password, department);

                JOptionPane.showMessageDialog(panel,
                        "Admin adicionado: " + name + " (" + cpf + ")");
            }else {
                userController.addMember(cpf, name, numberOfTuition, birthdate, department);

                JOptionPane.showMessageDialog(panel,
                        "Membro adicionado: " + name + " (" + cpf + ")");
            }
        });
    }

    public AddUserForm(JFrame frame, UserController userController, DepartmentController departmentController, ActionController actionController, ReviewController reviewController) {
        Collection<Department> departments = departmentController.getDepartments();
        for (Department d : departments) {
            departmentCombo.addItem(d);
        }

        typeCombo.addItemListener(event -> {
            if (event.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                String selected = event.getItem().toString();

                if (selected.equals("Admin")) {
                    passwordLabel.setVisible(true);
                    passwordField.setVisible(true);
                } else {
                    passwordLabel.setVisible(false);
                    passwordField.setVisible(false);
                }

                formPanel.revalidate();
                formPanel.repaint();
            }
        });


        // Add click event to button
        addUserButton.addActionListener(e -> {
            String cpf = userCpfField.getText().trim();
            String name = userNameField.getText().trim();
            String numberOfTuition = numberOfTuitionField.getText().trim();
            String birthdateText = birthdateField.getText().trim();
            String type = typeCombo.getSelectedItem().toString();
            Department department = (Department) departmentCombo.getSelectedItem();

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
            if (type.equals("Admin")) {
                password = new String(passwordField.getPassword());

                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(panel,
                            "Admins precisam de senha!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                userController.addAdmin(cpf, name, numberOfTuition, birthdate, password, department);

                Session.setCurrentUser(userController.getAdmins().iterator().next());
                JOptionPane.showMessageDialog(panel,
                        "Admin adicionado: " + name + " (" + cpf + ")");


                UserPanelForm userPanelForm = new UserPanelForm(userController, departmentController, actionController, reviewController);
                frame.setContentPane(userPanelForm.getPanel());
                frame.revalidate();
            }else {
                JOptionPane.showMessageDialog(panel,
                        "Durante o primeiro login só podem ser cadastrado admin!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}