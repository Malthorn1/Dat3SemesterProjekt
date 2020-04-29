/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 *
 * @author casper
 */
public class OpenCageDTO {
    
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lng")
    private String lng;
    @JsonProperty("country")
    private String country;
    @JsonProperty("country_code")
    private String countryCode;
    
    public OpenCageDTO(JsonObject jObj){
        
        JsonArray results = jObj.getAsJsonArray("results");
        JsonObject result = results.get(0).getAsJsonObject();
        JsonObject components = result.get("components").getAsJsonObject();
        JsonObject geometry = result.get("geometry").getAsJsonObject();
        
        this.lat = geometry.get("lat").getAsString();
        this.lng = geometry.get("lng").getAsString();
        this.country = components.get("country").getAsString();
        this.countryCode = components.get("country_code").getAsString();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    
    
    
   
    
    
    
}
