package fr.unice.polytech.soa1.cookbook.rest;


import java.util.Collection;
import java.util.HashMap;

public class Storage {

	// this mocks a database.
	private static HashMap<Integer, Tamtam> tamtams = new HashMap<Integer, Tamtam>();
	private static HashMap<Integer, Decoration> decorations = new HashMap<Integer, Decoration>();
	private static HashMap<Integer, Shipment> shipments = new HashMap<Integer, Shipment>();

	public static void createTamtam(Tamtam tamtam) {
		tamtams.put(tamtam.getId(), tamtam);
	}
	public static Tamtam getTamtam(Integer id) {
		return tamtams.get(id);
	}
	public static void deleteTamtam(Integer id) {
		tamtams.remove(id);
	}
	public static Collection<Tamtam> findAllTamtams() {
		return tamtams.values();
	}

	public static void createDecoration(Decoration decoration) {
		decorations.put(decoration.getId(), decoration);
	}
	public static Decoration getDecoration(Integer id) {
		return decorations.get(id);
	}
	public static void deleteDecoration(Integer id) {
		decorations.remove(id);
	}
	public static Collection<Decoration> findAllDecorations() {
		return decorations.values();
	}

	public static void createShipment(Shipment shipment) {
		shipments.put(shipment.getId(), shipment);
	}
	public static Shipment getShipment(Integer id) {
		return shipments.get(id);
	}
	public static void deleteShipment(Integer id) {
		shipments.remove(id);
	}
	public static Collection<Shipment> findAllShipments() {
		return shipments.values();
	}

	static {
		Tamtam tamtam = new Tamtam(1);
		tamtam.setName("Tamtam 1");
		tamtam.setBrand("Tamtamarque");
		tamtam.setSkin("Chèvre");
		tamtam.setWood("Acajou");

		Storage.create(tamtam);

		tamtam = new Tamtam(2);
		tamtam.setName("Tamtam 2");
		tamtam.setBrand("Tamtamarque");
		tamtam.setSkin("Biche");
		tamtam.setWood("Acajou");

		Storage.create(tamtam);

		tamtam = new Tamtam(3);
		tamtam.setName("Tamtam 3");
		tamtam.setBrand("Tamtamarque");
		tamtam.setSkin("Synthétique");
		tamtam.setWood("Pommier");

		Storage.create(tamtam);


		tamtam = new Tamtam(4);
		tamtam.setName("Tamtam 4");
		tamtam.setBrand("Tamama");
		tamtam.setSkin("Synthétique");
		tamtam.setWood("Pommier");

		Storage.create(tamtam);

		tamtam = new Tamtam(5);
		tamtam.setName("Tamtam 4 mé en mieu");
		tamtam.setBrand("Tamama");
		tamtam.setSkin("Synthétique");
		tamtam.setWood("Pommier");

		Storage.create(tamtam);

		tamtam = new Tamtam(6);
		tamtam.setName("Tamtam 6");
		tamtam.setBrand("Tamama");
		tamtam.setSkin("Chèvre");
		tamtam.setWood("Pommier");

		Storage.create(tamtam);

		tamtam = new Tamtam(7);
		tamtam.setName("Tamtamultime");
		tamtam.setBrand("Epictamtam");
		tamtam.setSkin("Chèvre");
		tamtam.setWood("Acajou");

		Storage.create(tamtam);
	}

}
