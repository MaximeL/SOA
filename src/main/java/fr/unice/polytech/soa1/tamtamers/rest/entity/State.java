package fr.unice.polytech.soa1.tamtamers.rest.entity;

public enum State {

    WAITING_PAYMENT ("Waiting Payment"),
    PREPARING_SHIPMENT ("Preparing Shipement"),
    SHIPING ("Shiping"),
    ARCHIVED ("Archived");

    private final String val;
    State(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}
