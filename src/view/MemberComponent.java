package view;

import controller.DepartmentController;
import controller.UserController;
import model.Member;
import model.LoginUser;

import javax.swing.*;
import java.awt.*;

public class MemberComponent extends JPanel {
    private JLabel nameLabel;
    private JButton editButton;

    public MemberComponent(Member member, UserController controller) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        setBackground(new Color(240, 240, 240));
        setPreferredSize(new Dimension(250, 80));

        nameLabel = new JLabel(member.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        editButton = new JButton("Editar");
        editButton.addActionListener(e -> {
            // cria e abre a nova janela
            EditMemberForm editForm = new EditMemberForm(member, controller);
            JFrame frame = new JFrame("Editar Membro");
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
