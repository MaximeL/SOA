package fr.unice.polytech.soa1.tamtamers.rest.database;

import fr.unice.polytech.soa1.tamtamers.rest.entity.Payment;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PaymentStorage {
    private static HashMap<Integer, Payment> payments = new HashMap<Integer, Payment>();

    // PAIMENTS
    public static Collection<Payment> findAllPayments() {
        return payments.values();
    }

    public static Payment getPaymentOfOrder(Integer order) {
        return payments.get(order);
    }

    public static void createPayment(Payment payment) {
        Random random = new Random();
        payment.setTransaction(String.valueOf(random.nextLong()));
        payments.put(payment.getOrder(), payment);
    }

    public static Collection<Payment> getToPay() {
        HashMap<Integer, Payment> topay = new HashMap<Integer, Payment>();
        for(Payment payment: payments.values()) {
            if(payment.getStatus() == Payment.Status.WAITING) {
                topay.put(payment.getOrder(), payment);
            }
        }
        return topay.values();
    }

    public static void validate(int id) {
        Payment payment = payments.get(id);
        payment.validate();

        payments.replace(id, payment);
    }

    public static void decline(int id) {
        Payment payment = payments.get(id);
        payment.decline();

        payments.replace(id, payment);
    }
}
