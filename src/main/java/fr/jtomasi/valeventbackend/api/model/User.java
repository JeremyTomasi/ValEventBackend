package fr.jtomasi.valeventbackend.api.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Classe permettant de représenter un utilisateur
 */
@Entity
@Table(name = "users")
@Data
public class User extends Member{

    private String firstName;

    public User(String lastName, String firstName, String email, String profilePicLink){
        super(lastName,email,profilePicLink);
        this.firstName = firstName;
    }

    public User(int id,String lastName, String firstName, String email, String profilePicLink){
        super(lastName,email,profilePicLink);
        super.setId(id);
        this.firstName = firstName;
    }

    public User() {}
}
