package fr.jtomasi.feveventbackend.api.service;

import fr.jtomasi.feveventbackend.api.model.Asso;
import fr.jtomasi.feveventbackend.api.repository.AssoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssoService{

    private AssoRepository assoRepository;

    public AssoService(AssoRepository repository){
        assoRepository = repository;
    }

    /**
     * Permet de récupérer une association via son ID
     * @param id L'id de l'association
     * @return Les informations de l'association, null si l'association est introuvable
     */
    public Asso getAssoById(int id){
        return assoRepository.findAssoById(id);
    }

    /**
     * Permet de récupérer une association via son email
     * @param email L'email de l'association à rechercher
     * @return Les informations de l'association, null si l'association est introuvable
     */
    public Asso getAssoByEmail(String email){
        return assoRepository.findAssoByEmail(email);
    }

    /**
     * Récupère la liste des associations
     * @return La liste des associations
     */
    public Iterable<Asso> getAssos(){
        return assoRepository.findAll();
    }

    /**
     * Permet d'ajouter une association
     * @param asso L'association à ajouter dans la BDD
     */
    public void addAsso(Asso asso){
        assoRepository.save(asso);
    }

    /**
     * Permet de mettre à jour une association
     * @param id L'ID de l'association
     * @param name Le nom de l'association mise à jour
     * @param email L'email de l'association mise à jour
     * @param description La description de l'association mise à jour
     * @param profilePicLink Le lien de la photo de profil de l'association mise à jour
     */
    public void updateAsso(int id, String name, String email, String description, String profilePicLink){
        Asso asso = assoRepository.findAssoById(id);
        if(asso != null){
            if(name != null){
                asso.setName(name);
            }
            if(email != null){
                asso.setEmail(email);
            }
            if(description != null){
                asso.setDescription(description);
            }
            if(profilePicLink != null){
                asso.setProfilePicLink(profilePicLink);
            }
            assoRepository.save(asso);
        }
    }

    public Asso getAssoByOrganizerId(int assoId){
        return assoRepository.findAssoById(assoId);
    }
}
