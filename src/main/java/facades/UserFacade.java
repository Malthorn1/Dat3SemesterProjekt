package facades;

import dtos.SearchHistoryDTOs;
import entities.Role;
import entities.SearchHistory;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import errorhandling.AuthenticationException;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {
  
    private static EntityManagerFactory emf;
    private static UserFacade instance;
    
    private UserFacade(){}
    
    /**
     * 
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade (EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }
    
    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }
    
    public SearchHistoryDTOs getSearchHistory(String username){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            TypedQuery<SearchHistory> tq = em.createQuery("SELECT s FROM SearchHistory s where s.user.userName like :username ", SearchHistory.class);
            tq.setParameter("username", username);
            return new SearchHistoryDTOs(tq.getResultList());
            
        }finally{
            em.close();
        }
    }
    
    public void addSearchToHistory(String search, String userName){
        EntityManager em = emf.createEntityManager();
        try{
            User user = em.find(User.class, userName);
            SearchHistory searchHistory = new SearchHistory(search, new java.util.Date(), user);
            em.getTransaction().begin();
            em.persist(searchHistory);
            user.addSearchHistory(searchHistory);
            em.merge(user);
            em.getTransaction().commit();
            
        }finally{
            em.close();
        }
        
    }
    
    public void registerUser(User user){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        
    }
 
    public String populateDB () {
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("user1", "test123");
    User admin = new User("admin1", "test123");
    User both = new User("user_admin1", "test123");

    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
        return null;
   
  }


}
