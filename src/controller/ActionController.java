package controller;

import catalog.Catalog;

public class ActionController {
    public void saveActions() {
        Catalog catalog = Catalog.getInstance();
        catalog.saveActions();
    }

    public boolean createAction(String name, String description, int points) {
        Catalog catalog = Catalog.getInstance();

        if(!validateName(name)) {
            return false;
        }

        if(!validateDescription(description)) {
            return false;
        }

        if(!validatePointsValue(points)) {
            return false;
        }

        catalog.insertAction(name, description, points);
        return true;
    }

    public boolean validateName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public boolean validateDescription(String description) {
        return description != null && !description.trim().isEmpty();
    }


    public boolean validatePointsValue(int points) {
        return points != 0;
    }

}
