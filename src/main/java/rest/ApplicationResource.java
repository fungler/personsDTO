package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import entities.Person;
import exceptions.PersonNotFoundException;
import utils.EMF_Creator;
import facades.PersonFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class ApplicationResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/person",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final PersonFacade FACADE =  PersonFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPersons() {
        List<Person> allPeeps = FACADE.getAllPersons();
        List<PersonDTO> apDTO = new ArrayList();
        
        for (Person p : allPeeps) {
            apDTO.add(new PersonDTO(p));
        }
        
        return GSON.toJson(apDTO);
    }

        @POST
	@Path("/addperson")
        @Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public String addPerson(String person) {
            PersonDTO p = GSON.fromJson(person, PersonDTO.class);
            Person pAdded = FACADE.addPerson(p.getfName(), p.getlName(), p.getPhone());
            return GSON.toJson(new PersonDTO(pAdded));
	}
        
        @PUT
	@Path("/editperson/{id}")
        @Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public String editPerson(@PathParam("id") long id, String pInfo) {
            PersonDTO p = GSON.fromJson(pInfo, PersonDTO.class);
            Person pEdit = new Person(p.getfName(), p.getfName(), p.getPhone());
            pEdit.setId(id);
            Person pEdited = FACADE.editPerson(pEdit);
            return GSON.toJson(new PersonDTO(pEdited));
            
	}

        // Jeg kan ikke få mine exceptions til at virke.
        // Derfor ser det sådan ud nu - det er bestemt ikke rigtigt, siden min exception ikke gør noget.
        // Laver ikke resten, fordi jeg udmærket godt ved, at det er forkert alligevel.
        @DELETE
	@Path("/deleteperson/{id}")
        @Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public String editPerson(@PathParam("id") int id) throws PersonNotFoundException {
            Person deletedPerson = FACADE.deletePerson(id);
            return GSON.toJson(new PersonDTO(deletedPerson));
	}
}
