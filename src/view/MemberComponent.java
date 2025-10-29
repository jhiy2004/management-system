package view;

import controller.UserController;
import model.Member;
import model.Session;

import javax.swing.*;
import java.awt.*;

public class MemberComponent extends JPanel {
    private JLabel nameLabel;
    private JButton editButton;
    private JButton deleteButton;

    public MemberComponent(Member member, UserController controller, Runnable onUpdate) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        setBackground(new Color(240, 240, 240));
        setPreferredSize(new Dimension(250, 80));

        nameLabel = new JLabel(member.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        editButton = new JButton("Editar");
        editButton.addActionListener(e -> {
            EditMemberForm editForm = new EditMemberForm(member, controller);
            JFrame frame = new JFrame("Editar Membro");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(editForm.getPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        deleteButton = new JButton("Deletar");
        deleteButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(
                    null,
                    "Tem certeza que deseja deletar este membro?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (option == JOptionPane.YES_OPTION) {
                boolean success = controller.removeMember(member.getCpf(), Session.getCurrentUser());

                if (success) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Membro removido com sucesso!",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    onUpdate.run();
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Falha ao remover o membro.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
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