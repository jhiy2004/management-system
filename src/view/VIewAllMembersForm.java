package view;

import controller.UserController;

import model.Member;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class VIewAllMembersForm {
    private JPanel panel;

    public VIewAllMembersForm(UserController userController) {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Collection<Member> members = userController.getMembers();

        if (members.isEmpty()) {
            JLabel emptyLabel = new JLabel("Nenhum membro encontrado.");
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(emptyLabel);
        } else {
            for (Member m : members) {
                MemberComponent component = new MemberComponent(m, userController);
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
