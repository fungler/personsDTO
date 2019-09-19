package facades;

import entities.Person;
import exceptions.PersonNotFoundException;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersonFacade implements IPersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getPersonCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long numOfPersons = (long)em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return numOfPersons;
        }finally{  
            em.close();
        }
        
    }

    @Override
    public Person addPerson(String fName, String lName, String phone) {
        EntityManager em = emf.createEntityManager();
        try{
            
            Person toCreate = new Person(fName, lName, phone);
            
            em.getTransaction().begin();
            em.persist(toCreate);
            em.getTransaction().commit();
            
            return toCreate;
        }finally{  
            em.close();
        }
    }

    @Override
    public Person deletePerson(int id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        
        try {
            Person foundPerson = (Person)em.createQuery("SELECT p FROM Person p WHERE p.id = " + id, Person.class).getSingleResult();
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Person c WHERE c.id =" + id).executeUpdate();
            em.getTransaction().commit();
            return foundPerson;
        } finally {
            em.close();
        }
    }

    @Override
    public Person getPerson(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        
        try {
            List<Person> allPeeps = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
            return allPeeps;
        } finally {
            em.close();
        }

    }

    @Override
    public Person editPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        try {
            Person pEdit = em.find(Person.class, p.getId());

            em.getTransaction().begin();
            pEdit.setfName(p.getfName());
            pEdit.setlName(p.getlName());
            pEdit.setPhone(p.getPhone());
            pEdit.setLastedited(Calendar.getInstance().getTime());
            em.getTransaction().commit();
            
            return pEdit;
        } finally {
            em.close();
        }
    }

}
