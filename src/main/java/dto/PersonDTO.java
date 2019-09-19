package dto;

import entities.Person;

public class PersonDTO {

    private long id;
    private String fName;
    private String lName;
    private String phone;

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
    
    public PersonDTO(Person p) {
        this.id = p.getId();
        this.fName = p.getfName();
        this.lName = p.getlName();
        this.phone = p.getPhone();
    }
    
    public PersonDTO() {
    }
}
