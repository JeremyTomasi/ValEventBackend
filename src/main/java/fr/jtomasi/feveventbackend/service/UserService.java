package fr.jtomasi.feveventbackend.service;

import fr.jtomasi.feveventbackend.model.User;
import fr.jtomasi.feveventbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserById(int id){
        return userRepository.findById(id);
    }

    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(int id, String name, String firstName, String email){
        User user = userRepository.findById(id);
        if(user != null){
            user.setName(name);
            user.setFirstName(firstName);
            user.setEmail(email);
            userRepository.save(user);
        }
    }
}
