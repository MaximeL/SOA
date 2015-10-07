package fr.unice.polytech.soa1.tamtamers.rest.entity;

public enum Status {

    WAITING_PAYMENT ("Waiting Payment"),
    PREPARING_SHIPMENT ("Preparing Shipement"),
    SHIPING ("Shiping"),
    ARCHIVED ("Archived"),
    PROBLEM ("Problem occured"),
    CANCELED("Canceled");

    private final String val;
    Status(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}
