package fr.unice.polytech.soa1.cookbook.rest;

import java.util.ArrayList;

public class Tamtam {
    private int id;
    private String name;
    private String description;
    private String image;

    private String brand;
    private String wood;
    private String skin;
    private double price;

    private ArrayList<Shipment> shipments = new ArrayList<Shipment>();
    private ArrayList<Decoration> decorations = new ArrayList<Decoration>();

    public Tamtam(int id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWood() {
        return wood;
    }

    public void setWood(String wood) {
        this.wood = wood;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    public ArrayList<Decoration> getDecorations()
    {
        return this.decorations;
    }

    public void addShipment(Shipment shipment) {
        this.shipments.add(shipment);
    }

    public ArrayList<Shipment> getShipments()
    {
        return this.shipments;
    }

    @Override
    public String toString() {
        return "Tamtam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", wood='" + wood + '\'' +
                ", skin='" + skin + '\'' +
                ", price=" + price +
                '}';
    }
}
