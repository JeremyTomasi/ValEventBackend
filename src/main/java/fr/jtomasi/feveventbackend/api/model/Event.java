package fr.jtomasi.feveventbackend.api.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Classe permettant de représenter un événement
 */
@Entity
@Table(name = "events")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String posterUrl;

    @Column(columnDefinition = "text")
    private String description;

    private String date;

    @Column(name = "asso_organizer1_id")
    private int assoOrganizer1Id;

    public Event(String name,String date, String description,String posterUrl, int assoOrganizer1){
        this.name = name;
        this.date = date;
        this.description = description;
        this.assoOrganizer1Id = assoOrganizer1;
        this.posterUrl = posterUrl;
    }

    public Event() {}
}
