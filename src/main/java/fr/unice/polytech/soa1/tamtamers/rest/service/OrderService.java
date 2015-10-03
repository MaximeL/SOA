package fr.unice.polytech.soa1.tamtamers.rest.service;

import fr.unice.polytech.soa1.tamtamers.rest.database.OrderStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Order;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// TODO : Sebastien Pas de verbe

/**
 * (PATH /orders) Service for the orders
 */
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderService {

    /**
     * (GET) Return all the orders in the database
     * @return Response JSon format
     */
    @GET
    public Response getOrders() {
        Collection<Order> orders = OrderStorage.findAllOrders();
        JSONArray result = new JSONArray();
        for(Order order: orders) {
            result.put(new JSONObject(order.toString()));
        }
        return Response.ok().entity(result.toString()).build();
    }

    /**
     * (GET /order) Return an order knowing his id
     * @param id  int  (QUERY)  id of the order
     * @return Response JSon format
     */
    @GET
    @Path("/order")
    public Response getOrder(@QueryParam("id") int id) {
        Order order = OrderStorage.getOrder(id);
        if(order != null) {
            return Response.ok().entity(order.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * (GET) Return all the orders of an user
     * @param id  int  (PATH)  id of the user
     * @return Response JSon format
     */
    @GET
    @Path("/{user}")
    public Response getUserOrders(@PathParam("user") int id) {
        Collection<Order> orders = OrderStorage.getUserOrders(id);
        JSONArray result = new JSONArray();
        for(Order order: orders) {
            result.put(new JSONObject(order.toString()));
        }
        return Response.ok().entity(result.toString()).build();
    }

    /**
     * (GET) Get all the order that are in a given state
     * @param state  String  (PATH)  Name of the state
     * @return Response JSon format
     */
    @GET
    @Path("/status")
    public Response getStateOrders(@QueryParam("status") String state) {
        Collection<Order> orders = OrderStorage.getStatusOrders(state);
        JSONArray result = new JSONArray();
        for(Order order: orders) {
            result.put(new JSONObject(order.toString()));
        }
        return Response.ok().entity(result.toString()).build();
    }

}
