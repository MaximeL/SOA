package fr.unice.polytech.soa1.cookbook.rest;

import java.time.Period;

public class Shipment {
    private int id;
    private String name;
    private double price;
    private Period delay;

    public Shipment(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Period getDelay() {
        return delay;
    }

    public void setDelay(Period delay) {
        this.delay = delay;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + "\"" +
                ", \"price\":" + price +
                ", \"delay\":\"" + delay.getDays() + " jours\"" +
                '}';
    }
}
