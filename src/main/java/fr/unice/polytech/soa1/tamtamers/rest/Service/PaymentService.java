package fr.unice.polytech.soa1.tamtamers.rest.service;

import fr.unice.polytech.soa1.tamtamers.rest.database.OrderStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.PaymentStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Order;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Payment;
import fr.unice.polytech.soa1.tamtamers.rest.entity.State;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("paiement")
public class PaymentService {
    @PUT
    public Response payCB(
            @QueryParam("cardnumber") String card,
            @QueryParam("owner") String owner,
            @QueryParam("verification-code") String verificationCode,
            @QueryParam("order") Integer orderId,
            @QueryParam("amount") Double amount
    ) {
        Order order = OrderStorage.getOrder(orderId);
        card = card.replaceAll(" ", "");

        if(
            card.length() == 16 &&
            (
                verificationCode.length() == 3 ||
                verificationCode.length() == 4
            ) &&
            order.getPrice() == amount &&
            order.getStatus() == State.WAITING_PAYMENT
        ) {
            Payment paiement = new Payment(orderId);
            paiement.setAmount(amount);
            paiement.setType(Payment.Type.CB);
            PaymentStorage.createPaiement(paiement);
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    public Response payPaypall(
            @QueryParam("account") String paypallAccount,
            @QueryParam("order") Integer orderId
    ) {
        Order order = OrderStorage.getOrder(orderId);
        Payment payment = new Payment(orderId);
        payment.setType(Payment.Type.PAYPAL);
        payment.setAmount(order.getPrice());

        PaymentStorage.createPaiement(payment);
        return Response.ok().build();
    }
}
