package fr.unice.polytech.soa1.tamtamers.rest.entity;

public class StockItem {
    private int itemId;
    private int numberInStock;
    private String supplying;

    private boolean disabled;

    public StockItem() {
        supplying = "2 days";
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

    public String getSupplying() {
        return supplying;
    }

    public void setSupplying(String supplying) {
        this.supplying = supplying;
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
        return "{" +
                "\"itemId\":" + this.getItemId() +
                ",\"numberInStock\":" + this.getNumberInStock() +
                ",\"supplying\":" + "\"" + this.getSupplying() +"\"" +
            "}";
    }
}
