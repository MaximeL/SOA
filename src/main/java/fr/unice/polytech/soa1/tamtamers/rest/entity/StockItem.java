package fr.unice.polytech.soa1.tamtamers.rest.entity;

/**
 * Created by sgregoire on 28/09/2015.
 */
public class StockItem {
    private int itemId;
    private int numberInStock;
    // TODO Un bon nom
    private Object timeTo;

    private boolean disabled;

    public StockItem() {

    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }

    public void addToStock(int number)
    {
        this.numberInStock += number;
    }

    public void removeFromStock(int number) {
        this.numberInStock -= number;
    }

    public Object getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Object timeTo) {
        this.timeTo = timeTo;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString()
    {
        // TODO
        return super.toString();
    }
}
