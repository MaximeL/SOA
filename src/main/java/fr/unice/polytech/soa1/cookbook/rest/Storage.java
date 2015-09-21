package fr.unice.polytech.soa1.cookbook.rest;


import java.time.Period;
import java.util.Collection;
import java.util.HashMap;

public class Storage {

	// this mocks a database.
	private static HashMap<Integer, Tamtam> tamtams = new HashMap<Integer, Tamtam>();

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

	static {
		Shipment UPS = new Shipment(1);
		UPS.setName("UPS");
		UPS.setDelay(Period.ofDays(2));
		UPS.setPrice(9.0);

		Shipment Colissimo = new Shipment(2);
		Colissimo.setName("Colissimo");
		Colissimo.setDelay(Period.ofDays(10));
		Colissimo.setPrice(0);

		Shipment FedEx = new Shipment(3);
		FedEx.setName("FedEx");
		FedEx.setDelay(Period.ofDays(1));
		FedEx.setPrice(15);

		Decoration leopard = new Decoration(1);
		leopard.setName("Design Léopard");
		leopard.setPrice(0.12);

		Decoration zebre = new Decoration(2);
		zebre.setName("Design zébré");
		zebre.setPrice(2);

		Decoration militaire = new Decoration(3);
		militaire.setName("Furtif");
		militaire.setPrice(-2);

		Tamtam tamtam = new Tamtam(1);
		tamtam.setName("Tamtam 1");
		tamtam.setPrice(200);
		tamtam.setBrand("Tamtamarque");
		tamtam.setSkin("Chèvre");
		tamtam.setWood("Acajou");
		tamtam.addShipment(UPS);
		tamtam.addShipment(FedEx);
		tamtam.addDecoration(leopard);
		tamtam.addDecoration(zebre);
		tamtam.addDecoration(militaire);

		Storage.createTamtam(tamtam);


		tamtam = new Tamtam(2);
		tamtam.setName("Tamtam 2");
		tamtam.setPrice(123.12);
		tamtam.setBrand("Tamtamarque");
		tamtam.setSkin("Biche");
		tamtam.setWood("Acajou");
		tamtam.addShipment(UPS);
		tamtam.addShipment(Colissimo);
		tamtam.addDecoration(leopard);
		tamtam.addDecoration(zebre);
		tamtam.addDecoration(militaire);


		Storage.createTamtam(tamtam);

		tamtam = new Tamtam(3);
		tamtam.setName("Tamtam 3");
		tamtam.setPrice(12000);
		tamtam.setBrand("Tamtamarque");
		tamtam.setSkin("Synthétique");
		tamtam.setWood("Pommier");
		tamtam.addShipment(Colissimo);
		tamtam.addShipment(FedEx);
		tamtam.addDecoration(leopard);
		tamtam.addDecoration(zebre);
		tamtam.addDecoration(militaire);

		Storage.createTamtam(tamtam);


		tamtam = new Tamtam(4);
		tamtam.setName("Tamtam 4");
		tamtam.setPrice(541);
		tamtam.setBrand("Tamama");
		tamtam.setSkin("Synthétique");
		tamtam.setWood("Pommier");
		tamtam.addShipment(FedEx);
		tamtam.addDecoration(leopard);
		tamtam.addDecoration(zebre);
		tamtam.addDecoration(militaire);

		Storage.createTamtam(tamtam);

		tamtam = new Tamtam(5);
		tamtam.setName("Tamtam 4 mé en mieu");
		tamtam.setPrice(15400);
		tamtam.setBrand("Tamama");
		tamtam.setSkin("Synthétique");
		tamtam.setWood("Pommier");
		tamtam.addShipment(Colissimo);
		tamtam.addDecoration(leopard);
		tamtam.addDecoration(zebre);
		tamtam.addDecoration(militaire);

		Storage.createTamtam(tamtam);

		tamtam = new Tamtam(6);
		tamtam.setName("Tamtam 6");
		tamtam.setPrice(35);
		tamtam.setBrand("Tamama");
		tamtam.setSkin("Chèvre");
		tamtam.setWood("Pommier");
		tamtam.addShipment(UPS);
		tamtam.addDecoration(leopard);
		tamtam.addDecoration(zebre);
		tamtam.addDecoration(militaire);

		Storage.createTamtam(tamtam);

		tamtam = new Tamtam(7);
		tamtam.setName("Tamtamultime");
		tamtam.setPrice(1000000);
		tamtam.setBrand("Epictamtam");
		tamtam.setSkin("Chèvre");
		tamtam.setWood("Acajou");
		tamtam.addShipment(UPS);
		tamtam.addDecoration(leopard);
		tamtam.addDecoration(zebre);
		tamtam.addDecoration(militaire);

		Storage.createTamtam(tamtam);
	}

}
