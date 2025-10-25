package view;

import controller.UserController;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class addUserForm {
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

    public addUserForm(UserController controller) {
        // Add click event to button
        addUserButton.addActionListener(e -> {
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

            // Chama o controller
            controller.addMember(cpf, name, numberOfTuition, birthdate);

            JOptionPane.showMessageDialog(panel,
                    "Membro adicionado: " + name + " (" + cpf + ")");
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}