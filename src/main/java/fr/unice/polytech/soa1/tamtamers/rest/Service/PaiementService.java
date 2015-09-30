package fr.unice.polytech.soa1.tamtamers.rest.Service;

import fr.unice.polytech.soa1.tamtamers.rest.database.OrderStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.PaiementStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Order;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Paiement;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("paiement")
public class PaiementService {
    @PUT
    public Response pay(
            @QueryParam("cardnumber") String card,
            @QueryParam("owner") String owner,
            @QueryParam("verification-code") String verificationCode,
            @QueryParam("order") Integer orderId,
            @QueryParam("amount") Float amount
    ) {
        Order order = OrderStorage.getOrder(orderId);
        card = card.replaceAll(" ", "");

        if(
            card.length() == 16 &&
            (
                verificationCode.length() == 3 ||
                verificationCode.length() == 4
            ) &&
            order.getTotal() == amount &&
            order.getStatus() == Order.Status.WAITING
        ) {
            Paiement paiement = new Paiement(orderId);
            paiement.setAmount(amount);
            PaiementStorage.createPaiement(paiement);
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
