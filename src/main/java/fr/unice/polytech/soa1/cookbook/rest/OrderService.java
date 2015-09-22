package fr.unice.polytech.soa1.cookbook.rest;

import org.json.JSONObject;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderService {
    @POST
    public Response createOrder(@QueryParam("tamtam") int idTamtam, @QueryParam("shipment") int idShipment, @QueryParam("decoration") int idDecoration) {
        JSONObject object = new JSONObject();
        object.put("tamtam", new JSONObject(Storage.getTamtam(idTamtam).minToString()));
        object.put("shipment", new JSONObject(Storage.getShipment(idShipment)));
        object.put("decoration", new JSONObject(Storage.getDecoration(idDecoration)));

        return Response.ok().entity(object.toString()).build();
    }
}
