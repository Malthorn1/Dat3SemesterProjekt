package facades;

import dtos.SearchHistoryDTOs;
import utils.EMF_Creator;
import entities.RenameMe;
import entities.SearchHistory;
import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class UserFacadeTest {

    private static EntityManagerFactory emf;
    private static UserFacade facade;
    private static User user = new User("user", "test123");
    public UserFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/dat3_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = UserFacade.getUserFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = UserFacade.getUserFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("SearchHistory.deleteAllRows").executeUpdate();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            
            
            
            
            em.persist(user);
            
            SearchHistory searchHistory = new SearchHistory("this is a test", new java.util.Date(), user);
            //user.addSearchHistory(searchHistory);
            em.persist(searchHistory);
            
           
            
            

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGetSearchHistory(){
        
        SearchHistoryDTOs searchHistory = facade.getSearchHistory("user");
        assertFalse(searchHistory.getAllSearches().isEmpty());
        
    }
    @Test
    public void testAddSearchHistory(){
        String search = "this is a test 2";
        facade.addSearchToHistory(search, user.getUserName());
        SearchHistoryDTOs searchHistory = facade.getSearchHistory("user");

        assertEquals(2, searchHistory.getAllSearches().size());
        assertEquals(user.getUserName(), searchHistory.getAllSearches().get(0).getUser());
        
        
    }

}
