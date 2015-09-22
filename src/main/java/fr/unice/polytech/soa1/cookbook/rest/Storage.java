package fr.unice.polytech.soa1.cookbook.rest;


import java.time.Period;
import java.util.Collection;
import java.util.HashMap;

public class Storage {

	// this mocks a database.
	private static HashMap<Integer, Tamtam> tamtams = new HashMap<Integer, Tamtam>();
	private static HashMap<Integer, User> users = new HashMap<Integer, User>();
	private static HashMap<Integer, Shipment> shipments = new HashMap<Integer, Shipment>();
	private static HashMap<Integer, Decoration> decorations = new HashMap<Integer, Decoration>();
	private static HashMap<Integer, Order> orders = new HashMap<Integer, Order>();

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

	// USER
	public static User createUser(User user) {
		user.setId(users.size());
		users.put(user.getId(), user);
		return user;
	}
	public static void deleteUser(Integer id) {
		users.remove(id);
	}
	public static void updateUser(User user) {
		// TODO Automatique avec les références ?
	}
	public static User getUser(Integer id) {
		return users.get(id);
	}
	public static Collection<User> findAllUsers() {
		return users.values();
	}

	// ORDER
	public static Collection<Order> findAllOrders() {
		return orders.values();
	}
	public static Order createOrder(Order order) {
		order.setId(orders.size());
		orders.put(order.getId(), order);
		return order;
	}
	public static Order getOrder(Integer id) {
		return orders.get(id);
	}

	static {
		User user = new User();
		user.setId(1);
		user.setAddress1("00 - Rue de Azerty");
		user.setAddress1("Bâtiment R - Etage 1.25");
		user.setFullname("Root User");
		user.setPc("57872");
		user.setState("France");
		user.setPhone("+33705214896");

		Storage.createUser(user);


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

		Storage.createShipment(UPS);
		Storage.createShipment(Colissimo);
		Storage.createShipment(FedEx);

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

		Storage.createDecoration(leopard);
		Storage.createDecoration(zebre);
		Storage.createDecoration(militaire);

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
