package fr.unice.polytech.soa1.tamtamers.rest.service;

import fr.unice.polytech.soa1.tamtamers.rest.database.DecorationStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.ShipmentStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.StockStorage;
import fr.unice.polytech.soa1.tamtamers.rest.database.TamtamStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Decoration;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Shipment;
import fr.unice.polytech.soa1.tamtamers.rest.entity.StockItem;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Tamtam;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * (PATH /tamtamers/tamtams) Services about the tamtams
 */
@Path("/tamtams")
@Produces(MediaType.APPLICATION_JSON)
public class TamtamService {

    /**
     * (GET) Search for tamtam(s) that fit the parameters. None returns all
     * @return Response JSon format
     */
    @GET
    public Response getAvailableTamtams(@QueryParam("brand") String brand, @QueryParam("skin") String skin, @QueryParam("wood") String wood) {
        Collection<Tamtam> tamtams = TamtamStorage.findAllTamtams();
        JSONArray result = new JSONArray();
        for(Tamtam tamtam: tamtams) {
            if(brand == null || tamtam.getBrand().equals(brand)) {
                if(skin == null || tamtam.getSkin().equals(skin)) {
                    if(wood == null || tamtam.getWood().equals(wood)) {
                        result.put(new JSONObject(tamtam));
                    }
                }
            }
            //result.put(new JSONObject(tamtam.minToString()));
        }
        return Response.ok().entity(result.toString()).build();
    }

