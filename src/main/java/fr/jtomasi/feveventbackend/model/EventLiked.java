package fr.jtomasi.feveventbackend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "event_liked")
@Data
public class EventLiked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "eventId")
    private int eventId;

    @Column(name = "memberId")
    private int memberId;

    public EventLiked(){}

    public EventLiked(int eventId, int memberId){
        this.eventId = eventId;
        this.memberId = memberId;
    }
}
