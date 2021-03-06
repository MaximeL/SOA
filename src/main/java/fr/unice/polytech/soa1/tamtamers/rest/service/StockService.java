package fr.unice.polytech.soa1.tamtamers.rest.service;

import fr.unice.polytech.soa1.tamtamers.rest.database.StockStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.StockItem;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * (PATH /entrepot/stock)Service about stocks
 */
@Path("/stock")
@Produces(MediaType.APPLICATION_JSON)
public class StockService {
    public static final String SERVICE = "/stock";

    /**
     * (GET) Get all the items in stock
     * @return Response JSon format
     */
    @GET
    public Response getAllStock() {
        Collection<StockItem> stockItems = StockStorage.getAllStockItems();
        JSONArray result = new JSONArray();
        for(StockItem item: stockItems) {
            result.put(new JSONObject(item.toString()));
        }
        return Response.ok().entity(result.toString()).build();
    }

    /**
     * (GET /{id}) Return an item of the stock regarding his id.
     * @param id int (PATH) id of the item you're looking for
     * @return Response JSon format
     */
    @GET
    @Path("/{id}")
    public Response getStockOf(@PathParam("id") int id) {
        StockItem stockItem = StockStorage.getStockItem(id);
        if(stockItem == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(stockItem.toString()).build();
    }

    /**
     * [PUT /{id}) Increment the number of element of an item in the stock
     * @param id      int  (PATH)  id of the item
     * @param number  int  (FORM)  number of element to add
     * @return Response JSon format
     */
    @PUT
    @Path("/{id}")
    public Response updateStock(
            @PathParam("id") int id,
            @FormParam("number") int number
    ) {
        StockItem stockItem = StockStorage.getStockItem(id);
        if(stockItem == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        stockItem.setNumberInStock(number);
        return Response.ok().build();
    }
}
