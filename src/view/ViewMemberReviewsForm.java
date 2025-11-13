package view;

import controller.ReviewController;
import model.Member;
import model.Review;
import model.Session;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class ViewMemberReviewsForm {
    private JPanel panel;
    private Member member;
    private ReviewController reviewController;

    public ViewMemberReviewsForm(Member member, ReviewController reviewController) {
        this.member = member;
        this.reviewController = reviewController;
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        refresh();
    }

    private void refresh() {
        panel.removeAll();

        Collection<Review> reviews = reviewController.getMemberReviews(member, Session.getCurrentUser());

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
