package view;

import controller.ActionController;

import javax.swing.*;

public class AddActionForm {
    private JPanel panel;
    private JButton adicionarAcaoButton;
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField pointsField;
    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JLabel pointsLabel;

    public AddActionForm(ActionController controller) {
        adicionarAcaoButton.addActionListener(e -> {
            String name = nameField.getText();
            String description = descriptionField.getText();

            int points;
            try {
                points = Integer.parseInt(pointsField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "O campo de pontos deve conter um número inteiro.",
                        "Erro de validação",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            boolean success = controller.createAction(name, description, points);

            if (success) {
                JOptionPane.showMessageDialog(
                        null,
                        "Ação adicionada com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                );

                // Limpa os campos do formulário
                nameField.setText("");
                descriptionField.setText("");
                pointsField.setText("");
            } else {
                JOptionPane.showMessageDialog(
                        panel,
                        "Falha ao adicionar ação. Verifique os campos e tente novamente.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
