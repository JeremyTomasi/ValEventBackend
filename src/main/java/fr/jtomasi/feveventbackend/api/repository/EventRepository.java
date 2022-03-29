package fr.jtomasi.feveventbackend.api.repository;

import fr.jtomasi.feveventbackend.api.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends CrudRepository<Event,Integer> {
    public Event getEventById(int id);

    @Query(value = "SELECT * FROM events WHERE asso_organizer1_id = :id_asso",nativeQuery = true)
    Iterable<Event> getEventsByAssoOrganizer(@Param("id_asso") int idAsso);

    @Query(value = "select events.* from events, event_liked where event_liked.member_id = :memberId and events.id = event_liked.event_id",nativeQuery = true)
    Iterable<Event> getEventsLikedByMemberId(int memberId);
}
