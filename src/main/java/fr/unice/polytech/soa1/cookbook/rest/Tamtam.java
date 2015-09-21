package fr.unice.polytech.soa1.cookbook.rest;

/**
 * Created by S�bastien on 21/09/2015.
 */
public class Tamtam {
    private int id;
    private String name;
    private String description;
    private String brand;
    private String wood;
    private String skin;

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

    @Override
    public String toString() {
        return "Tamtam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", wood='" + wood + '\'' +
                ", skin='" + skin + '\'' +
            '}';
    }
}
