package view;

import controller.ActionController;
import controller.DepartmentController;
import controller.UserController;
import model.Admin;
import model.LoginUser;
import model.Owner;
import model.Session;

import javax.swing.*;

public class UserPanelForm {
    private JButton editarPerfilButton;
    private JButton addMembroButton;
    private JButton verDepartamentosButton;
    private JButton verMembrosButton;
    private JLabel welcomeLabel;
    private JPanel panel;
    private JButton adicionarDepartamentoButton;
    private JButton adicionarAcaoButton;

    public UserPanelForm(UserController userController, DepartmentController departmentController, ActionController actionController) {
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
            frame.setContentPane(new addUserForm(userController).getPanel());
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
            frame.setContentPane(new ViewAllDepartmentsForm(departmentController, user).getPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        verMembrosButton.addActionListener(e -> {
            JFrame frame = new JFrame("Membros");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(new VIewAllMembersForm(userController).getPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
