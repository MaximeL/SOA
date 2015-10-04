package fr.unice.polytech.soa1.tamtamers.rest.entity;

public class Payment {
    private int order;
    private double amount;
    private String transaction;
    private Status status;
    private Type type;

    public Payment(int order) {
        this.order = order;
        this.status = Status.UNKNOWED;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = Status.VALID;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Status {
        UNKNOWED("Inconnu"),
        VALID("Validé"),
        DECLINE("Refusé");

        private final String val;
        Status(String val) {
            this.val = val;
        }

        @Override
        public String  toString() {
            return val;
        }
    }

    public enum Type {
        PAYPAL("PayPal"),
        CB("Credit Card");

        private final String val;
        Type(String val) {
            this.val = val;
        }

        @Override
        public String  toString() {
            return val;
        }
    }
}
