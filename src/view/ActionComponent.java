package view;

import controller.ActionController;
import model.Action;
import model.Session;

import javax.swing.*;
import java.awt.*;

public class ActionComponent extends JPanel {
    private JLabel nameLabel;
    private JButton editButton;
    private JButton deleteButton;

    public ActionComponent(Action action, ActionController controller, Runnable onUpdate) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        setBackground(new Color(240, 240, 240));
        setPreferredSize(new Dimension(250, 80));

        nameLabel = new JLabel(action.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        editButton = new JButton("Editar");
        editButton.addActionListener(e -> {
            /*
            EditDepartmentForm editForm = new EditDepartmentForm(action, controller);
            JFrame frame = new JFrame("Editar Action");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(editForm.getPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

             */
        });

        deleteButton = new JButton("Deletar");
        deleteButton.addActionListener(e -> {
            /*
            int option = JOptionPane.showConfirmDialog(
                    null,
                    "Tem certeza que deseja deletar este departamento?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (option == JOptionPane.YES_OPTION) {
                boolean success = controller.removeDepartment(department, Session.getCurrentUser());

                if (success) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Departamento removido com sucesso!",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    onUpdate.run();
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Falha ao remover o departamento.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

             */
        });

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        JPanel infoPanel = new JPanel(new GridLayout(1, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(nameLabel);

        add(infoPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.EAST);
    }
}