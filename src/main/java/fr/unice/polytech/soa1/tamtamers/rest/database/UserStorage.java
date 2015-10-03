package fr.unice.polytech.soa1.tamtamers.rest.database;

import fr.unice.polytech.soa1.tamtamers.rest.entity.User;

import java.time.Period;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {

	// this mocks a database.
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
	public static User findUserById(Integer id) {
		return users.get(id);
	}

	// TODO : Marche pas
	public static Collection<User> findUserByName(String search) {
        String[] tmp = search.split(" ");

        HashMap<Integer, User> result = new HashMap<Integer, User>();
        for(String match : tmp) {
            for(Map.Entry<Integer, User> entry : users.entrySet()) {
                Integer key = entry.getKey();
                User value = entry.getValue();
                if(value.getFullname().equals(match) && !result.containsKey(key)) result.put(key, value);
            }
        }
        return result.values();
    }
	public static Collection<User> findAllUsers() {
		return users.values();
	}

	static {
		User user = new User();
		user.setAddress1("00 - Rue de Azerty");
		user.setAddress2("Bâtiment R - Etage 1.25");
		user.setFullname("Root User");
		user.setZc("57872");
		user.setState("France");
		user.setPhone("+33705214896");

		UserStorage.createUser(user);

		user = new User();
		user.setAddress1("01 - Rue de la zouk");
		user.setAddress2("");
		user.setFullname("Bob Razowsky");
		user.setZc("06660");
		user.setState("France");
		user.setPhone("+33706660734");

		UserStorage.createUser(user);

	}

}
