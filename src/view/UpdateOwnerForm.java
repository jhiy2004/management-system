package view;

import controller.UserController;
import model.Owner;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UpdateOwnerForm {
    private JPanel panel;
    private JTextField nameField;
    private JTextField cpfField;
    private JTextField birthdateField;
    private JTextField numberOfTuitionField;
    private JTextField passwordField;
    private JButton updateOwnerButton;
    private JLabel nameLabel;
    private JLabel cpfLabel;
    private JLabel birthdateLabel;
    private JLabel numberOfTuitionLabel;
    private JLabel passwordLabel;

    public UpdateOwnerForm(Owner owner, UserController controller) {
        // Pre-fill fields with current owner info
        cpfField.setText(owner.getCpf());
        nameField.setText(owner.getName());
        numberOfTuitionField.setText(owner.getNumberOfTuition());
        birthdateField.setText(owner.getBirthdate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        passwordField.setText(owner.getPassword());

        // Button click event
        updateOwnerButton.addActionListener(e -> {
            String cpf = cpfField.getText().trim();
            String name = nameField.getText().trim();
            String numberOfTuition = numberOfTuitionField.getText().trim();
            String birthdateText = birthdateField.getText().trim();
            String password = passwordField.getText().trim();

            // Basic validation
            if (cpf.isBlank() || name.isBlank() || numberOfTuition.isBlank() || birthdateText.isBlank()) {
                JOptionPane.showMessageDialog(panel,
                        "Please fill in all required fields.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parse birthdate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthdate;
            try {
                birthdate = LocalDate.parse(birthdateText, formatter);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(panel,
                        "Invalid date! Use the format dd/MM/yyyy.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Call controller
            boolean success = controller.updateOwner(owner, cpf, name, birthdate, numberOfTuition, password);

            if (success) {
                JOptionPane.showMessageDialog(panel,
                        "User updated successfully: " + name + " (" + cpf + ")");
            } else {
                JOptionPane.showMessageDialog(panel,
                        "Failed to update user. Please check the data and try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}