package fr.unice.polytech.soa1.cookbook.rest;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
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
            result.put(new JSONObject(tamtam.minToString()));
        }
        return Response.ok().entity(result.toString()).build();
    }

    /**
     * Détail d'un tamtam
     * @param id int
     * @return Ressource
     */
    @Path("/{id}")
    @GET
    public Response getTamtam(@PathParam("id") int id) {
        Tamtam tamtam = Storage.getTamtam(id);
        if(tamtam == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(Storage.getTamtam(id).toString()).build();
    }


    /**
     * Search for tamtam(s) that fit the parameters.
     * @param brand String
     * @param skin String
     * @param wood String
     * @return Object Tamtams with an array "tamtam" of Tamtam.
     */
    @Path("/search/brand/{brand}/skin/{skin}/wood/{wood}/")
    @GET
    public Response searchTamtam(@PathParam("brand") String brand, @PathParam("skin") String skin, @PathParam("wood") String wood)
    {
        Collection<Tamtam> tamtams = Storage.findAllTamtams();
        JSONArray result = new JSONArray();

        for(Tamtam tamtam: tamtams) {
            if(tamtam.getBrand().equals(brand) && tamtam.getSkin().equals(skin) && tamtam.getWood().equals(wood))
                result.put(new JSONObject(tamtam));
        }

        return Response.ok().entity(result.toString(2)).build();
    }

    @Path("/types")
    @GET
    public Response getTypes()
    {
        Collection<Tamtam> tamtams = Storage.findAllTamtams();
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
