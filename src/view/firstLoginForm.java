package view;

import controller.UserController;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class firstLoginForm {
    private JButton cadastrarCompanhiaButton;
    private JTextField nameField;
    private JTextField cnpjField;
    private JTextField emailField;
    private JTextField dateField;
    private JLabel nameLabel;
    private JLabel cnpjLabel;
    private JLabel emailLabel;
    private JLabel dateLabel;
    private JPanel panel;

    public firstLoginForm(UserController controller) {
        // Add click event to button
        cadastrarCompanhiaButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String cnpj = cnpjField.getText().trim();
            String email = emailField.getText().trim();
            String dateText = dateField.getText().trim();

            // Define o formatter para dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date;

            try {
                date = LocalDate.parse(dateText, formatter);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(panel,
                        "Data inválida! Use o formato dd/MM/yyyy.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Chama o controller
            if (!controller.createCompany(name, cnpj, email, date)) {
                JOptionPane.showMessageDialog(panel,
                        "Alguma informação está incorreta",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(panel,
                    "Companhia cadastrada: " + name + " (" + cnpj + ")");
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}