/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.SearchHistory;
import entities.User;
import java.util.Date;

/**
 *
 * @author casper
 */
public class SearchHistoryDTO {
    
    private String search;
    private String date;
    private String user;
    
    
    public SearchHistoryDTO(String search, Date date, User user){
        this.search = search;
        this.date = date.toString();
        this.user = user.getUserName();
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    
    
    
    
    
    
    
    
    
}
