/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;


import com.google.gson.JsonObject;

/**
 *
 * @author casper
 */
public class OpenCageDTO {
    
    private String lat;
    private String lng;
    private String country;
    private String countrycode;
    
    public OpenCageDTO(JsonObject jobj){
        
        this.country = jobj.get("components").getAsJsonObject().get("country").getAsString();
        this.countrycode = jobj.get("components").getAsJsonObject().get("country_code").getAsString();
        this.lat = jobj.get("geometry").getAsJsonObject().get("lat").getAsString();
        this.lng = jobj.get("geometry").getAsJsonObject().get("lng").getAsString();
        
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

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

 
}
