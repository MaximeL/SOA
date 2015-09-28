package fr.unice.polytech.soa1.tamtamers.rest.database;

import fr.unice.polytech.soa1.tamtamers.rest.entity.StockItem;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Tamtam;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class StockStorage {
    private static HashMap<Integer, StockItem> stockItems = new HashMap<Integer, StockItem>();

    public static Collection<StockItem> findAllStockItems() {
        return stockItems.values();
    }

    public static StockItem findStockItem(Integer id) {
        return stockItems.get(id);
    }

    static {
        Collection tamtams = TamtamStorage.findAllTamtams();
        StockItem item;
        Random random = new Random();
        for(Tamtam tamtam : tamtams) {
            item = new StockItem();
            item.setItemId(tamtam.getId());
            item.setNumberInStock(random.nextInt(100));
        }
        // TODO : Initialiser le stock
    }
}
