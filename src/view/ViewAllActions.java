package view;

import controller.ActionController;
import model.Action;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class ViewAllActions {

    private ActionController actionController;
    private JPanel panel;

    public ViewAllActions(ActionController actionController) {
        this.actionController = actionController;
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        refresh();
    }

    private void refresh() {
        panel.removeAll();

        Collection<Action> actions = actionController.getActions();

        if (actions.isEmpty()) {
            JLabel emptyLabel = new JLabel("Nenhuma ação encontrada.");
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(emptyLabel);
        } else {
            for (Action a : actions) {
                ActionComponent component = new ActionComponent(a, actionController, this::refresh);
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
