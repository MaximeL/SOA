package fr.unice.polytech.soa1.tamtamers.rest.service;

import fr.unice.polytech.soa1.tamtamers.rest.database.ShipmentStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Shipment;
import org.json.JSONArray;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Service for the shipment
 */
@Path("/shipments")
@Produces(MediaType.APPLICATION_JSON)
public class ShipmentService {

    /**
     * List all the shipment or get them by name
     * @param type  String  (QUERY)  status of the shipment
     * @return Response
     */
    @GET
    public Response getShipment(@QueryParam("type") String type) {
        if(type != null) {
            if(type.equals("express")) {
                return getExpress();
            } else if(type.equals("normal")) {
                return getNormal();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } else {
            Collection<Shipment> shipments = ShipmentStorage.findAllShipments();
            JSONArray array = new JSONArray();
            for(Shipment shipment : shipments) {
                array.put(shipment.toString());
            }
            return Response.ok().entity(array.toString()).build();
        }
    }

    /**
     * Return the best express shipment service
     * @return Response JSon format
     */
    public Response getExpress() {
        Shipment res = ShipmentStorage.getExpress();
        if(res == null) res = ShipmentStorage.getNormal();
        if(res == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(res.toString()).build();
    }

    /**
     * Return the best normal shipement
     * @return Response JSon format
     */
    public Response getNormal() {
        Shipment res = ShipmentStorage.getNormal();
        if(res == null) res = ShipmentStorage.getExpress();
        if(res == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(res.toString()).build();
    }

}
