package fr.unice.polytech.soa1.cookbook.rest;

import org.json.JSONArray;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
        Collection<Tamtam> tamtams = Storage.findAll();
        JSONArray result = new JSONArray();
        for(Tamtam tamtam: tamtams) {
            result.put(tamtam.getName());
        }
        return Response.ok().entity(result.toString(2)).build();
    }

    @Path("/tamtams/{id}")
    @GET
    public Response getTamtam(@PathParam("id") int id) {
        Tamtam tamtam = Storage.read(id);
        if(tamtam == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        String value = Storage.read(id).toString();
        return Response.ok().entity("\""+value+"\"").build();
    }

    @Path("/tamtams/search/marque/{marque}/peau/{peau}/bois/{bois}/")
    @GET
    public Response foundTamtam()
    {
        return Response.ok().entity("").build();
    }

    @Path("tamtams/types")
    @GET
    public Response getTypes()
    {
        return Response.ok().entity("").build();
    }


    @Path("/tamtams/brands")
    @GET
    public Response getBrands()
    {
        return Response.ok().entity("").build();
    }

    @Path("/tamtams/skins")
    @GET
    public Response getSkins()
    {
        return Response.ok().entity("").build();
    }

    @Path("/tamtams/wood")
    @GET
    public Response getWood()
    {
        return Response.ok().entity("").build();
    }
}
