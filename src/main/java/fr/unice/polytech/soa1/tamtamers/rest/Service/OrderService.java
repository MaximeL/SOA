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

// TODO : Sébastien Pas de verbe
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderService {

    @GET
    public Response getOrders() {
        Collection<Order> orders = OrderStorage.findAllOrders();
        JSONArray result = new JSONArray();
        for(Order order: orders) {
            result.put(new JSONObject(order.toString()));
        }
        return Response.ok().entity(result.toString()).build();
    }

    @POST
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

    @GET
    @Path("/{id}")
    public Response getOrder(@PathParam("id") int id) {
        Order order = OrderStorage.getOrder(id);
        if(order != null) {
            return Response.ok().entity(order.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/user/{id}")
    public Response getUserOrders(@PathParam("id") int id) {
        Collection<Order> orders = OrderStorage.getUserOrders(id);
        JSONArray result = new JSONArray();
        for(Order order: orders) {
            result.put(new JSONObject(order.toString()));
        }
        return Response.ok().entity(result.toString()).build();
    }
}
