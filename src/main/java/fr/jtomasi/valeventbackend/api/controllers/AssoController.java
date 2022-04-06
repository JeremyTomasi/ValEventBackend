package fr.jtomasi.valeventbackend.api.controllers;

import fr.jtomasi.valeventbackend.api.model.Asso;
import fr.jtomasi.valeventbackend.api.service.AssoService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AssoController {
    private final AssoService assoService;

    public AssoController(AssoService assoService){
        this.assoService = assoService;
    }

    /**
     * Permet de récupérer la liste de tous les associations dans la base de données
     * @return La liste des associations au format JSON
     */
    @GetMapping("/api/assos")
    public Iterable<Asso> getAllAssos(){
        return assoService.getAssos();
    }

    /**
     * Permet de récupérer les informations d'une association selon son ID
     * @param id L'identifiant de l'association
     * @return Un objet JSON avec les informations de l'association ou un objet JSON vide si l'association est
     * introuvable
     */
    @RequestMapping(value = "/api/asso/id/{id}",method = RequestMethod.GET)
    public Asso getAssoWithId(@PathVariable int id){
        return assoService.getAssoById(id);
    }

    /**
     * Permet de récupérer une association selon son email
     * @param email L'email de l'asso dont on veut récupérer les informations
     * @return L'asso correspondante à l'adresse mail, null sinon
     */
    @RequestMapping(value = "/api/asso/email/{email}", method = RequestMethod.GET)
    public Asso getAssoWithEmail(@PathVariable String email){
        return assoService.getAssoByEmail(email);
    }

    /**
     * Permet d'ajouter une association dans la base de données
     * @param name Le nom de l'association
     * @param email L'email qui va être associé au compte de l'association
     */
    @PutMapping("/api/asso/add")
    public void insertAsso(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String description,
                           @RequestParam String profilePicLink){
        Asso asso = new Asso(name,email,description,profilePicLink);
        assoService.addAsso(asso);
    }

    /**
     * Permet de mettre à jour une association
     * @param id L'id de l'association dans la BDD
     * @param name Le nom de l'association mise à jour
     * @param email L'email de l'association mise à jour
     * @param description La description de l'association mise à jour
     */
    @PostMapping("/api/asso/update/{id}")
    public void updateAsso(@PathVariable int id,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String description,
                           @RequestParam(required = false) String profilePicLink){
        assoService.updateAsso(id,name,email,description,profilePicLink);
    }

}
