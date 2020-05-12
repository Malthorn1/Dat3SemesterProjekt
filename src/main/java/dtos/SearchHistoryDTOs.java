/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.SearchHistory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author casper
 */
public class SearchHistoryDTOs {
    
    private List<SearchHistoryDTO> allSearches = new ArrayList();
    
    
    public SearchHistoryDTOs(List<SearchHistory> searchHistoryEntities){{
        for(SearchHistory searchHistoryEntity : searchHistoryEntities){
            allSearches.add(new SearchHistoryDTO(searchHistoryEntity.getSearch(), searchHistoryEntity.getDate(), searchHistoryEntity.getUser()));
        }
    }
    
}

    public List<SearchHistoryDTO> getAllSearches() {
        return allSearches;
    }
    
    
    
}
