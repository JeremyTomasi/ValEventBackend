package fr.jtomasi.feveventbackend.api.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "assos")
@Data
public class Asso extends Member {

    private String description;

    public Asso(String name, String email,String description, String profilePicLink){
        super(name,email,profilePicLink);
        this.description = description;
    }

    public Asso() {}
}
