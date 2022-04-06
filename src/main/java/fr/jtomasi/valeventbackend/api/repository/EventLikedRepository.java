package fr.jtomasi.valeventbackend.api.repository;

import fr.jtomasi.valeventbackend.api.model.EventLiked;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EventLikedRepository extends CrudRepository<EventLiked,Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM event_liked where event_id = :event_id and member_id = :member_id",nativeQuery = true)
    public void removeEventLikedByMemberIdAndEventId(@Param("member_id") int memberId, @Param("event_id") int eventId);
}
