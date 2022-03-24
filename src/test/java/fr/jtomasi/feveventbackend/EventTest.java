package fr.jtomasi.feveventbackend;

import fr.jtomasi.feveventbackend.api.model.Asso;
import fr.jtomasi.feveventbackend.api.model.Event;
import fr.jtomasi.feveventbackend.api.repository.AssoRepository;
import fr.jtomasi.feveventbackend.api.repository.EventRepository;
import fr.jtomasi.feveventbackend.api.service.AssoService;
import fr.jtomasi.feveventbackend.api.service.EventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql(scripts = "/insert.sql")
@Sql(scripts = "/cleanup.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class EventTest {

    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    private AssoService assoService;

    @Mock
    private AssoRepository assoRepository;

    @BeforeEach
    void setUp(){
        Event event = new Event("Congrès FES","18/02/2022","Congrès Femmes en Sciences","/congres_fes.png",1);
        Mockito.when(eventRepository.getEventById(1))
                .thenReturn(event);

        Asso asso = new Asso(1,"SPHIMX","sphimx@sphimx.com","Association des etudiants en sciences","/test.png");
        Mockito.when(assoRepository.findAssoById(1))
                .thenReturn(asso);

        eventService = new EventService(eventRepository);
        assoService = new AssoService(assoRepository);
    }

    @Test
    public void findById(){
        Event event = eventService.getEventById(1);
        Assertions.assertNotNull(event);
    }

    @Test
    public void testGetEventNotCreated(){
        Event event = eventService.getEventById(3);
        Assertions.assertNull(event);
    }

    @Test
    public void testGetName(){
        Event event = eventService.getEventById(1);
        Assertions.assertEquals("Congrès FES",event.getName());
    }

    @Test
    public void testGetDate(){
        Event event = eventService.getEventById(1);
        Assertions.assertEquals("18/02/2022",event.getDate());
    }

    @Test
    public void testGetDescription(){
        Event event = eventService.getEventById(1);
        Assertions.assertEquals("Congrès Femmes en Sciences",event.getDescription());
    }

    @Test
    public void testGetPosterUrl(){
        Event event = eventService.getEventById(1);
        Assertions.assertEquals("/congres_fes.png",event.getPosterUrl());
    }

    @Test
    public void testGetAssoOrganizer(){
        Event event = eventRepository.getEventById(1);

        Asso asso = assoService.getAssoByOrganizerId(event.getAssoOrganizer1Id());

        Assertions.assertNotNull(asso);
        Assertions.assertEquals("SPHIMX",asso.getName());
    }

    @Test
    public void testUpdateEventName(){
        eventService.updateEvent(1,"Test",null,null,null);
        Event event = eventService.getEventById(1);

        Assertions.assertEquals("Test",event.getName());
        Assertions.assertEquals("18/02/2022",event.getDate());
        Assertions.assertEquals("Congrès Femmes en Sciences",event.getDescription());
        Assertions.assertEquals("/congres_fes.png",event.getPosterUrl());
    }

    @Test
    public void testUpdateEventDate(){
        eventService.updateEvent(1,null,"24/03/2022",null,null);
        Event event = eventService.getEventById(1);

        Assertions.assertEquals("Congrès FES",event.getName());
        Assertions.assertEquals("24/03/2022",event.getDate());
        Assertions.assertEquals("Congrès Femmes en Sciences",event.getDescription());
        Assertions.assertEquals("/congres_fes.png",event.getPosterUrl());
    }

    @Test
    public void testUpdateEventDescription(){
        eventService.updateEvent(1,null,null,"Test",null);
        Event event = eventService.getEventById(1);

        Assertions.assertEquals("Congrès FES",event.getName());
        Assertions.assertEquals("18/02/2022",event.getDate());
        Assertions.assertEquals("Test",event.getDescription());
        Assertions.assertEquals("/congres_fes.png",event.getPosterUrl());
    }

    @Test
    public void testUpdateEventPosterUrl(){
        eventService.updateEvent(1,null,null,null,"/test.png");
        Event event = eventService.getEventById(1);

        Assertions.assertEquals("Congrès FES",event.getName());
        Assertions.assertEquals("18/02/2022",event.getDate());
        Assertions.assertEquals("Congrès Femmes en Sciences",event.getDescription());
        Assertions.assertEquals("/test.png",event.getPosterUrl());
    }

}
