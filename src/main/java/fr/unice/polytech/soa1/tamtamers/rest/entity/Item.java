package fr.unice.polytech.soa1.tamtamers.rest.entity;

import fr.unice.polytech.soa1.tamtamers.rest.database.DecorationStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.TamtamStorage;

public class Item {

    private Tamtam tamtam;
    private Decoration decoration = null;
    private double price;

    public Item(Integer tamtamId, Integer decorationId) {
        tamtam = TamtamStorage.getTamtam(tamtamId);
        decoration = DecorationStorage.getDecoration(decorationId);
        price = tamtam.getPrice() + decoration.getPrice();
    }

    public Item(Integer tamtamId) {
        tamtam = TamtamStorage.getTamtam(tamtamId);
        price = tamtam.getPrice();
    }

    public Tamtam getTamtam() {
        return tamtam;
    }

    public Decoration getDecoration() {
        return decoration;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        String res = "{ \"tamtam\":"+this.tamtam;
        if(this.decoration != null) res += ", \"decoration\":"+this.decoration;
        res += "}";
        return res;
    }
}
