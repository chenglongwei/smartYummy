package com.smartYummy.model;

/**
 * Created by chenglongwei on 4/21/16.
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * An entity User composed by four fields (id, email, password, role).
 * The Entity annotation indicates that this class is a JPA entity.
 * The Table annotation specifies the name for the table in the db.
 * @author chenglongwei
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    // The customer's email
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    // The customer's email
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
