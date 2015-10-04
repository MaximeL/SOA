package fr.unice.polytech.soa1.tamtamers.rest.service;

import fr.unice.polytech.soa1.tamtamers.rest.database.OrderStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.ShipmentStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.TamtamStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.UserStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collection;

@Path("/customers")
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
        return Response.created(URI.create("localhost:8181/cxf/tamtamers/users/" + user.getId())).build();
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
     * @return Response
     */
    @POST
    @Path("/{id}/orders")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(
            @PathParam("id") int id,
            @HeaderParam("order") JSONObject data
    ) {
        User user = UserStorage.findUserById(id);
        Integer shipmentId;

        if(data.has("shipment") || user != null) {
            shipmentId = (Integer) data.get("shipment");
            Shipment shipment = ShipmentStorage.getShipment(shipmentId);

            if(data.has("items")) {
                JSONArray items = data.getJSONArray("items");
                JSONObject object;

                Order order = new Order();

                for(int i = 0; i < data.length(); i++) {
                    object = items.getJSONObject(i);

                    if(object.has("tamtam") && object.has("decoration")) {
                        Integer tamtamId = (Integer) object.get("tamtam");
                        Integer decorationId = (Integer) object.get("decoration");

                        Tamtam tamtam = TamtamStorage.getTamtam(tamtamId);
                        Decoration decoration = TamtamStorage.getDecoration(decorationId);

                        if(tamtam != null || decoration != null) {
                            order.addItem(tamtamId, decorationId);
                        } else {
                            return Response.status(Response.Status.NOT_FOUND).build();
                        }
                    } else if(object.has("tamtam")) {
                        Integer tamtamId = (Integer) object.get("tamtam");
                        Tamtam tamtam = TamtamStorage.getTamtam(tamtamId);

                        if(tamtam != null) {
                            order.addItem(tamtamId);
                        } else {
                            return Response.status(Response.Status.NOT_FOUND).build();
                        }
                    } else {
                        return Response.status(Response.Status.NOT_FOUND).build();
                    }
                }
                order.setUser(user);
                order.computePrice();
                order.setShipment(shipment);

                OrderStorage.createOrder(order);
                return Response.created(URI.create("http://localhost:8181/cxf/tamtamers/orders/" + order.getId())).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * (POST /{id}/orders) Create a new order
     * @param id  int  (PATH)  id of th eorder
     * @return Response
     */
    @GET
    @Path("/{id}/orders")
    public Response getOrders(
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