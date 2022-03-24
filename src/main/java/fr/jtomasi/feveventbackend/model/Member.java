package fr.jtomasi.feveventbackend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String email;

    private String profilePicLink;

    public Member(){

    }

    public Member(String name, String email, String profilePicLink){
        this.name = name;
        this.email = email;
        this.profilePicLink = profilePicLink;
    }
}
