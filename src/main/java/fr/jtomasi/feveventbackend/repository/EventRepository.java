package fr.jtomasi.feveventbackend.repository;

import fr.jtomasi.feveventbackend.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event,Integer> {
}
