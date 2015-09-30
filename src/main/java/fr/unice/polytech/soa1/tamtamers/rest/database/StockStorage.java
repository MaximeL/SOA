package fr.unice.polytech.soa1.tamtamers.rest.database;

import fr.unice.polytech.soa1.tamtamers.rest.entity.StockItem;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Tamtam;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by sgregoire on 28/09/2015.
 */
public class StockStorage {
    private static HashMap<Integer, Tamtam> tamtams = new HashMap<Integer, Tamtam>();

    public static Collection<StockItem> findAllStockItems() { return null; }


}
