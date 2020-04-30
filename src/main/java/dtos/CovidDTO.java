/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.lang.reflect.Array;


/**
 *
 * @author casper
 */
public class CovidDTO {
    
    private String Country; 
    private int Confirmed; 
    private int Deaths; 
    private int Recovered; 
    private int Active; 
    private String Date; 

    public CovidDTO() {
    }
    
    

    public CovidDTO(JsonObject jobj) {
        this.Country = jobj.get("Country").getAsString(); 
        this.Confirmed =  jobj.get("Confirmed").getAsInt();
        this.Deaths = jobj.get("Deaths").getAsInt();
        this.Recovered = jobj.get("Recovered").getAsInt();
        this.Active = Confirmed - Deaths - Recovered;
        this.Date = jobj.get("Date").getAsString();
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public int getConfirmed() {
        return Confirmed;
    }

    public void setConfirmed(int Confirmed) {
        this.Confirmed = Confirmed;
    }

    public int getDeaths() {
        return Deaths;
    }

    public void setDeaths(int Deaths) {
        this.Deaths = Deaths;
    }

    public int getRecovered() {
        return Recovered;
    }

    public void setRecovered(int Recovered) {
        this.Recovered = Recovered;
    }

    

    public int getActive() {
        return Active;
    }

    public void setActive(int Active) {
        this.Active = Active;
    }


    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

 


  
    
    
}
