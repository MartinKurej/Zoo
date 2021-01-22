import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Path("animals")
@Produces(MediaType.APPLICATION_JSON)
public class zoo {

    static HashMap<Integer, HashMap<String, String>> animals = new HashMap<>();
    static Integer index = 0;

    @GET
    public Response getanimals() {
        return Response.ok(animals).build();
    }

    @GET
    @Path("{index}")
    public Response getAnim(@PathParam("index") Integer index) {
        if (index != null && animals.get(index) != null) {
            return Response.ok(animals.get(index)).build();
        } else {
            return Response.ok("Nothing was found.").build();
        }
    }

    @POST
    public Response giveNewAnimal(@FormParam("name") String name, @FormParam("age") String age) {
        if (name != null && age != null) {
            HashMap<String, String> newAnim = new HashMap<>();
            newAnim.put("name", name);
            newAnim.put("age", age);
            animals.put(index, newAnim);
            index++;
            return Response.ok(animals).build();
        } else {
            return Response.ok("Name and Age is missing.").build();
        }
    }

    @DELETE
    @Path("{index}")
    public Response slaughterAnim(@PathParam("index") Integer index) {
        if (index != null) {
            animals.remove(index);
        }
        return Response.ok(animals).build();
    }
}
