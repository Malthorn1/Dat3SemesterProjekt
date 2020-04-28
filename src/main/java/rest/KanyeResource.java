package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CatFactDTO;
import dtos.KanyeDTO;
import utils.EMF_Creator;
import facades.FacadeExample;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.HttpUtils;

//Todo Remove or change relevant parts before ACTUAL use
@Path("kanye")
public class KanyeResource {
    Gson  gson = new Gson();

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/dat3",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final FacadeExample FACADE =  FacadeExample.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getKanye() throws IOException {
        String kanyeQuote = HttpUtils.fetchData("https://api.kanye.rest/");
        
        KanyeDTO kanyeDTO = gson.fromJson(kanyeQuote, KanyeDTO.class);
        
        return  gson.toJson(kanyeDTO);
        
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getRenameMeCount();
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }

 
}
