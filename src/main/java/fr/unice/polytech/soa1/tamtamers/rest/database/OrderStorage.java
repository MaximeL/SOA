package fr.unice.polytech.soa1.tamtamers.rest.database;

import fr.unice.polytech.soa1.tamtamers.rest.entity.Order;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    public static Collection<Order> getUserOrders(int id) {
        HashMap<Integer, Order> result = new HashMap<Integer, Order>();
        for(Map.Entry<Integer, Order> entry : orders.entrySet()) {
            Integer key = entry.getKey();
            Order value = entry.getValue();
            if(value.getId() == id && !result.containsKey(key)) result.put(key, value);
        }
        return result.values();
    }

    public static Collection<Order> getStateOrders(String state) {
        HashMap<Integer, Order> result = new HashMap<Integer, Order>();
        for(Map.Entry<Integer, Order> entry : orders.entrySet()) {
            Integer key = entry.getKey();
            Order value = entry.getValue();
            if(value.getStatus().equals(state) && !result.containsKey(key)) result.put(key, value);
        }
        return result.values();
    }
}
