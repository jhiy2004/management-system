package catalog;

import model.Admin;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
    private static Catalog instance = null;
    private final Map<String, Admin> admins;
    private final Map<String, User> users;

    public static Catalog getInstance() {
        if (instance == null) {
            instance = new Catalog();
        }
        return instance;
    }

    private Catalog() {
        this.admins = new HashMap<>();
        this.users = new HashMap<>();
    }

    public Map<String, Admin> getAdmins() {
        return admins;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public Admin getAdminByCpf(String cpf) {
        return this.admins.get(cpf);
    }

    public User getUserByCpf(String cpf) {
        return this.users.get(cpf);
    }

    public boolean cpfExists(String cpf) {
        return this.users.containsKey(cpf) || this.admins.containsKey(cpf);
    }

    public void insertUser(String cpf, String name) {
        User newUser = new User(cpf, name);

        this.users.put(cpf, newUser);
    }

    public void removeUser(String cpf) {
        this.users.remove(cpf);
    }
}
