package fr.unice.polytech.soa1.tamtamers.rest.entity;

public class StockItem {
    private int itemId;
    private int numberInStock;
    // TODO Un bon nom
    private String timeTo;

    private boolean disabled;

    public StockItem() {
        // TODO
        timeTo = "2 days";
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

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
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
        return "{" +
                "\"itemId\":" + this.getItemId() +
                ",\"numberInStock\":" + this.getNumberInStock() +
                ",\"timeTo\":" + "\"2 days\"" +
            "}";
    }
}
