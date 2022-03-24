package fr.jtomasi.feveventbackend;

import fr.jtomasi.feveventbackend.api.model.Asso;
import fr.jtomasi.feveventbackend.api.model.Event;
import fr.jtomasi.feveventbackend.api.repository.AssoRepository;
import fr.jtomasi.feveventbackend.api.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql(scripts = "/insert.sql")
@Sql(scripts = "/cleanup.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class EventTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AssoRepository assoRepository;

    @Test
    public void findById(){
        Event event = eventRepository.getEventById(1);
        Assertions.assertNotNull(event);
    }

    @Test
    public void testGetEventNotCreated(){
        Event event = eventRepository.getEventById(3);
        Assertions.assertNull(event);
    }

    @Test
    public void testGetName(){
        Event event = eventRepository.getEventById(1);
        Assertions.assertEquals("Congrès FES",event.getName());
    }

    @Test
    public void testGetDate(){
        Event event = eventRepository.getEventById(1);
        Assertions.assertEquals("18/02/2022",event.getDate());
    }

    @Test
    public void testGetDescription(){
        Event event = eventRepository.getEventById(1);
        Assertions.assertEquals("Congrès Femmes en Sciences",event.getDescription());
    }

    @Test
    public void testGetPosterUrl(){
        Event event = eventRepository.getEventById(1);
        Assertions.assertEquals("/congres_fes.png",event.getPosterUrl());
    }

    @Test
    public void testGetAssoOrganizer(){
        Event event = eventRepository.getEventById(1);

        Asso asso = assoRepository.findAssoById(event.getAssoOrganizer1Id());

        Assertions.assertNotNull(asso);
    }

}
