package fr.unice.polytech.soa1.tamtamers.rest.entity;

public class Paiement {
    private int order;
    private float amount;
    private String transaction;
    private Status status;

    public Paiement(int order) {
        this.order = order;
        this.status = Status.UNKNOWED;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
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
        this.status = status;
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
}
