package view;

import controller.ReviewController;
import model.Action;
import model.Review;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class ViewAllReviews {
    private JPanel panel;
    private ReviewController reviewController;

    public ViewAllReviews(ReviewController reviewController) {
        this.reviewController = reviewController;
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        refresh();
    }

    private void refresh() {
        panel.removeAll();

        Collection<Review> reviews = reviewController.getReviews();

        if (reviews.isEmpty()) {
            JLabel emptyLabel = new JLabel("Nenhuma review encontrada.");
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(emptyLabel);
        } else {
            for (Review r : reviews) {
                ReviewComponent component = new ReviewComponent(r, reviewController, this::refresh);
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
