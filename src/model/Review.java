package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Review implements Serializable {
    private User reviewer;
    private Member reviewedMember;
    private String optionalContext;
    private List<Action> actions;
    private LocalDate date;

    public Review(LoginUser reviewer, Member reviewedMember, String optionalContext, List<Action> actions, LocalDate date) {
        this.reviewer = reviewer;
        this.date = date;
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

    public boolean inRange(LocalDate dataInicio, LocalDate dataFim) {
        if (date == null || dataInicio == null || dataFim == null) return false;

        return !date.isBefore(dataInicio) && !date.isAfter(dataFim);
    }

}
