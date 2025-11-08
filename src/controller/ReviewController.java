package controller;

import catalog.Catalog;
import model.*;

import java.util.Collection;
import java.util.List;

public class ReviewController {
    public Collection<Review> getReviews() {
        Catalog catalog = Catalog.getInstance();
        return catalog.getReviews();
    }

    public void saveReviews() {
        Catalog catalog = Catalog.getInstance();
        catalog.saveReviews();
    }

    public Collection<Review> getDepartments() {
        Catalog catalog = Catalog.getInstance();

        return catalog.getReviews();
    }

    public boolean makeReview(LoginUser reviewer, Member reviewedMember, String optionalContext, List<Action> actions) {
        if (!validateAccessLevel(reviewer)) {
            return false;
        }

        Catalog catalog = Catalog.getInstance();

        catalog.insertReview(reviewer, reviewedMember, optionalContext, actions);

        return true;
    }

    private boolean validateAccessLevel(LoginUser user) {
        return (user instanceof Admin);
    }
}
