package fr.unice.polytech.soa1.tamtamers.rest.service;

import fr.unice.polytech.soa1.tamtamers.rest.database.StockStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.StockItem;
import fr.unice.polytech.soa1.tamtamers.rest.tool.Const;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collection;

// TODO : Sébastien Pas de verbe
@Path("/stock")
@Produces(MediaType.APPLICATION_JSON)
public class StockService {
    public static final String SERVICE = "/stock";

    @GET
    public Response getAllStock() {
        Collection<StockItem> stockItems = StockStorage.getAllStockItems();
        JSONArray result = new JSONArray();
        for(StockItem item: stockItems) {
            result.put(new JSONObject(item.toString()));
        }
        return Response.ok().entity(result.toString()).build();
    }

    @GET
    @Path("/{id}")
    public Response getStockOf(@PathParam("id") int id) {
        StockItem stockItem = StockStorage.getStockItem(id);
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
        StockItem stockItem = StockStorage.getStockItem(id);
        if(stockItem == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if(number <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        stockItem.addToStock(number);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}/remove")
    public Response removeFromStock(
            @PathParam("id") int id,
            @QueryParam("number") int number
    ) {
        StockItem stockItem = StockStorage.getStockItem(id);
        if(stockItem == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if(number <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        stockItem.addToStock(number);
        return Response.noContent().build();
    }

    @PUT
    public Response addItem(
            @QueryParam("id") int id,
            @QueryParam("name") String name,
            @QueryParam("description") String description
            // TODO suite
    ) {
        // TODO
        StockItem stockItem = new StockItem();
        stockItem.setItemId(id);
        // TODO suite ...

        StockStorage.store(stockItem);
        // TODO Check URI
        return Response.created(URI.create(Const.BASE_URL + SERVICE + stockItem.getItemId())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response disableItem(@PathParam("id") int id) {
        // TODO : Disable item
        StockItem stockItem = StockStorage.getStockItem(id);
        stockItem.setDisabled(true);
        return Response.ok().build();
    }
}
