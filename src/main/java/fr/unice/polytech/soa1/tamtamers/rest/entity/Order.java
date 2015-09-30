package fr.unice.polytech.soa1.tamtamers.rest.entity;

import java.util.ArrayList;

public class Order {
    private int id;
    private Shipment shipment;
    private ArrayList<Item> items;
    private User user;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Decoration getDecoration() {
        for(Item item : items) {
            if(item.getDecoration().getId() == id) return item.getDecoration();
        }
        return null;
    }

    public Tamtam getTamtam(int id) {
        for(Item item : items) {
            if(item.getTamtam().getId() == id) return item.getTamtam();
        }
        return null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addItem(Integer tamtamId, Integer decorationId) {
        items.add(new Item(tamtamId, decorationId));
    }

    public void addItem(Integer tamtamId) {
        items.add(new Item(tamtamId));
    }

    public void setPrice() {
        price = 0;

        if(shipment != null) price += shipment.getPrice();

        for(Item item: items) {
            price += item.getPrice();
        }
    }

    public double getPrice() {
        return price;
    }

    public State getStatus() {
        return shipment.getState();
    }

    @Override
    public String toString()
    {
        String itm = "[";
        for(Item item : items) {
            itm += item+",";
        }
        itm = itm.substring(0, itm.length()-1);
        itm += "]";
        return "{" +
                "\"id\":" + id +
                ", \"items\":" +itm +
                ", \"shipment\":" + shipment.toString() +
                ", \"price\":" + price +
            "}";
    }
}
