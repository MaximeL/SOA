package fr.unice.polytech.soa1.cookbook.rest;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    @GET
    public Response getUsers() {
        Collection<User> users = Storage.findAllUsers();
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
            @QueryParam("potal-code") String pc,
            @QueryParam("state") String state
        ) {
        User user = new User();
        user.setFullname(fullname);
        user.setPhone(phone);
        user.setAddress1(address1);
        user.setAddress2(address2);
        user.setPc(pc);
        user.setState(state);

        user = Storage.createUser(user);
        return Response.ok().entity(user.toString()).build();

    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") int id) {
        User user = Storage.getUser(id);
        if(user != null) {
            return Response.ok().entity(user.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        User user = Storage.getUser(id);
        if(user != null) {
            Storage.deleteUser(user.getId());
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // TODO : Fonctionne pas ...
    @PUT
    @Path("/{id}")
    public Response updateUser(
            @PathParam("id") int id,
            @QueryParam("fullname") String fullname,
            @QueryParam("phone") String phone,
            @QueryParam("address1") String address1,
            @QueryParam("address2") String address2,
            @QueryParam("potal-code") String pc,
            @QueryParam("state") String state
    ) {
        User user = Storage.getUser(id);
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
            if(!pc.equals("")) {
                user.setPc(pc);
            }
            if(!state.equals("")) {
                user.setState(state);
            }
            Storage.updateUser(user);
            return Response.ok().entity(user.toString()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
