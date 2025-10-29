package view;

import controller.DepartmentController;
import controller.UserController;
import model.Department;
import model.Session;
import model.User;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EditDepartmentForm {
    private JButton editarDepartamentoButton;
    private JTextField nameField;
    private JLabel nameLabel;
    private JPanel panel;

    public EditDepartmentForm(Department department, DepartmentController controller) {
        nameField.setText(department.getName());

        // Add click event to button
        editarDepartamentoButton.addActionListener(e -> {
            String name = nameField.getText().trim();

            // Chama o controller
            if (!controller.editDepartment(department, name, Session.getCurrentUser())) {
                JOptionPane.showMessageDialog(panel,
                        "Algum erro ocorreu",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(panel,
                    "Departamento editado!");
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
