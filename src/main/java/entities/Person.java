package entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;


@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fName;
    private String lName;
    private String phone;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastedited;
    
    public Person() {
    }

    public Person(Long id, String firstname, String lastname, String phone) {
        this.id = id;
        this.fName = firstname;
        this.lName = lastname;
        this.phone = phone;
    }
    
    
    
    public Person(String firstname, String lastname, String phone) {
        this.fName = firstname;
        this.lName = lastname;
        this.phone = phone;
        this.created = Calendar.getInstance().getTime();
        this.lastedited = Calendar.getInstance().getTime();
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastedited() {
        return lastedited;
    }

    public void setLastedited(Date lastedited) {
        this.lastedited = lastedited;
    }
    
   
}
