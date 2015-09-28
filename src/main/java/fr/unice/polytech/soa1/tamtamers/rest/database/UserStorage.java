package fr.unice.polytech.soa1.tamtamers.rest.database;

import fr.unice.polytech.soa1.tamtamers.rest.entity.User;

import java.util.Collection;
import java.util.HashMap;

public class UserStorage {
    private static HashMap<Integer, User> users = new HashMap<Integer, User>();

    // USER
    public static User createUser(User user) {
        user.setId(users.size() + 1);
        users.put(user.getId(), user);
        return user;
    }
    public static void deleteUser(Integer id) {
        users.remove(id);
    }
    public static User getUser(Integer id) {
        return users.get(id);
    }
    public static Collection<User> findAllUsers() {
        return users.values();
    }

    static {
        User user = new User();
        user.setAddress1("00 - Rue de Azerty");
        user.setAddress1("Bâtiment R - Etage 1.25");
        user.setFullname("Root User");
        user.setZc("57872");
        user.setState("France");
        user.setPhone("+33705214896");

        UserStorage.createUser(user);
    }
}
