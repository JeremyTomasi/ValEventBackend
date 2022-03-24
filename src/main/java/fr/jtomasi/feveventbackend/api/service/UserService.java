package fr.jtomasi.feveventbackend.api.service;

import fr.jtomasi.feveventbackend.api.model.User;
import fr.jtomasi.feveventbackend.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Récupère les infos d'un utilisateur selon son ID
     * @param id L'ID de l'utilisateur
     * @return Les infos de l'utilisateur, null si l'utilisateur est introuvable
     */
    public User getUserById(int id){
        return userRepository.findById(id);
    }

    /**
     * Récupère les infos d'un utilisateur selon son ID
     * @param email L'email de l'utilisateur
     * @return Les infos de l'utilisateur, null si l'utilisateur est introuvable
     */
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    /**
     * Récupère la liste des utilisateurs
     * @return La liste des utilisateurs
     */
    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    /**
     * Ajoute un utilisateur dans la BDD
     * @param user L'utilisateur à ajouter
     */
    public void addUser(User user){
        userRepository.save(user);
    }

    /**
     * Permet de mettre à jour un utilisateur
     * @param id L'ID de l'utilisateur
     * @param name Le nom de l'utilisateur mis à jour
     * @param firstName Le prénom de l'utilisateur mis à jour
     * @param email L'email de l'utilisateur mis à jour
     */
    public void updateUser(int id, String name, String firstName, String email, String profilePicLink){
        User user = userRepository.findById(id);
        if(user != null){
            if(name != null){
                user.setName(name);
            }
            if(firstName != null){
                user.setFirstName(firstName);
            }
            if(email != null){
                user.setEmail(email);
            }
            if(profilePicLink != null){
                user.setProfilePicLink(profilePicLink);
            }
            userRepository.save(user);
        }
    }
}
