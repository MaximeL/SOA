package fr.unice.polytech.soa1.tamtamers.rest.service;

import fr.unice.polytech.soa1.tamtamers.rest.database.OrderStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.PaymentStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Order;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Payment;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Status;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * (PATH /payment) Service for the payment
 */
@Path("/payment")
public class PaymentService {

    /**
     * (PUT) Payment by creditcard. Payment is valide if amout is odd.
     * @param card              String   (QUERY)  card number
     * @param owner             String   (QUERY)  owner name
     * @param verificationCode  String   (QUERY)  verification code
     * @param orderId           Integer  (QUERY)  id of the customer
     * @param amount            Double   (QUERY)  amount payed
     * @return Response JSon format
     */
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
            order.getStatus() == Status.WAITING_PAYMENT
        ) {
            Payment payment = new Payment(orderId);
            payment.setAmount(amount);
            payment.setType(Payment.Type.CB);
            payment.setOrder(orderId);

            //Racourcis
            if(payment.getPaid()) {
                payment.decline();
                OrderStorage.getOrder(orderId).cancelOrder();
            } else {
                payment.validate();
                OrderStorage.getOrder(orderId).nextStatus();
            }


            PaymentStorage.createPayment(payment);
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * All the payment that need to be validated
     * @return
     */
    @GET
    public Response getToPay() {
        Collection<Payment> pmts = PaymentStorage.getToPay();
        JSONArray result = new JSONArray();

        for(Payment p : pmts)
            result.put(new JSONObject(p.toString()));

        return Response.ok().entity(result.toString()).build();
    }

    /**
     * Set status of a payment.
     * @param id      int     (PATH)   id of the payment
     * @param status  String  (QUERY)  status to set
     * @return
     */
    @PUT
    @Path("/{id}")
    public Response validate(@PathParam("id") int id, @QueryParam("status") String status) {
        if(status.equals("VALID")) {
            PaymentStorage.validate(id);
            OrderStorage.getOrder(id).nextStatus();
            return Response.ok().build();
        } else if(status.equals("INVALID")) {
            PaymentStorage.decline(id);
            OrderStorage.getOrder(id).cancelOrder();
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
