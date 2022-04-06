package fr.jtomasi.valeventbackend.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Classe permettant de représenter une association
 */
@Entity
@Table(name = "assos")
@Getter
@Setter
public class Asso extends Member {

    private String description;

    public Asso(String name, String email,String description, String profilePicLink){
        super(name,email,profilePicLink);
        this.description = description;
    }

    public Asso(int id,String name, String email,String description, String profilePicLink){
        super(name,email,profilePicLink);
        super.setId(id);
        this.description = description;
    }

    public Asso() {}
}
