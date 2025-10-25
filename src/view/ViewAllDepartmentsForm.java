package view;

import controller.DepartmentController;
import model.Department;
import model.LoginUser;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class ViewAllDepartmentsForm {
    private JPanel panel;

    public ViewAllDepartmentsForm(DepartmentController departmentController, LoginUser user) {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Collection<Department> departments = departmentController.getDepartments();

        if (departments.isEmpty()) {
            JLabel emptyLabel = new JLabel("Nenhum departamento encontrado.");
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(emptyLabel);
        } else {
            for (Department d : departments) {
                DepartmentComponent component = new DepartmentComponent(d, departmentController, user);
                component.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(component);
                panel.add(Box.createVerticalStrut(8));
            }
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
