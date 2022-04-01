package fr.jtomasi.feveventbackend.api.controllers;

import fr.jtomasi.feveventbackend.api.model.Event;
import fr.jtomasi.feveventbackend.api.service.EventService;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    /**
     * Permet de récupérer la liste de tous les événements
     * @return La liste des événements au format JSON
     */
    @GetMapping("/events")
    public Iterable<Event> getAllEvents(){
        return eventService.getEvents();
    }

    /**
     * Permet de récupérer les événements selon l'ID d'une association
     * @param asso_id L'id de l'association dont on veut récupérer les événements
     * @return La liste des événements de l'association
     */
    @GetMapping("/events/asso/id/{asso_id}")
    public Iterable<Event> getEventsByAssoId(@PathVariable int asso_id) {
        return eventService.getEventsByAssoOrganizer(asso_id);
    }

    /**
     * Permet de mettre à jour un événement
     * @param id L'id de l'événement
     * @param name Le nom de l'événement mise à jour
     * @param date La date de l'événement mise à jour
     * @param description La description de l'événement mise à jour
     * @param posterUrl Le lien de l'affiche de l'événement mise à jour
     */
    @PostMapping("/event/update/{id}")
    public void updateEvent(@PathVariable int id,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String date,
                            @RequestParam(required = false) String description,
                            @RequestParam(required = false) String posterUrl){
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

    /**
     * Permet d'ajouter un événement
     * @param name Le nom de l'événement
     * @param date La date de l'événement
     * @param description La description de l'événement
     * @param posterUrl Le lien de l'affiche de l'événement
     * @param assoOrganizerId L'id de l'association organisatrice
     */
    @PutMapping("/event/add")
    public void addEvent(@RequestParam String name,
                         @RequestParam String date,
                         @RequestParam String description,
                         @RequestParam String posterUrl,
                         @RequestParam int assoOrganizerId){
        Event event = new Event(name,date,description,posterUrl,assoOrganizerId);
        eventService.addEvent(event);
    }

    /**
     * Permet de mettre en événement en favori pour l'utilisateur passé en argument
     * @param eventId L'identifiant de l'événement
     * @param memberId L'identifiant du membre
     */
    @PutMapping("/event/add_favourite")
    public void addFavourite(@RequestParam int eventId, @RequestParam int memberId){
        eventService.addFavourite(eventId,memberId);
    }

    /**
     * Permet de récupérer la liste des événements favori d'un membre
     * @param memberId L'identifiant du membre
     * @return La liste des événements favori du membre
     */
    @GetMapping("/events/favourites/{memberId}")
    public Iterable<Event> getEventsLikedByMemberId(@PathVariable int memberId){
        return eventService.getEventsLikedByMemberId(memberId);
    }

    /**
     * Permet de retirer un événement des événements favoris pour un membre et un événément donné
     * @param eventId L'identifiant de l'événement
     * @param memberId L'identifiant du membre
     */
    @DeleteMapping("/events/remove_favourite/{eventId}/{memberId}")
    public void removeFavourite(@PathVariable int eventId, @PathVariable int memberId){
        eventService.removeFavourite(memberId,eventId);
    }
}
