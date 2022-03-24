package fr.jtomasi.feveventbackend.api.model;

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

    public User() {}
}
