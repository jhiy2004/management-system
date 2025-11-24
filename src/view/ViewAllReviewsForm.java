package view;

import controller.ReviewController;
import model.Review;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Collection;

public class ViewAllReviewsForm {
    private JPanel panel;
    private ReviewController reviewController;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public ViewAllReviewsForm(LocalDate dataInicio, LocalDate dataFim, ReviewController reviewController) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.reviewController = reviewController;
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        refresh();
    }

    private void refresh() {
        panel.removeAll();

        Collection<Review> reviews = reviewController.getReviews(dataInicio, dataFim);

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
