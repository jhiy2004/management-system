package view;

import controller.UserController;

import javax.swing.*;

public class addUserForm {
    private JPanel panel;
    private JButton addUserButton;
    private JTextField userNameField;
    private JTextField userCpfField;
    private JLabel userCpfLabel;
    private JPanel formPanel;
    private JPanel buttonPanel;
    private JLabel userNameLabel;

    public addUserForm(UserController controller) {
        // Add click event to button
        addUserButton.addActionListener(e -> {
            String cpf = userCpfField.getText();
            String name = userNameField.getText();

            controller.addUser(cpf, name);
            JOptionPane.showMessageDialog(panel,
                    "User added: " + name + " (" + cpf + ")");
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
