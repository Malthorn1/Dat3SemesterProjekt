package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dtos.CombinedDTO;
import dtos.CovidDTO;
import dtos.NASADTO;
import dtos.OpenCageDTO;
import dtos.WeatherDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.HttpUtils;
import utils.Settings;

@Path("all")
public class NASAResource {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static ExecutorService executor = Executors.newFixedThreadPool(4);

    @Path("info/{q}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllInfo(@PathParam("q") String q) throws IOException, InterruptedException, ExecutionException {

        OpenCageDTO ocDTO = getGeoInfo(q);
        
        Future<String> satFuture = getSatelliteImg(ocDTO.getLat(), ocDTO.getLng());
        Future<JsonObject> weatherFuture = getWeatherData(ocDTO.getLat(), ocDTO.getLng());
        
        
        
        String satImg = satFuture.get();
        JsonObject weatherData = weatherFuture.get();
        
        NASADTO nDTO = new NASADTO(satImg);
        
        WeatherDTO wDTO = new WeatherDTO(weatherData);
        

        CombinedDTO combined = new CombinedDTO(ocDTO, wDTO, nDTO);

        return GSON.toJson(combined);
    }

    private static OpenCageDTO getGeoInfo(String parameter) throws IOException {
        String key = Settings.getPropertyValue("apikey.opencage");
        String geoData = HttpUtils.fetchData("https://api.opencagedata.com/geocode/v1/json?q=" + parameter + "&key=" + key);
        JsonObject jobj = new Gson().fromJson(geoData, JsonObject.class);

        OpenCageDTO ocDTO = new OpenCageDTO(jobj.get("results").getAsJsonArray().get(0).getAsJsonObject());
        return ocDTO;

    }

    private static Future<String> getSatelliteImg(String lat, String lng) throws IOException {
        String key = Settings.getPropertyValue("apikey.nasa");
        return executor.submit(() -> {
            return HttpUtils.fetchImg("https://api.nasa.gov/planetary/earth/imagery?lon=" + lng + "&lat=" + lat + "&dim=0.15&api_key=" + key);
        });

    }

    private static Future<ArrayList> getCovidInfo(String parameter) throws IOException {
        return executor.submit(() -> {
            String covidInfo = HttpUtils.fetchData("https://api.covid19api.com/total/dayone/country/" + parameter);
            JsonArray jobj = new Gson().fromJson(covidInfo, JsonArray.class);
            ArrayList covidData = new ArrayList();
            for (int i = 0; i < jobj.size(); i++) {
                CovidDTO cDTO = new CovidDTO(jobj.get(i).getAsJsonObject());
                covidData.add(cDTO);
            }
            return covidData;

        });
        
    }
    
    private static Future<JsonObject> getWeatherData(String lat, String lng){
        String key = Settings.getPropertyValue("apikey.openweather");
        return executor.submit(()-> {
            
           String data =  HttpUtils.fetchData("https://api.openweathermap.org/data/2.5/onecall?lat="+lat+"&lon="+lng+"&appid="+key);
           JsonObject json = GSON.fromJson(data, JsonObject.class);
            System.out.println(json);
           return json;
           
        });
        
    }

}
