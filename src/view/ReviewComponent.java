package view;

import controller.ReviewController;
import model.Action;
import model.Review;

import javax.swing.*;
import java.awt.*;

public class ReviewComponent extends JPanel {
    private JLabel line1;
    private JLabel line2;
    private JLabel line3;
    private JButton editButton;
    private JButton deleteButton;

    public ReviewComponent(Review review, ReviewController controller, Runnable onUpdate) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        setBackground(new Color(240, 240, 240));
        setPreferredSize(new Dimension(350, 90));

        String reviewerName = review.getReviewer().getName();
        String memberName = review.getReviewedMember().getName();
        String context = review.getOptionalContext();

        int totalPoints = review.getTotalPoints();

        int actionsCount = review.getNumActions();

        line1 = new JLabel(reviewerName + " → " + memberName);
        line1.setFont(new Font("Arial", Font.BOLD, 14));

        line2 = new JLabel(actionsCount + " ações (" + totalPoints + " pts)");
        line2.setFont(new Font("Arial", Font.PLAIN, 12));

        line3 = new JLabel(context == null ? "" : context);
        line3.setFont(new Font("Arial", Font.ITALIC, 12));

        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(line1);
        infoPanel.add(line2);
        if (context != null && !context.isBlank()) {
            infoPanel.add(line3);
        }

        editButton = new JButton("Editar");
        deleteButton = new JButton("Deletar");

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        add(infoPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.EAST);
    }
}