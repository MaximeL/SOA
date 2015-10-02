package fr.unice.polytech.soa1.tamtamers.rest.service;

import fr.unice.polytech.soa1.tamtamers.rest.database.ShipmentStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Shipment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by sgregoire on 28/09/2015.
 */
@Path("/stock")
@Produces(MediaType.APPLICATION_JSON)
public class ShipmentService {

    @GET
    @Path("/express")
    public Response getExpress() {
        Shipment res = ShipmentStorage.getExpress();
        if(res == null) res = ShipmentStorage.getNormal();
        if(res == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(res.toString()).build();
    }

    @GET
    @Path("/normal")
    public Response getNormal() {
        Shipment res = ShipmentStorage.getNormal();
        if(res == null) res = ShipmentStorage.getExpress();
        if(res == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(res.toString()).build();
    }

}
