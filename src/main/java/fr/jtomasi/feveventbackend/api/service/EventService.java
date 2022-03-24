package fr.jtomasi.feveventbackend.api.service;

import fr.jtomasi.feveventbackend.api.model.Event;
import fr.jtomasi.feveventbackend.api.model.EventLiked;
import fr.jtomasi.feveventbackend.api.repository.EventLikedRepository;
import fr.jtomasi.feveventbackend.api.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private EventRepository eventRepository;
    private EventLikedRepository eventLikedRepository;

    public EventService(EventRepository eventRepository, EventLikedRepository eventLikedRepository){
        this.eventRepository = eventRepository;
        this.eventLikedRepository = eventLikedRepository;
    }

    /**
     * Récupère les informations d'un événement via son ID
     * @param id L'id de l'événement
     * @return Les informations de l'événement, null si l'événement est introuvable
     */
    public Event getEventById(int id){
        return eventRepository.getEventById(id);
    }

    /**
     * Recupère la liste des événements
     * @return La liste des événements
     */
    public Iterable<Event> getEvents(){
        return eventRepository.findAll();
    }

    /**
     * Ajoute un événement dans la BDD
     * @param event L'événement à ajouter
     */
    public void addEvent(Event event){
        eventRepository.save(event);
    }

    /**
     * Permet de mettre à jour un événement
     * @param id L'ID de l'événement
     * @param name Le nom de l'événement mis à jour
     * @param date La date de l'événement mis à jour
     * @param description La description de l'événement mis à jour
     * @param posterUrl Le lien de l'affiche mis à jour
     */
    public void updateEvent(int id, String name, String date, String description, String posterUrl){
        Event event = eventRepository.getEventById(id);
        if(event != null){
            event.setName(name);
            event.setDate(date);
            event.setDescription(description);
            event.setPosterUrl(posterUrl);

            eventRepository.save(event);
        }
    }

    /**
     * Recupère la liste des événements via l'ID d'une association
     * @param assoOrganizer L'ID de l'association
     * @return La liste des événements organisés par l'association passé en argument
     */
    public Iterable<Event> getEventsByAssoOrganizer(int assoOrganizer){
        return eventRepository.getEventsByAssoOrganizer(assoOrganizer);
    }

    /**
     * Ajoute un événement favori dans la BDD
     * @param eventId L'ID de l'événement
     * @param memberId L'ID du membre
     */
    public void addFavourite(int eventId,int memberId){
        EventLiked eventLiked = new EventLiked(eventId,memberId);
        eventLikedRepository.save(eventLiked);
    }

    /**
     * Recupère la liste des événements favori d'un membre
     * @param memberId L'ID du membre
     * @return La liste des événements favoris du membre
     */
    public Iterable<Event> getEventsLikedByMemberId(int memberId){
        return eventRepository.getEventsLikedByMemberId(memberId);
    }

    /**
     * Récupère l'événement selon l'identifiant de l'événement et du membre
     * @param memberId L'ID du membre
     * @param eventId L'ID de l'événement
     * @return Les infos de l'événement, null si l'événement est introuvable
     */
    public Event getEventByMemberIdAndEventId(int memberId, int eventId){
        return eventRepository.getEventByMemberIdAndEventId(memberId,eventId);
    }

    /**
     * Retire un événement de la liste des événements favoris du membre
     * @param memberId L'ID du membre
     * @param eventId L'ID de l'événement
     */
    public void removeFavourite(int memberId, int eventId){
        eventLikedRepository.removeEventLikedByMemberIdAndEventId(memberId,eventId);
    }
}
