package fr.unice.polytech.soa1.tamtamers.rest.service;

import fr.unice.polytech.soa1.tamtamers.rest.database.TamtamStorage;
import fr.unice.polytech.soa1.tamtamers.rest.entity.Tamtam;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

// TODO : Sébastien Pas de verbe

@Path("/tamtams")
@Produces(MediaType.APPLICATION_JSON)
/**
 * (PATH /tamtams) Services about the tamtams
 */
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
     * (GET) Detail of a tamtam regarding his id
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
}
