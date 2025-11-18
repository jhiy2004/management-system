package view;

import controller.ActionController;
import controller.DepartmentController;
import controller.ReviewController;
import controller.UserController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class UserPanelForm {
    private JButton editarPerfilButton;
    private JButton addMembroButton;
    private JButton verDepartamentosButton;
    private JButton verMembrosButton;
    private JLabel welcomeLabel;
    private JPanel panel;
    private JButton adicionarDepartamentoButton;
    private JButton adicionarAcaoButton;
    private JButton adicionarReviewButton;
    private JButton verAcoesButton;
    private JButton verReviewsButton;
    private JButton verDesempenhoDeMembroButton;
    private JButton verDesempenhoDeDepartamentoButton;

    public UserPanelForm(UserController userController, DepartmentController departmentController, ActionController actionController, ReviewController reviewController) {
        LoginUser user = Session.getCurrentUser();

        welcomeLabel.setText("Olá, " + user.getName());

        editarPerfilButton.addActionListener(e -> {

            JFrame frame = new JFrame("Atualizar Perfil");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            if (user instanceof Admin) {
                frame.setContentPane(new UpdateAdminForm((Admin) user, userController, departmentController).getPanel());
            }else if(user instanceof Owner) {
                frame.setContentPane(new UpdateOwnerForm((Owner) user, userController).getPanel());
            }
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        addMembroButton.addActionListener(e -> {
            // cria a janela
            JFrame frame = new JFrame("Adicionar Membro");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(new AddUserForm(userController, departmentController).getPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        adicionarDepartamentoButton.addActionListener(e -> {
            JFrame frame = new JFrame("Adicionar Departamento");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(new AddDepartmentForm(departmentController).getPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        adicionarAcaoButton.addActionListener(e -> {
            JFrame frame = new JFrame("Adicionar Ação");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(new AddActionForm(actionController).getPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        verDepartamentosButton.addActionListener(e -> {
            JFrame frame = new JFrame("Departamentos");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(new ViewAllDepartmentsForm(departmentController).getPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        verMembrosButton.addActionListener(e -> {
            JFrame frame = new JFrame("Membros");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(new ViewAllMembersForm(userController).getPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        adicionarReviewButton.addActionListener(e -> {
            JFrame frame = new JFrame("Adicionar Review");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(new RegisterReviewForm(userController, actionController, reviewController).getPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        verAcoesButton.addActionListener(e -> {
            JFrame frame = new JFrame("Ver Ações");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(new ViewAllActions(actionController).getPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        verReviewsButton.addActionListener(e -> {
            JFrame frame = new JFrame("Ver Reviews");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(new ViewAllReviewsForm(reviewController).getPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        verDesempenhoDeMembroButton.addActionListener(e -> {
            Collection<Member> members = userController.getMembers();

            JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(verDesempenhoDeMembroButton),
                    "Selecionar Membro", true);
            dialog.setLayout(new BorderLayout(10, 10));

            JComboBox<Member> comboBox = new JComboBox<>(members.toArray(new Member[0]));

            JButton confirmarButton = new JButton("Ver Desempenho");

            confirmarButton.addActionListener(ev -> {
                Member selected = (Member) comboBox.getSelectedItem();
                if (selected != null) {
                    dialog.dispose(); // Fecha o dialog

                    JFrame frame = new JFrame("Ver desempenho do membro");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setContentPane(new ViewMemberReviewsForm(selected, reviewController).getPanel());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            });

            // Layout do dialog
            JPanel centerPanel = new JPanel(new FlowLayout());
            centerPanel.add(new JLabel("Selecione um membro:"));
            centerPanel.add(comboBox);

            dialog.add(centerPanel, BorderLayout.CENTER);
            dialog.add(confirmarButton, BorderLayout.SOUTH);

            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });

        verDesempenhoDeDepartamentoButton.addActionListener(e -> {
            Collection<Department> departments = departmentController.getDepartments();

            JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(verDesempenhoDeDepartamentoButton),
                    "Selecionar Departamento", true);
            dialog.setLayout(new BorderLayout(10, 10));

            JComboBox<Department> comboBox = new JComboBox<>(departments.toArray(new Department[0]));

            JButton confirmarButton = new JButton("Ver Desempenho");

            confirmarButton.addActionListener(ev -> {
                Department selected = (Department) comboBox.getSelectedItem();
                if (selected != null) {
                    dialog.dispose(); // Fecha o dialog

                    JFrame frame = new JFrame("Ver desempenho do departamento");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setContentPane(new ViewDepartmentReviewsForm(selected, userController, reviewController).getPanel());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            });

            // Layout do dialog
            JPanel centerPanel = new JPanel(new FlowLayout());
            centerPanel.add(new JLabel("Selecione um departamento:"));
            centerPanel.add(comboBox);

            dialog.add(centerPanel, BorderLayout.CENTER);
            dialog.add(confirmarButton, BorderLayout.SOUTH);

            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
