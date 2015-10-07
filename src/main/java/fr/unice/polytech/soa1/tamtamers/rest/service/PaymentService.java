package fr.unice.polytech.soa1.tamtamers.rest.service;

import fr.unice.polytech.soa1.tamtamers.rest.database.OrderStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.PaymentStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Order;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Payment;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Status;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * (PATH /payment) Service for the payment
 */
@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentService {

    /**
     * (PUT) Payment by creditcard. Payment is valide if amout is odd.
     * @param card              String   (FORM)  card number
     * @param owner             String   (FORM)  owner name
     * @param verificationCode  String   (FORM)  verification code
     * @param orderId           Integer  (FORM)  id of the customer
     * @param amount            Double   (FORM)  amount payed
     * @return Response JSon format
     */
    @PUT
    public Response payCB(
            @FormParam("cardnumber") String card,
            @FormParam("owner") String owner,
            @FormParam("verification-code") String verificationCode,
            @FormParam("order") Integer orderId,
            @FormParam("amount") Double amount
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
            if(card.equals("0000000000000000")) {
                payment.waiting();
            } else if(card.equals("1111111111111111")) {
                payment.decline();
                OrderStorage.getOrder(orderId).problem();
            } else {
                payment.validate();
                OrderStorage.getOrder(orderId).payed();
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
     * @param id      int     (PATH)  id of the payment
     * @param status  String  (FORM)  status to set
     * @return
     */
    @PUT
    @Path("/{id}")
    public Response validate(@PathParam("id") int id, @FormParam("status") String status) {
        if(status.equals("VALID")) {
            PaymentStorage.validate(id);
            OrderStorage.getOrder(id).payed();
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
