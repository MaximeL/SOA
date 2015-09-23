package fr.unice.polytech.soa1.cookbook.rest;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;


@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderService {
    @GET
    public Response getOrders() {
        Collection<Order> orders = Storage.findAllOrders();
        JSONArray result = new JSONArray();
        for(Order order: orders) {
            result.put(new JSONObject(order.toString()));
        }
        return Response.ok().entity(result.toString()).build();
    }

    @POST
    public Response createOrder(
            @QueryParam("tamtam") int idTamtam,
            @QueryParam("shipment") int idShipment,
            @QueryParam("decoration") int idDecoration,
            @QueryParam("user") int idUser
    ) {
        Tamtam tamtam = Storage.getTamtam(idTamtam);
        Shipment shipment = Storage.getShipment(idShipment);
        User user = Storage.getUser(idUser);

        if(tamtam == null || shipment == null || user == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Decoration decoration = Storage.getDecoration(idDecoration);

        if(
            !(decoration != null && tamtam.getDecorations().containsKey(idDecoration)) ||
            !tamtam.getShipments().containsKey(shipment.getId())
        ) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Order order = new Order();
        order.setTamtam(tamtam);
        order.setShipment(shipment);
        order.setDecoration(decoration);
        order.setUser(user);

        order = Storage.createOrder(order);

        return Response.ok().entity(order.toString()).build();
    }

    @GET
    @Path("/{id}")
    public Response getOrder(@PathParam("id") int id) {
        Order order = Storage.getOrder(id);
        if(order != null) {
            return Response.ok().entity(order.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
