package fr.unice.polytech.soa1.tamtamers.rest.service;

import fr.unice.polytech.soa1.tamtamers.rest.database.StockStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.TamtamStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.StockItem;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

// TODO : Sébastien Pas de verbe
@Path("/stock")
@Produces(MediaType.APPLICATION_JSON)
public class StockService {
    @GET
    public Response getAllStock() {
        // TODO
        Collection<StockItem> stockItems = StockStorage.findAllStockItems();
        JSONArray result = new JSONArray();
        for(StockItem item: stockItems) {
            result.put(new JSONObject(item.toString()));
        }
        return Response.ok().entity(result.toString()).build();
    }

    @GET
    @Path("/{id}")
    public Response getStockOf(@PathParam("id") int id) {
        // TODO
        StockItem stockItem = TamtamStorage.getTamtam(id);
        if(stockItem == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(stockItem.toString()).build();
    }

    @PUT
    @Path("/{id}/add")
    public Response addToStock(
            @PathParam("id") int id,
            @QueryParam("number") int number
    ) {
        // TODO
        StockItem stockItem = TamtamStorage.getTamtam(id);
        if(stockItem == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if(number <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        stockItem.addToStock(number);
        return Response.ok().entity(stockItem.toString()).build();
    }

    @PUT
    @Path("/{id}/remove")
    public Response removeFromStock(@PathParam("id") int id) {
        // TODO
        return Response.ok().build();
    }

    @POST
    public Response addItem(
            @QueryParam("name") String name,
            @QueryParam("description") String description
            // TODO suite
    ) {
        // TODO
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response disableItem(@PathParam("id") int id) {
        // TODO : Disable item
        return Response.ok().build();
    }
}
