package fr.unice.polytech.soa1.tamtamers.rest.service;

import fr.unice.polytech.soa1.tamtamers.rest.database.OrderStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Order;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * (PATH /orders) Service for the orders
 */
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderService {

    /**
     * (GET) Return all the orders in the database or the order matching a given status
     * @param state  String  (QUERY)  Name of the state for filter. Optional
     * @return Response JSon format
     */
    @GET
    public Response getOrders(@QueryParam("status") String state) {
        Collection<Order> orders;
        if(state != null) {
            orders = OrderStorage.getStatusOrders(state);
        } else {
            orders = OrderStorage.findAllOrders();
        }

        JSONArray result = new JSONArray();
        for(Order order: orders) {
            result.put(new JSONObject(order.toString()));
        }
        return Response.ok().entity(result.toString()).build();
    }

    /**
     * (GET /{id}) Return an order knowing his id
     * @param id  int  (QUERY)  id of the order
     * @return Response JSon format
     * TODO : Ca affiche bien l'ID de transaction et l'id de livraison ?
     * TODO : Réponse : id transation = id commande. order contien lobjet shipment et l'affiche (afficher que l'id plutot ?)
     *
     */
    @GET
    @Path("/{id}")
    public Response getOrder(@PathParam("id") int id) {
        Order order = OrderStorage.getOrder(id);
        if(order != null) {
            return Response.ok().entity(order.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * (PUT /{id}) Cancel the order
     * @param id      int     (PATH)   id of the order
     * @param action  String  (FORM)  Must be "CANCEL"
     * @return
     */
    @PUT
    @Path("/{id}}")
    public Response cancelPayment(@PathParam("id") int id, @FormParam("action") String action) {
        if(action.equals("CANCEL")) {
            if(OrderStorage.getOrder(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
            OrderStorage.cancelOrder(id);
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * (POST /status) Change the state of an order to the next state
     * @param id  int  (FORM)  id of the order
     * @return Response JSon format
     * TODO : Changer l'URL
     */
    @POST
    @Path("/status")
    public Response getStateOrders(@FormParam("id") int id) {
        // TODO @Maxime : C'est pas au client de faire ça ?
        // TODO Reponse : le status est changé par le paiment puis pas le service de livraison.
        OrderStorage.getOrder(id).nextStatus();
        return Response.ok().build();
    }

}
