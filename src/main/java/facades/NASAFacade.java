/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dtos.NASADTO;
import entities.RenameMe;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import utils.HttpUtils;

/**
 *
 * @author mikke
 */
public class NASAFacade {

    private String PokemonByURL = "https://pokeapi.co/api/v2/pokemon/";

    private static EntityManagerFactory emf;
    private static NASAFacade instance;

    public NASAFacade() {
    }

    public static NASAFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new NASAFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


}
