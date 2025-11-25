package controller;

import catalog.Catalog;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReviewController {
    public Collection<Review> getMemberReviews(Member member, LocalDate dataInicio, LocalDate dataFim,  LoginUser user) {
        if (!validateAccessLevel(user)) return null;

        Catalog catalog = Catalog.getInstance();
        Collection<Review> reviews = catalog.getReviews();
        Collection<Review> memberReviews = new ArrayList<>();

        for (Review r : reviews) {
            if (r.getReviewedMember().getCpf().equals(member.getCpf()) && r.inRange(dataInicio, dataFim)) {
                System.out.println("Encontrou review do membro");
                memberReviews.add(r);
            }
        }

        return memberReviews;
    }

    public Collection<Review> getMembersReviews(Collection<Member> members, LocalDate dataInicio, LocalDate dataFim, LoginUser user) {
        if (!validateAccessLevel(user)) return null;

        Catalog catalog = Catalog.getInstance();
        Collection<Review> allReviews = catalog.getReviews();
        Collection<Review> memberReviews = new ArrayList<>();

        for (Review review : allReviews) {
            for (Member member : members) {
                if (review.getReviewedMember().getCpf().equals(member.getCpf()) && review.inRange(dataInicio, dataFim)) {
                    memberReviews.add(review);
                    break;
                }
            }
        }

        return memberReviews;
    }



    public Collection<Review> getReviews(LocalDate dataInicio, LocalDate dataFim) {
        Catalog catalog = Catalog.getInstance();
        Collection<Review> reviews = catalog.getReviews();
        Collection<Review> rangeReviews = new ArrayList<>();

        for (Review r : reviews) {
            if (r.inRange(dataInicio, dataFim)) {
                rangeReviews.add(r);
            }
        }

        return rangeReviews;
    }

    public void saveReviews() {
        Catalog catalog = Catalog.getInstance();
        catalog.saveReviews();
    }

    public Collection<Review> getDepartments() {
        Catalog catalog = Catalog.getInstance();

        return catalog.getReviews();
    }

    public boolean makeReview(LoginUser reviewer, Member reviewedMember, String optionalContext, List<Action> actions, LocalDate date) {
        if (!validateAccessLevel(reviewer)) {
            return false;
        }

        Catalog catalog = Catalog.getInstance();

        catalog.insertReview(reviewer, reviewedMember, optionalContext, actions, date);

        return true;
    }

    private boolean validateAccessLevel(LoginUser user) {
        return (user instanceof Admin);
    }
}
