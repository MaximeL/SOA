package fr.unice.polytech.soa1.tamtamers.rest.entity;

import java.time.Period;

public class Shipment {
    private int id;
    private String name;
    private double price;
    private Period delay;
    private State state;

    public Shipment(int id) {
        this.id = id;
        this.state = State.WAITING_PAYMENT;
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

    public State getState() {
        return this.state;
    }

    public void nextState() {
        switch (this.state) {
            case WAITING_PAYMENT:
                this.state = State.PREPARING_SHIPMENT;
                break;

            case PREPARING_SHIPMENT:
                this.state = State.SHIPING;
                break;

            default:
                this.state = State.ARCHIVED;
                break;
        }
    }
    public void previousState() {
        switch (this.state) {
            case ARCHIVED:
                this.state = State.SHIPING;
                break;

            case SHIPING:
                this.state = State.PREPARING_SHIPMENT;
                break;

            default:
                this.state = State.WAITING_PAYMENT;
                break;
        }
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

enum State {

    WAITING_PAYMENT ("Waiting Payment"),
    PREPARING_SHIPMENT ("Preparing Shipement"),
    SHIPING ("Shiping"),
    ARCHIVED ("Archived");

    private final String val;
    State(String val) {
        this.val = val;
    }

    @Override
    public String  toString() {
        return val;
    }
}