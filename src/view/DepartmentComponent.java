package view;

import controller.DepartmentController;
import model.Department;
import model.LoginUser;

import javax.swing.*;
import java.awt.*;

public class DepartmentComponent extends JPanel {
    private JLabel nameLabel;
    private JButton editButton;

    public DepartmentComponent(Department department, DepartmentController controller, LoginUser user) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        setBackground(new Color(240, 240, 240));
        setPreferredSize(new Dimension(250, 80));

        nameLabel = new JLabel(department.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        editButton = new JButton("Editar");
        editButton.addActionListener(e -> {
            // cria e abre a nova janela
            EditDepartmentForm editForm = new EditDepartmentForm(department, controller, user);
            JFrame frame = new JFrame("Editar Departamento");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(editForm.getPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        JPanel infoPanel = new JPanel(new GridLayout(1, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(nameLabel);

        add(infoPanel, BorderLayout.CENTER);
        add(editButton, BorderLayout.EAST);
    }
}