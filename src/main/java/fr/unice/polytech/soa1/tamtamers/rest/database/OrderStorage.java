package fr.unice.polytech.soa1.tamtamers.rest.database;

import fr.unice.polytech.soa1.tamtamers.rest.entity.Order;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by sgregoire on 28/09/2015.
 */
public class OrderStorage {
    private static HashMap<Integer, Order> orders = new HashMap<Integer, Order>();

    // ORDER
    public static Collection<Order> findAllOrders() {
        return orders.values();
    }
    public static Order createOrder(Order order) {
        order.setId(orders.size() + 1);
        orders.put(order.getId(), order);
        return order;
    }
    public static Order getOrder(Integer id) {
        return orders.get(id);
    }
}
