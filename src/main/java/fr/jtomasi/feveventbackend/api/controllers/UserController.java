package fr.jtomasi.feveventbackend.api.controllers;

import fr.jtomasi.feveventbackend.api.model.User;
import fr.jtomasi.feveventbackend.api.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * Permet de récupérer la liste de tous les utilisateurs inscrits
     * @return La liste des utilisateurs inscrits au format JSON
     */
    @GetMapping("/users")
    public Iterable<User> getAllUsers(){
        return userService.getUsers();
    }

    /**
     * Permet de récupérer les informations d'un utilisateur selon son ID
     * @param id L'identifiant de l'utilisateur
     * @return Un objet JSON avec les informations de l'utilisateur ou un objet JSON vide si l'utilisateur est
     * introuvable
     */
    @RequestMapping(value = "/user/id/{id}",method = RequestMethod.GET)
    public User getUserWithId(@PathVariable int id){
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/user/email/{email}",method = RequestMethod.GET)
    public User getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    /**
     * Permet d'ajouter un utilisateur dans la base de données
     * @param name Le nom de l'utilisateur
     * @param email L'email qui va être associé au compte
     * @param firstName Le prénom de l'utilisateur (optionel dans le cas d'une association)
     */
    @PutMapping("/user/add")
    public void insertUser(@RequestParam String name, @RequestParam String firstName, @RequestParam String email, @RequestParam String profilePicLink){
        User user = new User(name,firstName,email,profilePicLink);
        userService.addUser(user);
    }

    @PostMapping("/user/update/{id}")
    public void updateUser(@PathVariable("id") int id, @RequestParam String name, @RequestParam String firstName, @RequestParam String email){
        userService.updateUser(id,name,firstName,email);
    }
}
