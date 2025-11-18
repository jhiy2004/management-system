package view;

import controller.ActionController;
import controller.DepartmentController;
import controller.ReviewController;
import controller.UserController;

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
                    "Departamento adicionado: " + name);
        });
    }

    public AddDepartmentForm(JFrame frame, UserController userController, DepartmentController departmentController, ActionController actionController, ReviewController reviewController) {
        adicionarDepartamentoButton.addActionListener(e -> {
            String name = departmentNameField.getText();

            departmentController.createDepartment(name);
            JOptionPane.showMessageDialog(panel,
                    "Departamento adicionado: " + name);

            AddUserForm addUserForm = new AddUserForm(frame, userController, departmentController, actionController, reviewController);
            frame.setContentPane(addUserForm.getPanel());
            frame.revalidate();
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
