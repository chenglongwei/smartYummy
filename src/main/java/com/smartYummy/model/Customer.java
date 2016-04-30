package com.smartYummy.model;

/**
 * Created by chenglongwei on 4/21/16.
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * An entity Customer composed by three fields (id, email, name).
 * The Entity annotation indicates that this class is a JPA entity.
 * The Table annotation specifies the name for the table in the db.
 * @author chenglongwei
 */
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    // The customer's email
    @NotNull
    private String email;
    // The customer's name
    private String name;
    // The customer's email
    @NotNull
    private String password;

    public Customer() {
    }

    public Customer(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
