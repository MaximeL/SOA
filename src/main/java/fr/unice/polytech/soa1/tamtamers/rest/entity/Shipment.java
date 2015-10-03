package fr.unice.polytech.soa1.tamtamers.rest.entity;

import java.time.Period;
import java.util.Random;

public class Shipment {
    private int id;
    private String name;
    private double price;
    private Period delay;
    private boolean available;
    private String tracking;

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

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void generateCodeTracking()
    {
        Random random = new Random();
        this.tracking = String.valueOf(random.nextLong());
    }

    @Override
    public String toString() {
        String tracking = "";
        if(this.tracking != null) {
            tracking = ", \"Code tracking\": \""+this.tracking+ "\"";
        }

        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + "\"" +
                ", \"price\":" + price +
                ", \"delay\":\"" + delay.getDays() + " jours\"" +
                tracking +
                '}';
    }
}

