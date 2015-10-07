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

    @Override
    public String toString(){
        String transaction = "";
        if(this.transaction.equals("")) {
            transaction += ",\"numero de transaction\":" + "\"" + transaction + "\"";
        }
        return "{" +
                "\"order\":" + order +
                ",\"amount\":" + amount +
                ",\"status\":" + "\"" + status + "\"" +
                transaction +
            "}";
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

    public Type getType() {
        return type;
    }

    public boolean getPaid() {
        return (amount%2) == 0;
    }

    public void validate() {
        this.status = Status.VALID;
    }

    public void decline() {
        this.status = Status.DECLINE;
    }

    public void waiting() {
        this.status = Status.WAITING;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Status {
        UNKNOWED("Inconnu"),
        WAITING("En attente de validation"),
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
