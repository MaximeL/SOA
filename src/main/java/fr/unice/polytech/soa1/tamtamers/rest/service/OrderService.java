package fr.unice.polytech.soa1.tamtamers.rest.service;

import fr.unice.polytech.soa1.tamtamers.rest.database.OrderStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.TamtamStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.UserStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
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
     * (POST /add) Create a new order
     * @param tamtams      int[]  (QUERY)  ids of the tamtams in correlation with the decorations
     * @param idShipment   int    (QUERY)  id of the selected shipment
     * @param decorations  int[]  (QUERY)  ids of the decoration in corelation with the tamtams
     * @param idUser       int    (QUERY)  id of the customer who ordered
     * @return Response
     */
    @POST
    @Path("/add")
    public Response createOrder(
            @QueryParam("tamtam") int[] tamtams,
            @QueryParam("shipment") int idShipment,
            @QueryParam("decoration") int[] decorations,
            @QueryParam("user") int idUser
    ) {
        if(tamtams.length == decorations.length) {
            Shipment shipment = TamtamStorage.getShipment(idShipment);
            User user = UserStorage.getUser(idUser);

            if(shipment == null || user == null || tamtams == null || decorations == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            Order order = new Order();

            for(int i = 0; i < tamtams.length; i++) {
                if(decorations[i] == -1) {
                    order.addItem(tamtams[i]);
                } else {
                    order.addItem(tamtams[i], decorations[i]);
                }
                Tamtam tmp = TamtamStorage.getTamtam(tamtams[i]);
                HashMap<Integer, Shipment> hship = tmp.getShipments();
                boolean oneGood = false;

                for(Map.Entry<Integer, Shipment> ship : hship.entrySet()) {

                    Integer key = ship.getKey();
                    Shipment value = ship.getValue();
                    if(value.getId() == idShipment) oneGood = true;
                }

                if(!oneGood) return Response.status(Response.Status.BAD_REQUEST).build();
            }
/*
            if(
                    !(decoration != null && tamtam.getDecorations().containsKey(idDecoration)) ||
                            !tamtam.getShipments().containsKey(shipment.getId())
                    ) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }*/

            order.setShipment(shipment);
            order.setUser(user);

            order.setPrice();

            order = OrderStorage.createOrder(order);

            return Response.ok().entity(order.toString()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    /**
     * (GET) Return an order knowing his id
     * @param id  int  (PATH)  id of the order
     * @return Response JSon format
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
    @Path("/{status}")
    public Response getStateOrders(@PathParam("status") String state) {
        Collection<Order> orders = OrderStorage.getStatusOrders(state);
        JSONArray result = new JSONArray();
        for(Order order: orders) {
            result.put(new JSONObject(order.toString()));
        }
        return Response.ok().entity(result.toString()).build();
    }

}
