package fr.jtomasi.feveventbackend.api.service;

import fr.jtomasi.feveventbackend.api.model.Asso;
import fr.jtomasi.feveventbackend.api.repository.AssoRepository;
import org.springframework.stereotype.Service;

@Service
public class AssoService {

    private final AssoRepository assoRepository;

    public AssoService(AssoRepository assoRepository) {
        this.assoRepository = assoRepository;
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
            asso.setName(name);
            asso.setEmail(email);
            asso.setDescription(description);
            asso.setProfilePicLink(profilePicLink);
            assoRepository.save(asso);
        }
    }
}
