package fr.unice.polytech.soa1.tamtamers.rest.service;

import fr.unice.polytech.soa1.tamtamers.rest.database.OrderStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.PaymentStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Order;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Payment;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Status;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * (PATH /payment) Service for the payment
 */
@Path("/payment")
public class PaymentService {

    /**
     * (PUT) Payment by creditcard
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
            PaymentStorage.createPayment(payment);
            order.nextStatus();
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * (PUT) Payment by PayPal
     * @param paypallAccount  String   (QUERY)  name of the PayPal account
     * @param orderId         Integer  (QUERY)  id of the order
     * @return Response JSon format
     */
    @PUT
    public Response payPaypall(
            @QueryParam("account") String paypallAccount,
            @QueryParam("order") Integer orderId
    ) {
        Order order = OrderStorage.getOrder(orderId);
        Payment payment = new Payment(orderId);
        payment.setType(Payment.Type.PAYPAL);
        payment.setAmount(order.getPrice());

        PaymentStorage.createPayment(payment);
        order.nextStatus();
        return Response.ok().build();
    }
}
