package fr.jtomasi.feveventbackend.api.controllers;

import fr.jtomasi.feveventbackend.api.model.Event;
import fr.jtomasi.feveventbackend.api.service.EventService;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    /**
     * Permet de récupérer la liste de tous les utilisateurs inscrits
     * @return La liste des utilisateurs inscrits au format JSON
     */
    @GetMapping("/events")
    public Iterable<Event> getAllEvents(){
        return eventService.getEvents();
    }

    @GetMapping("/events/asso/id/{asso_id}")
    public Iterable<Event> getEventsByAssoId(@PathVariable int asso_id){
        return eventService.getEventsByAssoId(asso_id);
    }

    @PostMapping("/event/update/{id}")
    public void updateEvent(@PathVariable int id, @RequestParam String name, @RequestParam String date, @RequestParam String description, @RequestParam String posterUrl){
        eventService.updateEvent(id,name,date,description,posterUrl);
    }

    /**
     * Permet de récupérer les informations d'un utilisateur selon son ID
     * @param id L'identifiant de l'utilisateur
     * @return Un objet JSON avec les informations de l'utilisateur ou un objet JSON vide si l'utilisateur est
     * introuvable
     */
    @RequestMapping(value = "/event/id/{id}",method = RequestMethod.GET)
    public Event getEventById(@PathVariable int id){
        return eventService.getEventById(id);
    }

    @PutMapping("/event/add")
    public void addEvent(@RequestParam String name, @RequestParam String date, @RequestParam String description,@RequestParam String posterUrl, @RequestParam int assoOrganizerId){
        Event event = new Event(name,date,description,posterUrl,assoOrganizerId);
        eventService.addEvent(event);
    }

    @PutMapping("/event/add_favourite")
    public void addFavourite(@RequestParam int eventId, @RequestParam int memberId){
        eventService.addFavourite(eventId,memberId);
    }

    @GetMapping("/events/favourites/{memberId}")
    public Iterable<Event> getEventsLikedByMemberId(@PathVariable int memberId){
        return eventService.getEventsLikedByMemberId(memberId);
    }

    @GetMapping("/events/favourite/{memberId}/{eventId}")
    public Event getEventByMemberIdAndEventId(@PathVariable int memberId, @PathVariable int eventId){
        return eventService.getEventByMemberIdAndEventId(memberId,eventId);
    }

    @DeleteMapping("/events/remove_favourite/{eventId}/{memberId}")
    public void removeFavourite(@PathVariable int eventId, @PathVariable int memberId){
        eventService.removeFavourite(memberId,eventId);
    }
}
