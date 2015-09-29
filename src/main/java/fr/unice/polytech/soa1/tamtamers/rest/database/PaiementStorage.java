package fr.unice.polytech.soa1.tamtamers.rest.database;

import fr.unice.polytech.soa1.tamtamers.rest.entity.Paiement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class PaiementStorage {
    private static HashMap<Integer, Paiement> paiements = new HashMap<Integer, Paiement>();

    // PAIMENTS
    public static Collection<Paiement> findAllPaiements() {
        return paiements.values();
    }

    public static Paiement getPaiementOfOrder(Integer order) {
        return paiements.get(order);
    }

    public static void createPaiement(Paiement paiement) {
        Random random = new Random();
        paiement.setTransaction(String.valueOf(random.nextLong()));
        paiement.setStatus(Paiement.Status.VALID);
        paiements.put(paiement.getOrder(), paiement);
    }
}
