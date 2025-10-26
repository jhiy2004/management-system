package model;

import java.io.Serializable;

public class Action implements Serializable {
    private String name;
    private String description;
    private int points;

    public Action(String name, String description, int points) {
        this.name = name;
        this.description = description;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}