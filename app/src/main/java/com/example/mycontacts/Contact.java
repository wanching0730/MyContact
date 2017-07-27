package com.example.mycontacts;

/**
 * Created by James Ooi on 26/7/2017.
 */

public class Contact implements java.io.Serializable {

    //primary key in database
    private long id;
    private String name;
    private String email;
    private String phone;

    public Contact() {}

    public Contact(String name, String email, String phone) {
        this.id = 0;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Contact(long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public long getId() {return id;}
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}

    public void setId(long id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}
    public void setPhone(String phone) {this.phone = phone;}
}
