package view;

import controller.DepartmentController;
import model.Department;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class ViewAllDepartmentsForm {
    private JPanel panel;
    private DepartmentController departmentController;

    public ViewAllDepartmentsForm(DepartmentController departmentController) {
        this.departmentController = departmentController;

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        refresh();
    }

    private void refresh() {
        panel.removeAll();

        Collection<Department> departments = departmentController.getDepartments();

        if (departments.isEmpty()) {
            JLabel emptyLabel = new JLabel("Nenhum departamento encontrado.");
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(emptyLabel);
        } else {
            for (Department d : departments) {
                DepartmentComponent component = new DepartmentComponent(d, departmentController, this::refresh);
                component.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(component);
                panel.add(Box.createVerticalStrut(8));
            }
        }

        panel.revalidate();
        panel.repaint();
    }

    public JPanel getPanel() {
        return panel;
    }
}