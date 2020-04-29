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

    //   @JsonProperty("lat")
//    private JsonArray results;
    //  private JsonArray components; 
    //   @JsonProperty("lng")
    @JsonProperty("url")
    private String url;
    private String lng;
    private JsonArray licenses;
    //  private JsonArray results; 
    // @JsonProperty("country")
    private String country;
    // @JsonProperty("country_code")
    private String country_Code;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public OpenCageDTO(JsonObject jObj) {
//        this.licenses = jObj.getAsJsonObject("licenses").getAsJsonArray(); 
        
       
       this.licenses = jObj.getAsJsonArray("licenses"); 
//
//        JsonArray results = jObj.get("licenses").getAsJsonArray();
//        this.licenses = results;
//        this.url = jObj.
//    
                
                

        //this.url = temp.get(1).getAsString(); 
        //    this.url = licenses.get(0).get(1).getAsString(); 
        //  this.url = results.get(0).getAsString();
//       this.url =  licenses.get(0).getAsString();         
//      
//        
//        results = jObj.getAsJsonArray("results");
//        JsonObject result = results.get(0).getAsJsonObject();
//        JsonObject components = result.get("components").getAsJsonObject();
//        JsonObject geometry = result.get("geometry").getAsJsonObject();
//        
//        this.results = jObj.get("results").getAsJsonArray(); 
//        this.country_Code = results.get(0).getAsString(); 
////      
//        
//        this.lat = geometry.get("lat").getAsString();
//        this.lng = geometry.get("lng").getAsString();
//        this.country = components.get("country").getAsString();
//        this.countryCode = components.get("country_code").getAsString();
    }

//    public JsonArray getComponents() {
//        return components;
//    }
//
//    public void setComponents(JsonArray components) {
//        this.components = components;
//    }
//    
//
//    public JsonArray getResults() {
//        return results;
//    }
//
//    public void setResults(JsonArray results) {
//        this.results = results;
//    }
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

    public String getCountry_Code() {
        return country_Code;
    }

    public void setCountry_Code(String country_Code) {
        this.country_Code = country_Code;
    }

}
