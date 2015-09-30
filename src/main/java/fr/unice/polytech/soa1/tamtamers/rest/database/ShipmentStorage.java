package fr.unice.polytech.soa1.tamtamers.rest.database;

import fr.unice.polytech.soa1.tamtamers.rest.entity.Shipment;

import java.time.Period;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maxime on 9/28/2015.
 */
public class ShipmentStorage {

    // this mocks a database.
    private static HashMap<Integer, Shipment> shipments = new HashMap<Integer, Shipment>();

    // USER
    public static Shipment createShipment(Shipment shipment) {
        shipment.setId(shipments.size() + 1);
        shipments.put(shipment.getId(), shipment);
        return shipment;
    }
    public static void deleteShipment(Integer id) {
        shipments.remove(id);
    }
    public static Shipment getShipment(Integer id) {
        return shipments.get(id);
    }
    public static Collection<Shipment> findAllShipments() {
        return shipments.values();
    }

    static {
        Shipment shipment = new Shipment(0);
        shipment.setName("UPS");
        shipment.setPrice(5);
        shipment.setDelay(Period.ofDays(2));
        ShipmentStorage.createShipment(shipment);

        shipment = new Shipment(1);
        shipment.setName("La Poste");
        shipment.setPrice(0);
        shipment.setDelay(Period.ofDays(5));
        ShipmentStorage.createShipment(shipment);

    }
}
