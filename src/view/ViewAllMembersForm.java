package view;

import controller.UserController;
import model.Member;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class ViewAllMembersForm {
    private JPanel panel;
    private UserController userController;

    public ViewAllMembersForm(UserController userController) {
        this.userController = userController;

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        refresh();
    }

    private void refresh() {
        panel.removeAll();

        Collection<Member> members = userController.getMembers();

        if (members.isEmpty()) {
            JLabel emptyLabel = new JLabel("Nenhum membro encontrado.");
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(emptyLabel);
        } else {
            for (Member m : members) {
                MemberComponent component = new MemberComponent(m, userController, this::refresh);
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