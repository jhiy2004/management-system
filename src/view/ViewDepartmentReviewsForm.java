package view;

import controller.ReviewController;
import controller.UserController;
import model.Department;
import model.Member;
import model.Review;
import model.Session;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class ViewDepartmentReviewsForm {
    private JPanel panel;
    private Department department;
    private ReviewController reviewController;
    private UserController userController;

    public ViewDepartmentReviewsForm(Department department, UserController userController, ReviewController reviewController) {
        this.department = department;
        this.userController = userController;
        this.reviewController = reviewController;
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        refresh();
    }

    private void refresh() {
        panel.removeAll();

        Collection<Member> members = userController.getMembersDepartment(department);
        Collection<Review> reviews = reviewController.getMembersReviews(members, Session.getCurrentUser());

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