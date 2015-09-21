package fr.unice.polytech.soa1.cookbook.rest;

import org.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/tamtams")
@Produces(MediaType.APPLICATION_JSON)
public class TamtamService {

    /**
     * Liste des tamtams
     * @return Response
     */
    @GET
    public Response getAvailableTamtams() {
        Collection<Tamtam> tamtams = Storage.findAllTamtams();
        JSONArray result = new JSONArray();
        for(Tamtam tamtam: tamtams) {
            result.put(tamtam.getName());
        }
        return Response.ok().entity(result.toString(2)).build();
    }

    /**
     * Liste des tamtams par id
     * @param id
     * @return
     */
    @Path("/tamtams/{id}")
    @GET
    public Response getTamtam(@PathParam("id") int id) {
        Tamtam tamtam = Storage.getTamtam(id);
        if(tamtam == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        String value = Storage.getTamtam(id).toString();
        return Response.ok().entity("\""+value+"\"").build();
    }


    /**
     * Search for tamtam(s) that fit the parameters.
     * @param brand
     * @param skin
     * @param wood
     * @return Object Tamtams with an array "tamtam" of Tamtam.
     */
    @Path("/tamtams/search/brand/{brand}/skin/{skin}/wood/{wood}/")
    @GET
    public Response searchTamtam(@PathParam("brand") String brand, @PathParam("skin") String skin, @PathParam("wood") String wood)
    {
        Collection<Tamtam> tamtams = Storage.findAllTamtams();
        JSONArray result = new JSONArray();

        JSONArray tamtamResult = new JSONArray("tamtams");

        for(Tamtam tamtam: tamtams) {
            if(tamtam.getBrand().equals(brand) && tamtam.getSkin().equals(skin) && tamtam.getWood().equals(wood))
                tamtamResult.put(tamtam);
        }
        result.put(tamtamResult);
        return Response.ok().entity(result.toString(2)).build();
    }

    @Path("tamtams/types")
    @GET
    public Response getTypes()
    {
        Collection<Tamtam> tamtams = Storage.findAllTamtams();
        JSONArray result = new JSONArray();

        JSONArray woods = new JSONArray("woods");
        JSONArray skins = new JSONArray("skins");
        JSONArray brand = new JSONArray("brands");

        for(Tamtam tamtam: tamtams) {
            woods.put(tamtam.getWood());
            skins.put(tamtam.getSkin());
            brand.put(tamtam.getBrand());
        }
        result.put(woods);
        result.put(skins);
        result.put(brand);
        return Response.ok().entity(result.toString(2)).build();
    }



    @Path("/tamtams/")
}
