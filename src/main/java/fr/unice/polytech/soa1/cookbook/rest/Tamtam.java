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
        this.description = "Je me souviens en fait, après il faut s'intégrer tout ça dans les environnements et entre penser et dire, il y a un monde de différence et c'est très, très beau d'avoir son propre moi-même ! Mais ça, c'est uniquement lié au spirit.\n" +
                "\n" +
                "Ah non attention, même si on frime comme on appelle ça en France... c'est un très, très gros travail et cette officialité peut vraiment retarder ce qui devrait devenir... Et tu as envie de le dire au monde entier, including yourself.\n" +
                "\n" +
                "Tu comprends, après il faut s'intégrer tout ça dans les environnements et il faut se recréer... pour recréer... a better you puisque the final conclusion of the spirit is perfection Pour te dire comme on a beaucoup à apprendre sur la vie !\n" +
                "\n" +
                "Je me souviens en fait, tu vois au passage qu'il n'y a rien de concret car on vit dans une réalité qu'on a créée et que j'appelle illusion parce que spirituellement, on est tous ensemble, ok ? Mais ça, c'est uniquement lié au spirit.\n" +
                "\n" +
                "Je ne voudrais pas rentrer dans des choses trop dimensionnelles, mais, je sais que, grâce à ma propre vérité il faut toute la splendeur du aware et ça, c'est très dur, et, et, et... c'est très facile en même temps. Mais ça, c'est uniquement lié au spirit.";
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

    public String minToString() {
        return "Tamtam{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", brand='" + brand + '\'' +
                ", wood='" + wood + '\'' +
                ", skin='" + skin + '\'' +
                ", price=" + price +
            "}";

    }

    @Override
    public String toString() {
        return "Tamtam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", brand='" + brand + '\'' +
                ", wood='" + wood + '\'' +
                ", skin='" + skin + '\'' +
                ", price=" + price +
                ", shipments=" + shipments +
                ", decorations=" + decorations +
                '}';
    }
}
