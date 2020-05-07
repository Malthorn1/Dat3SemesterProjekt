/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 *
 * @author casper
 */
public class WeatherDTO {
    
    private String timezone;
    private JsonObject current;
    private JsonArray daily;
    
    public WeatherDTO(JsonObject jobj){
        this.timezone = jobj.get("timezone").getAsString();
        this.current = jobj.get("current").getAsJsonObject();
        this.daily = jobj.get("daily").getAsJsonArray();
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public JsonObject getCurrent() {
        return current;
    }

    public void setCurrent(JsonObject current) {
        this.current = current;
    }

    public JsonArray getDaily() {
        return daily;
    }

    public void setDaily(JsonArray daily) {
        this.daily = daily;
    }

    

   

    
    
    
    
    
    
}
