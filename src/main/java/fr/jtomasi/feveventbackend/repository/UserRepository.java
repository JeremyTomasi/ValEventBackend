package fr.jtomasi.feveventbackend.repository;

import fr.jtomasi.feveventbackend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
