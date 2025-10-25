package view;

import controller.DepartmentController;
import controller.UserController;
import model.Admin;
import model.Department;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.List;

public class UpdateAdminForm {
    private JPanel panel;
    private JTextField nameField;
    private JTextField cpfField;
    private JTextField birthdateField;
    private JTextField numberOfTuitionField;
    private JTextField passwordField;
    private JLabel nameLabel;
    private JLabel cpfLabel;
    private JLabel birthdateLabel;
    private JLabel numberOfTuitionLabel;
    private JLabel passwordLabel;
    private JLabel departmentLabel;
    private JComboBox departmentCombo;
    private JButton editarButton;

    public UpdateAdminForm(Admin admin, UserController userController, DepartmentController departmentController) {
        // Pre-fill fields with current owner info
        cpfField.setText(admin.getCpf());
        nameField.setText(admin.getName());
        numberOfTuitionField.setText(admin.getNumberOfTuition());
        birthdateField.setText(admin.getBirthdate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        passwordField.setText(admin.getPassword());

        // Populate combo box
        Collection<Department> departments = departmentController.getDepartments();
        for (Department dept : departments) {
            departmentCombo.addItem(dept.getName());
        }

        // Pre-select the admin's current department (if any)
        if (admin.getDepartment() != null) {
            departmentCombo.setSelectedItem(admin.getDepartment().getName());
        }

        // Button click event
        editarButton.addActionListener(e -> {
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

            // Get selected department by name
            String selectedDepartmentName = (String) departmentCombo.getSelectedItem();
            Department selectedDpt = departmentController.getDepartmentByName(selectedDepartmentName);

            // Call controller
            boolean success = userController.updateAdmin(admin, cpf, name, birthdate, numberOfTuition, password, selectedDpt);

            if (success) {
                JOptionPane.showMessageDialog(panel,
                        "Admin atualizado com sucesso: " + name + " (" + cpf + ")");
            } else {
                JOptionPane.showMessageDialog(panel,
                        "Falha ao atualizar o admin.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JPanel getPanel() {
        return this.panel;
    }
}
