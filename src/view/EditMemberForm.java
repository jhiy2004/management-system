package view;

import controller.UserController;
import model.Member;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EditMemberForm {
    private JPanel panel;
    private JTextField nameField;
    private JTextField cpfField;
    private JTextField numberOfTuitionField;
    private JTextField birthdateField;
    private JLabel nameLabel;
    private JLabel cpfLabel;
    private JLabel numberOfTuitionLabel;
    private JLabel birthdateLabel;
    private JButton editarButton;

    public EditMemberForm(Member member, UserController controller) {
        cpfField.setText(member.getCpf());
        nameField.setText(member.getName());
        numberOfTuitionField.setText(member.getNumberOfTuition());
        birthdateField.setText(member.getBirthdate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        editarButton.addActionListener(e -> {
            String cpf = cpfField.getText().trim();
            String name = nameField.getText().trim();
            String numberOfTuition = numberOfTuitionField.getText().trim();
            String birthdateText = birthdateField.getText().trim();

            // Define o formatter para dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthdate = null;

            try {
                birthdate = LocalDate.parse(birthdateText, formatter);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(panel,
                        "Data inválida! Use o formato dd/MM/yyyy.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = controller.updateMember(member, cpf, name, numberOfTuition, birthdate);

            if (success) {
                JOptionPane.showMessageDialog(panel,
                        "Membro atualizado: " + name + " (" + cpf + ")");
            } else {
                JOptionPane.showMessageDialog(panel,
                        "Falha ao atualizar o membro",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}