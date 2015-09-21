package fr.unice.polytech.soa1.cookbook.rest;


import java.util.Collection;
import java.util.HashMap;

public class Storage {

	// this mocks a database.
	private static HashMap<Integer, Tamtam> contents = new HashMap<Integer, Tamtam>();

	public static void create(Tamtam tamtam) {
		contents.put(tamtam.getId(), tamtam);
	}


	public static Tamtam read(Integer id) {
		return contents.get(id);
	}

	public static void delete(Integer id) {
		contents.remove(id);
	}

	public static Collection<Tamtam> findAll() {
		return contents.values();
	}


	static {
		Tamtam tamtam = new Tamtam(1);
		tamtam.setName("");
		tamtam.setBrand("");
		tamtam.setSkin("");
		tamtam.setWood("");


		Storage.create(tamtam);
	}

}
