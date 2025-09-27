package view;

import controller.DepartmentController;
import javax.swing.*;

public class AddDepartmentForm {
    private JPanel panel;
    private JButton adicionarDepartamentoButton;
    private JTextField departmentNameField;
    private JLabel departmentNameLabel;

    public AddDepartmentForm(DepartmentController controller) {
        // Add click event to button
        adicionarDepartamentoButton.addActionListener(e -> {
            String name = departmentNameField.getText();

            controller.createDepartment(name);
            JOptionPane.showMessageDialog(panel,
                    "Department added: " + name);
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
