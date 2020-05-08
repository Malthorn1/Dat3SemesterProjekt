package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Role;
import entities.User;
import utils.EMF_Creator;
import facades.FacadeExample;
import facades.UserFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("register")
public class RegisterResource {

//    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
//            "pu",
//            "jdbc:mysql://localhost:3307/dat3",
//            "dev",
//            "ax2",
//            EMF_Creator.Strategy.CREATE);
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    public static final UserFacade USER_FACADE = UserFacade.getUserFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response registerUser(String jsonString) {
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        User user = new User(username, password);
        Role role = new Role("user");
        user.addRole(role);
        USER_FACADE.registerUser(user);
        
        return Response.ok("User successfully created").build();

    }

}
