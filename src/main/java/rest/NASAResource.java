package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import dtos.CovidDTO;
import dtos.OpenCageDTO;
import utils.EMF_Creator;
import facades.NASAFacade;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.json.Json;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    private static final NASAFacade FACADE = NASAFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("info/{q}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllInfo(@PathParam("q") String q) throws IOException {
        return getGeoInfo(q);
    }



    private static String getGeoInfo(String parameter) throws IOException {
        String key = Settings.getPropertyValue("apikey.opencage");
        String geoData = HttpUtils.fetchData("https://api.opencagedata.com/geocode/v1/json?q=" + parameter + "&key=" + key);
        JsonObject jobj = new Gson().fromJson(geoData, JsonObject.class);

        OpenCageDTO ocDTO = new OpenCageDTO(jobj.get("results").getAsJsonArray().get(0).getAsJsonObject());
        return GSON.toJson(ocDTO);

    }

    private static ArrayList getCovidInfo(String parameter) throws IOException {
        String covidInfo = HttpUtils.fetchData("https://api.covid19api.com/total/dayone/country/" + parameter);
        JsonArray jobj = new Gson().fromJson(covidInfo, JsonArray.class);
        ArrayList covidData = new ArrayList();
        for (int i = 0; i < jobj.size(); i++) {
            CovidDTO cDTO = new CovidDTO(jobj.get(i).getAsJsonObject());
            covidData.add(cDTO);
        }
        return covidData;
    }

}
