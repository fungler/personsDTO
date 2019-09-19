package facades;

import java.util.List;
import entities.Person;
import exceptions.PersonNotFoundException;

public interface IPersonFacade {
    public Person addPerson(String fName, String lName, String phone);  
    public Person deletePerson(int id) throws PersonNotFoundException;  
    public Person getPerson(int id);  
    public List<Person> getAllPersons();  
    public Person editPerson(Person p);
}

