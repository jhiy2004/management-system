package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Review implements Serializable {
    private User reviewer;
    private Member reviewedMember;
    private String optionalContext;
    private List<Action> actions;

    public Review(LoginUser reviewer, Member reviewedMember, String optionalContext, List<Action> actions) {
        this.reviewer = reviewer;
        this.reviewedMember = reviewedMember;
        this.optionalContext = optionalContext;
        this.actions = new ArrayList<>();

        for(Action a : actions) {
            addAction(a);
        }
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public void removeAction(Action action) {
        this.actions.remove(action);
    }

    public User getReviewer() {
        return reviewer;
    }

    public Member getReviewedMember() {
        return reviewedMember;
    }

    public String getOptionalContext() {
        return optionalContext;
    }

    public int getTotalPoints() {
        int sum = 0;
        for (Action a : actions) {
            sum += a.getPoints();
        }

        return sum;
    }

    public int getNumActions () {
        return actions.size();
    }
}
