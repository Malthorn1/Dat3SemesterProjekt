package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dtos.OpenCageDTO;
import utils.EMF_Creator;
import facades.NASAFacade;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import utils.HttpUtils;
import utils.Settings;


@Path("all")
public class NASAResource {
        private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/dat3",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final NASAFacade FACADE =  NASAFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    
    
    @Path("info")
    @GET
    public String getAllInfo() throws IOException{
        String  key  = Settings.getPropertyValue("apikey.opencage");
        String geoData = HttpUtils.fetchData("https://api.opencagedata.com/geocode/v1/json?q=copenhagen&key="+ key);
        
        
        OpenCageDTO ocDTO = GSON.fromJson(geoData, OpenCageDTO.class);
        System.out.println(ocDTO);
        
        
        return  GSON.toJson(ocDTO);
        
    }
    


    
}
