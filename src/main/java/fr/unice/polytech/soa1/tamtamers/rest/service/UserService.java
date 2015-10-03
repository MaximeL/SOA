package fr.unice.polytech.soa1.tamtamers.rest.service;

import fr.unice.polytech.soa1.tamtamers.rest.database.OrderStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.TamtamStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.UserStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Order;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Shipment;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Tamtam;
import fr.unice.polytech.soa1.tamtamers.rest.entity.User;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    @GET
    public Response getUsers(@QueryParam("name") String name) {
        Collection<User> users;
        if(name != null) {
            users = UserStorage.findUserByName(name);
        } else {
            users = UserStorage.findAllUsers();
        }
        JSONArray result = new JSONArray();
        for(User user : users) {
            result.put(new JSONObject(user.minToString()));
        }
        return Response.ok().entity(result.toString()).build();
    }

    @POST
    public Response addUser(
            @QueryParam("fullname") String fullname,
            @QueryParam("phone") String phone,
            @QueryParam("address1") String address1,
            @QueryParam("address2") String address2,
            @QueryParam("zip-code") String zc,
            @QueryParam("state") String state
        ) {
        User user = new User();
        user.setFullname(fullname);
        user.setPhone(phone);
        user.setAddress1(address1);
        user.setAddress2(address2);
        user.setZc(zc);
        user.setState(state);

        user = UserStorage.createUser(user);
        return Response.ok().entity(user.toString()).build();

    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") int id) {
        User user = UserStorage.findUserById(id);
        if(user != null) {
            return Response.ok().entity(user.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

//    @GET
//    public Response getUser() {
//        System.out.println("ici ?");
//
//        JSONArray result = new JSONArray();
//        for(User user : users) {
//            result.put(new JSONObject(user.minToString()));
//        }
//        return Response.ok().entity(result.toString()).build();
//    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        User user = UserStorage.findUserById(id);
        if(user != null) {
            UserStorage.deleteUser(user.getId());
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(
            @PathParam("id") int id,
            @QueryParam("fullname") String fullname,
            @QueryParam("phone") String phone,
            @QueryParam("address1") String address1,
            @QueryParam("address2") String address2,
            @QueryParam("zip-code") String zc,
            @QueryParam("state") String state
    ) {
        User user = UserStorage.findUserById(id);
        if(user != null) {
            if(!fullname.equals("")) {
                user.setFullname(fullname);
            }
            if(!phone.equals("")) {
                user.setPhone(phone);
            }
            if(!address1.equals("")) {
                user.setAddress1(address1);
            }
            if(!address2.equals("")) {
                user.setAddress2(address2);
            }
            if(!zc.equals("")) {
                user.setZc(zc);
            }
            if(!state.equals("")) {
                user.setState(state);
            }
            return Response.ok().entity(user.toString()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * (POST /{id}/orders) Create a new order
     * @param id           int    (QUERY)  id of the customer who ordered
     * @param tamtams      int[]  (QUERY)  ids of the tamtams in correlation with the decorations
     * @param idShipment   int    (QUERY)  id of the selected shipment
     * @param decorations  int[]  (QUERY)  ids of the decoration in corelation with the tamtams
     * @return Response
     */
    @POST
    @Path("/{id}/orders")
    public Response createOrder(
            @PathParam("id") int id,
            @QueryParam("tamtam") int[] tamtams,
            @QueryParam("shipment") int idShipment,
            @QueryParam("decoration") int[] decorations
    ) {
        if(tamtams.length == decorations.length) {
            Shipment shipment = TamtamStorage.getShipment(idShipment);
            User user = UserStorage.findUserById(id);

            System.out.println(tamtams);
            System.out.println(decorations);
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

            return Response.created(URI.create("http://localhost:8181/cxf/tamtamers/orders/" + order.getId())).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    /**
     * (POST /{id}/orders) Create a new order
     * @param id  int  (PATH)  id of th eorder
     * @return Response
     */
    @GET
    @Path("/{id}/orders")
    public Response createOrder(
            @PathParam("id") int id
    ) {
        Collection<Order> orders = OrderStorage.getUserOrders(id);
        JSONArray array = new JSONArray();

        for(Order order: orders) {
            array.put(new JSONObject(order.toString()));
        }

        return Response.ok().entity(array.toString()).build();
    }
}
