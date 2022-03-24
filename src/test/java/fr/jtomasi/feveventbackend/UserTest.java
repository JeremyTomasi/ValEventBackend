package fr.jtomasi.feveventbackend;

import fr.jtomasi.feveventbackend.api.model.User;
import fr.jtomasi.feveventbackend.api.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql(scripts = "/insert.sql")
@Sql(scripts = "/cleanup.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserById(){
        User user = userRepository.findById(1);
        Assertions.assertNotNull(user);
    }

    @Test
    public void testFindUserByEmail(){
        User user = userRepository.findByEmail("jeremy.tomasi@outlook.com");
        Assertions.assertNotNull(user);
    }

    @Test
    public void testGetLastName(){
        User user = userRepository.findById(1);
        Assertions.assertEquals("Tomasi",user.getName());
    }

    @Test
    public void testGetFirstName(){
        User user = userRepository.findById(1);
        Assertions.assertEquals("Jeremy",user.getFirstName());
    }

    @Test
    public void testGetEmail(){
        User user = userRepository.findById(1);
        Assertions.assertEquals("jeremy.tomasi@outlook.com",user.getEmail());
    }

    @Test
    public void testGetProfilePicLink(){
        User user = userRepository.findById(1);
        Assertions.assertEquals("/test.png",user.getProfilePicLink());
    }
}
