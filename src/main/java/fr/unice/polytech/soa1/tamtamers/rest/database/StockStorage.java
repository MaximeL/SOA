package fr.unice.polytech.soa1.tamtamers.rest.database;

import fr.unice.polytech.soa1.tamtamers.rest.entity.StockItem;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Tamtam;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class StockStorage {
    private static HashMap<Integer, StockItem> stockItems = new HashMap<Integer, StockItem>();

    public static HashMap<Integer, StockItem> getHashMap() {
        return stockItems;
    }

    public static Collection<StockItem> getAllStockItems() {
        HashMap<Integer, StockItem> items = new HashMap<Integer, StockItem>();
        for(StockItem item : stockItems.values()) {
            if(!item.isDisabled()) {
                items.put(item.getItemId(), item);
            }
        }
        return items.values();
    }

    public static StockItem getStockItem(Integer id) {
        return stockItems.get(id);
    }

    public static StockItem store(StockItem item) {
        stockItems.put(item.getItemId(), item);
        return item;
    }

    static {
        Collection<Tamtam> tamtams = TamtamStorage.findAllTamtams();
        StockItem item;
        Random random = new Random();
        for(Tamtam tamtam : tamtams) {
            item = new StockItem();
            item.setItemId(tamtam.getId());
            item.setNumberInStock(random.nextInt(100));
            stockItems.put(item.getItemId(), item);
        }
    }
}
