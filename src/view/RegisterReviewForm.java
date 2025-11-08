package view;

import controller.ActionController;
import controller.ReviewController;
import controller.UserController;
import model.Action;
import model.LoginUser;
import model.Member;
import model.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Collection;

public class RegisterReviewForm {
    private JPanel panel;
    private JLabel reviewerLabel;
    private JLabel memberLabel;
    private JLabel actionLabel;
    private JComboBox memberCombobox;
    private JComboBox actionCombobox;
    private JButton adicionarAcaoButton;
    private JTable actionsTable;
    private JButton cadastrarReviewButton;
    private JTextField reviewField;
    private JTextField contextField;
    private JLabel contextLabel;

    public RegisterReviewForm(
            UserController userController,
            ActionController actionController,
            ReviewController reviewController
    ) {
        LoginUser reviewer = Session.getCurrentUser();

        reviewField.setText(reviewer.getName());

        Collection<Member> members = userController.getMembers();
        for (Member m : members) {
            memberCombobox.addItem(m);
        }

        Collection<Action> actions = actionController.getActions();
        for (Action a : actions) {
            actionCombobox.addItem(a);
        }

        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[]{"ActionObj", "Nome", "Descrição", "Pontos"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        actionsTable.setModel(tableModel);
        actionsTable.getColumnModel().getColumn(0).setMinWidth(0);
        actionsTable.getColumnModel().getColumn(0).setMaxWidth(0);
        actionsTable.getColumnModel().getColumn(0).setWidth(0);

        java.util.List<Action> selectedActions = new java.util.ArrayList<>();

        adicionarAcaoButton.addActionListener(e -> {
            Action selected = (Action) actionCombobox.getSelectedItem();
            if (selected == null) return;
            if (selectedActions.contains(selected)) {
                JOptionPane.showMessageDialog(panel, "Esta ação já foi adicionada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            selectedActions.add(selected);
            tableModel.addRow(new Object[]{
                    selected,
                    selected.getName(),
                    selected.getDescription(),
                    selected.getPoints()
            });
        });

        actionsTable.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) {
                    int row = actionsTable.getSelectedRow();
                    if (row >= 0) {
                        Action a = (Action) tableModel.getValueAt(row, 0);
                        selectedActions.remove(a);
                        tableModel.removeRow(row);
                    }
                }
            }
        });

        cadastrarReviewButton.addActionListener(e -> {
            Member member = (Member) memberCombobox.getSelectedItem();
            String context = contextField.getText();
            if (reviewer == null || member == null) {
                JOptionPane.showMessageDialog(panel, "Selecione um revisor e um membro.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            reviewController.makeReview(
                    reviewer,
                    member,
                    context,
                    selectedActions
            );
            JOptionPane.showMessageDialog(panel, "Review cadastrada!");
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}