    /**
     * (GET /{id}) Detail of a tamtam regarding his id
     * @param id  int  (PATH)  Tamtam's id you're looking for
     * @return Response JSon format
     */
    @Path("/{id}")
    @GET
    public Response getTamtam(@PathParam("id") int id) {
        Tamtam tamtam = TamtamStorage.getTamtam(id);
        if(tamtam == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        StockItem item = StockStorage.getStockItem(id);
        if(item.getNumberInStock() == 0) {
            tamtam.setStatus(Tamtam.Status.OUT);
        } else {
            tamtam.setStatus(Tamtam.Status.STOCK);
        }
        return Response.ok().entity(tamtam.toString()).build();
    }


    /**
     * (GET /types) Return all the caracteristics that the tamtam can have. List the woods, skins and brands.
     * @return Response JSon format
     */
    @Path("/types")
    @GET
    public Response getTypes()
    {
        Collection<Tamtam> tamtams = TamtamStorage.findAllTamtams();
        JSONObject result = new JSONObject();

        ArrayList<String> woods = new ArrayList<String>();
        ArrayList<String> skins = new ArrayList<String>();
        ArrayList<String> brands = new ArrayList<String>();

        for(Tamtam tamtam: tamtams) {
            if(!woods.contains(tamtam.getWood())) {
                woods.add(tamtam.getWood());
            }
            if(!skins.contains(tamtam.getSkin())) {
                skins.add(tamtam.getSkin());
            }
            if(!brands.contains(tamtam.getBrand())) {
                brands.add(tamtam.getBrand());
            }
        }

        result.put("woods", woods);
        result.put("skins", skins);
        result.put("brands", brands);

        return Response.ok().entity(result.toString(2)).build();
    }

    /**
     * (POST) Add a tamtam to the showcase
     * @param name         String     (FORM)  name of the tamtam
     * @param description  String     (FORM)  description of the tamtam
     * @param image        String     (FORM)  image of the tamtam
     * @param brand        String     (FORM)  brand of the tamtam
     * @param wood         String     (FORM)  wood used by the tamtam
     * @param skin         String     (FORM)  skin used by the tamtam
     * @param price        Double     (FORM)  price of the tamtam
     * @param decorations  JSONArray  (FORM)  Decorations available
     * @param shipments    JSONArray  (FORM)  Shipments available
     * @return Response
     */
    @POST
    public Response addTamtam(
            @FormParam("name") String name,
            @FormParam("description") String description,
            @FormParam("image") String image,
            @FormParam("brand") String brand,
            @FormParam("wood") String wood,
            @FormParam("skin") String skin,
            @FormParam("price") Double price,
            @FormParam("decorations") JSONArray decorations,
            @FormParam("shipments") JSONArray shipments
    ) {
        Tamtam tamtam = new Tamtam();
        if(
            name == null ||
            description == null ||
            image == null ||
            brand == null ||
            wood == null ||
            skin == null ||
            // TODO : Valeur par défaut du prix ?
            price == null
        ) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        tamtam.setName(name);
        tamtam.setDescription(description);
        tamtam.setImage(image);
        tamtam.setBrand(brand);
        tamtam.setWood(wood);
        tamtam.setSkin(skin);
        tamtam.setPrice(price);

        HashMap<Integer, Decoration> decorationHashMap = new HashMap<Integer, Decoration>();
        for(int i = 0; i < decorations.length(); i++) {
            Integer decorationId = (Integer) decorations.get(i);
            Decoration decoration = DecorationStorage.getDecoration(decorationId);
            if (decoration == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            decorationHashMap.put(decorationId, decoration);
        }
        tamtam.setDecorations(decorationHashMap);

        HashMap<Integer, Shipment> shipmentHashMap = new HashMap<Integer, Shipment>();
        for(int i = 0; i < shipments.length(); i++) {
            Integer shipmentId = (Integer) shipments.get(i);
            Shipment shipment= ShipmentStorage.getShipment(shipmentId);
            if (shipment == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            shipmentHashMap.put(shipmentId, shipment);
        }
        tamtam.setShipments(shipmentHashMap);

        TamtamStorage.createTamtam(tamtam);
        return Response.created(URI.create("http://localhost:8181/cxf/tamtamers/tamtams/" + tamtam.getId())).build();
    }

    /**
     * (PUT /{id})Update tamtam
     * @param id           Integer    (PATH)  id of the tamtam
     * @param name         String     (FORM)  name of the tamtam
     * @param description  String     (FORM)  description of the tamtam
     * @param image        String     (FORM)  image of the tamtam
     * @param brand        String     (FORM)  brand of the tamtam
     * @param wood         String     (FORM)  wood used by the tamtam
     * @param skin         String     (FORM)  skin used by the tamtam
     * @param price        Double     (FORM)  price of the tamtam
     * @param decorations  JSONArray  (FORM)  Decorations available
     * @param shipments    JSONArray  (FORM)  Shipments available
     * @return Response
     */
    @PUT
    @Path("/{id}")
    public Response updateTamtam(
            @PathParam("id") Integer id,
            @FormParam("name") String name,
            @FormParam("description") String description,
            @FormParam("image") String image,
            @FormParam("brand") String brand,
            @FormParam("wood") String wood,
            @FormParam("skin") String skin,
            @FormParam("price") Double price,
            @FormParam("decorations") JSONArray decorations,
            @FormParam("shipments") JSONArray shipments
    ) {
        Tamtam tamtam = TamtamStorage.getTamtam(id);
        if(tamtam != null) {
            if(name != null) {
                tamtam.setName(name);
            }
            if(description != null) {
                tamtam.setDescription(description);
            }
            if(image != null) {
                tamtam.setImage(image);
            }
            if(brand != null) {
                tamtam.setBrand(brand);
            }
            if(wood != null) {
                tamtam.setWood(wood);
            }
            if(skin != null) {
                tamtam.setSkin(skin);
            }
            if(price != null) {
                tamtam.setPrice(price);
            }
            if(decorations != null) {
                HashMap<Integer, Decoration> decorationHashMap = new HashMap<Integer, Decoration>();
                for (int i = 0; i < decorations.length(); i++) {
                    Integer decorationId = (Integer) decorations.get(i);
                    Decoration decoration = DecorationStorage.getDecoration(decorationId);
                    if (decoration == null) {
                        return Response.status(Response.Status.BAD_REQUEST).build();
                    }
                    decorationHashMap.put(decorationId, decoration);
                }
                tamtam.setDecorations(decorationHashMap);
            }
            if(shipments != null) {
                HashMap<Integer, Shipment> shipmentHashMap = new HashMap<Integer, Shipment>();
                for (int i = 0; i < shipments.length(); i++) {
                    Integer shipmentId = (Integer) shipments.get(i);
                    Shipment shipment = ShipmentStorage.getShipment(shipmentId);
                    if (shipment == null) {
                        return Response.status(Response.Status.BAD_REQUEST).build();
                    }
                    shipmentHashMap.put(shipmentId, shipment);
                }
                tamtam.setShipments(shipmentHashMap);
            }

            return Response.ok().entity(tamtam.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * (DELETE /{id}) Delete tamtam from the showcase
     * @param id  int  (PATH)  id of the tamtam
     * @return Response
     */
    @DELETE
    @Path("/{id}")
    public Response deleteTamtam(@PathParam("id") int id) {
        if(TamtamStorage.getTamtam(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
        TamtamStorage.deleteTamtam(id);
        return Response.status(Response.Status.OK).build();
    }
}