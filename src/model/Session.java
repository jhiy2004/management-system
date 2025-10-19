package model;

public class Session {
    private static LoginUser currentUser;

    public static void setCurrentUser(LoginUser user) { currentUser = user; }
    public static LoginUser getCurrentUser() { return currentUser; }
}
