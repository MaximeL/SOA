package fr.unice.polytech.soa1.tamtamers.rest.entity;

public class Decoration {
    private int id;
    private String name;
    private String image;
    private double price;

    public Decoration(int id) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + "\"" +
                ", \"image\":\"" + image + "\"" +
                ", \"price\":" + price +
                '}';
    }
}
