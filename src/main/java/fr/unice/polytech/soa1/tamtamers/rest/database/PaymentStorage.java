package fr.unice.polytech.soa1.tamtamers.rest.database;

import fr.unice.polytech.soa1.tamtamers.rest.entity.Payment;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class PaymentStorage {
    private static HashMap<Integer, Payment> paiements = new HashMap<Integer, Payment>();

    // PAIMENTS
    public static Collection<Payment> findAllPaiements() {
        return paiements.values();
    }

    public static Payment getPaiementOfOrder(Integer order) {
        return paiements.get(order);
    }

    public static void createPaiement(Payment payment) {
        Random random = new Random();
        payment.setTransaction(String.valueOf(random.nextLong()));
        payment.setStatus(Payment.Status.VALID);
        paiements.put(payment.getOrder(), payment);
    }
}
