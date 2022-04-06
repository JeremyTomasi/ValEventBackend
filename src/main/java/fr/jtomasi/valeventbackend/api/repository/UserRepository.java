package fr.jtomasi.valeventbackend.api.repository;

import fr.jtomasi.valeventbackend.api.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer>
{
    @Query(value = "SELECT * FROM users WHERE email = :email",nativeQuery = true)
    public User findByEmail(String email);

    User findById(int id);
}
