package fr.unice.polytech.soa1.tamtamers.rest.database;


import fr.unice.polytech.soa1.tamtamers.rest.entity.Decoration;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Shipment;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Tamtam;

import java.time.Period;
import java.util.Collection;
import java.util.HashMap;

public class TamtamStorage {

	// this mocks a database.
	private static HashMap<Integer, Tamtam> tamtams = new HashMap<Integer, Tamtam>();
	private static HashMap<Integer, Shipment> shipments = new HashMap<Integer, Shipment>();
	private static HashMap<Integer, Decoration> decorations = new HashMap<Integer, Decoration>();


	// TAMTAM
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

	// DECORATION
	public static void createDecoration(Decoration decoration) {
		decorations.put(decoration.getId(), decoration);
	}
	public static Decoration getDecoration(Integer id) {
		return decorations.get(id);
	}

	// SHIPMENT
	public static void createShipment(Shipment shipment) {
		shipments.put(shipment.getId(), shipment);
	}
	public static Shipment getShipment(Integer id) {
		return shipments.get(id);
	}

	static {
		Shipment Normal = new Shipment(1);
		Normal.setName("Normal");
		Normal.setDelay(Period.ofDays(5));
		Normal.setPrice(9.0);

		Shipment Express = new Shipment(2);
		Express.setName("Express");
		Express.setDelay(Period.ofDays(1));
		Express.setPrice(0);

		Decoration leopard = new Decoration(1);
		leopard.setName("Design Léopard");
		leopard.setPrice(0.12);
		leopard.setImage("http://images.jedessine.com/_uploads/_tiny_galerie/20091044/leopard-dessin-source_sen.jpg");

		Decoration zebre = new Decoration(2);
		zebre.setName("Design zébré");
		zebre.setPrice(2);
		zebre.setImage("http://www.quizz.biz/uploads/quizz/823431/10_MSbd0.jpg");

		Decoration militaire = new Decoration(3);
		militaire.setName("Furtif");
		militaire.setPrice(-2);
		militaire.setImage("http://i.ytimg.com/vi/8DIhCp1O52Q/hqdefault.jpg");

		TamtamStorage.createDecoration(leopard);
		TamtamStorage.createDecoration(zebre);
		TamtamStorage.createDecoration(militaire);

		Tamtam tamtam = new Tamtam(1);
		tamtam.setName("Tamtam 1");
		tamtam.setPrice(200);
		tamtam.setBrand("Tamtamarque");
		tamtam.setSkin("Chèvre");
		tamtam.setWood("Acajou");
		tamtam.addShipment(Normal);
		tamtam.addShipment(Express);
		tamtam.addDecoration(leopard);
		tamtam.addDecoration(zebre);
		tamtam.addDecoration(militaire);

		TamtamStorage.createTamtam(tamtam);


		tamtam = new Tamtam(2);
		tamtam.setName("Tamtam 2");
		tamtam.setPrice(123.12);
		tamtam.setBrand("Tamtamarque");
		tamtam.setSkin("Biche");
		tamtam.setWood("Acajou");
		tamtam.addShipment(Normal);
		tamtam.addShipment(Express);
		tamtam.addDecoration(leopard);
		tamtam.addDecoration(zebre);
		tamtam.addDecoration(militaire);


		TamtamStorage.createTamtam(tamtam);

		tamtam = new Tamtam(3);
		tamtam.setName("Tamtam 3");
		tamtam.setPrice(12000);
		tamtam.setBrand("Tamtamarque");
		tamtam.setSkin("Synthétique");
		tamtam.setWood("Pommier");
		tamtam.addShipment(Normal);
		tamtam.addShipment(Express);
		tamtam.addDecoration(leopard);
		tamtam.addDecoration(zebre);
		tamtam.addDecoration(militaire);

		TamtamStorage.createTamtam(tamtam);


		tamtam = new Tamtam(4);
		tamtam.setName("Tamtam 4");
		tamtam.setPrice(541);
		tamtam.setBrand("Tamama");
		tamtam.setSkin("Synthétique");
		tamtam.setWood("Pommier");
		tamtam.addShipment(Express);
		tamtam.addDecoration(leopard);
		tamtam.addDecoration(zebre);
		tamtam.addDecoration(militaire);

		TamtamStorage.createTamtam(tamtam);

		tamtam = new Tamtam(5);
		tamtam.setName("Tamtam 4 mé en mieu");
		tamtam.setPrice(15400);
		tamtam.setBrand("Tamama");
		tamtam.setSkin("Synthétique");
		tamtam.setWood("Pommier");
		tamtam.addShipment(Normal);
		tamtam.addDecoration(leopard);
		tamtam.addDecoration(zebre);
		tamtam.addDecoration(militaire);

		TamtamStorage.createTamtam(tamtam);

		tamtam = new Tamtam(6);
		tamtam.setName("Tamtam 6");
		tamtam.setPrice(35);
		tamtam.setBrand("Tamama");
		tamtam.setSkin("Chèvre");
		tamtam.setWood("Pommier");
		tamtam.addShipment(Normal);
		tamtam.addDecoration(leopard);
		tamtam.addDecoration(zebre);
		tamtam.addDecoration(militaire);

		TamtamStorage.createTamtam(tamtam);

		tamtam = new Tamtam(7);
		tamtam.setName("Tamtamultime");
		tamtam.setPrice(1000000);
		tamtam.setBrand("Epictamtam");
		tamtam.setSkin("Chèvre");
		tamtam.setWood("Acajou");
		tamtam.addShipment(Normal);
		tamtam.addDecoration(leopard);
		tamtam.addDecoration(zebre);
		tamtam.addDecoration(militaire);

		TamtamStorage.createTamtam(tamtam);
	}

}
