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

    public Event getEventById(int id){
        return eventRepository.getEventById(id);
    }

    public Iterable<Event> getEvents(){
        return eventRepository.findAll();
    }

    public void addEvent(Event event){
        eventRepository.save(event);
    }

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

    public Iterable<Event> getEventsByAssoOrganizer(int assoOrganizer){
        return eventRepository.getEventsByAssoOrganizer(assoOrganizer);
    }

    public void addFavourite(int eventId,int memberId){
        EventLiked eventLiked = new EventLiked(eventId,memberId);
        eventLikedRepository.save(eventLiked);
    }

    public Iterable<Event> getEventsLikedByMemberId(int memberId){
        return eventRepository.getEventsLikedByMemberId(memberId);
    }

    public Event getEventByMemberIdAndEventId(int memberId, int eventId){
        return eventRepository.getEventByMemberIdAndEventId(memberId,eventId);
    }

    public void removeFavourite(int memberId, int eventId){
        eventLikedRepository.removeEventLikedByMemberIdAndEventId(memberId,eventId);
    }
}
