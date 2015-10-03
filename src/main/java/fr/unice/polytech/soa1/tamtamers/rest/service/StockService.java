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
/**
 * (PATH /stock)Service about stocks
 */
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
     * (GET) Return an item of the stock regarding his id.
     * @param id int (PATH) id of the item you're looking for
     * @return Response JSon format
     */
    @GET
    @Path("/{id}/search")
    public Response getStockOf(@PathParam("id") int id) {
        StockItem stockItem = StockStorage.getStockItem(id);
        if(stockItem == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(stockItem.toString()).build();
    }

    /**
     * [PUT /add) Increment the number of element of an item in the stock
     * @param id      int  (PATH) id of the item
     * @param number  int  (QUERY) number of element to add
     * @return Response JSon format
     */
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

    /**
     * (PUT /remove) Remove a number of element of an item in the stock
     * @param id      int  (PATH)   id of the item
     * @param number  int  (QUERY)  number of element to remove
     * @return Response JSon format
     */
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

    /**
     * (PUT) Add a new item in the stock
     * @param id           int      (QUERY)  id of the item
     * @param name         String   (QUERY)  name of the item
     * @param description  String   (QUERY)  description of the item
     * @return Response JSon format
     */
    @PUT
    @Path("/add")
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

    /**
     * (DELETE) Remove an item from the stock
     * @param id  int  (PATH)  id of the item
     * @return Response JSon format
     */
    @DELETE
    @Path("/{id}/delete")
    public Response disableItem(@PathParam("id") int id) {
        // TODO : Disable item
        StockItem stockItem = StockStorage.getStockItem(id);
        stockItem.setDisabled(true);
        return Response.ok().build();
    }
}
