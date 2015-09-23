package fr.unice.polytech.soa1.cookbook.rest;

public class Order {
    private int id;
    private Tamtam tamtam;
    private Shipment shipment;
    private Decoration decoration;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tamtam getTamtam() {
        return tamtam;
    }

    public void setTamtam(Tamtam tamtam) {
        this.tamtam = tamtam;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Decoration getDecoration() {
        return decoration;
    }

    public void setDecoration(Decoration decoration) {
        this.decoration = decoration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString()
    {
        String deco = "";
        if(decoration != null) {
            deco += ", \"decoration\":" + decoration.toString();
        }
        return "{" +
                "\"id\":" + id +
                ", \"tamtam\":" + tamtam.minToString() +
                ", \"shipment\":" + shipment.toString() +
                deco +
            "}";
    }
}
