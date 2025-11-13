package controller;

import catalog.Catalog;
import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReviewController {
    public Collection<Review> getMemberReviews(Member member, LoginUser user) {
        if (!validateAccessLevel(user)) return null;

        Catalog catalog = Catalog.getInstance();
        Collection<Review> reviews = catalog.getReviews();
        Collection<Review> memberReviews = new ArrayList<>();

        for (Review r : reviews) {
            if (r.getReviewedMember().getCpf().equals(member.getCpf())) {
                System.out.println("Encontrou review do membro");
                memberReviews.add(r);
            }
        }

        return memberReviews;
    }

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
