package fr.jtomasi.feveventbackend;

import fr.jtomasi.feveventbackend.api.model.User;
import fr.jtomasi.feveventbackend.api.repository.UserRepository;
import fr.jtomasi.feveventbackend.api.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql(scripts = "/insert.sql")
@Sql(scripts = "/cleanup.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp(){
        User user = new User(1,"Tomasi","Jeremy","test@test.fr","/test.png");

        Mockito.when(userRepository.findById(1))
                .thenReturn(user);

        Mockito.when(userRepository.findByEmail("test@test.fr"))
                .thenReturn(user);

        userService = new UserService(userRepository);
    }

    @Test
    public void testFindUserById(){
        User user = userRepository.findById(1);
        Assertions.assertNotNull(user);
    }

    @Test
    public void testFindUserByEmail(){
        User user = userRepository.findByEmail("test@test.fr");
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
        Assertions.assertEquals("test@test.fr",user.getEmail());
    }

    @Test
    public void testGetProfilePicLink(){
        User user = userRepository.findById(1);
        Assertions.assertEquals("/test.png",user.getProfilePicLink());
    }

    @Test
    public void testUpdateUserName(){
        userService.updateUser(1,"Test",null,null,null);

        User user = userService.getUserById(1);
        Assertions.assertEquals("Test",user.getName());
        Assertions.assertEquals("Jeremy",user.getFirstName());
        Assertions.assertEquals("test@test.fr",user.getEmail());
        Assertions.assertEquals("/test.png",user.getProfilePicLink());
    }

    @Test
    public void testUpdateUserFirstName(){
        userService.updateUser(1,null,"Test",null,null);

        User user = userService.getUserById(1);
        Assertions.assertEquals("Test",user.getFirstName());
        Assertions.assertEquals("Tomasi",user.getName());
        Assertions.assertEquals("test@test.fr",user.getEmail());
        Assertions.assertEquals("/test.png",user.getProfilePicLink());
    }

    @Test
    public void testUpdateUserEmail(){
        userService.updateUser(1,null,null,"test2@test2.fr",null);
        User user = userService.getUserById(1);

        Assertions.assertEquals("test2@test2.fr",user.getEmail());
        Assertions.assertEquals("Tomasi",user.getName());
        Assertions.assertEquals("Jeremy",user.getFirstName());
        Assertions.assertEquals("/test.png",user.getProfilePicLink());
    }

    @Test
    public void testUpdateUserProfilePic(){
        userService.updateUser(1,null,null,null,"/test2.png");
        User user = userService.getUserById(1);

        Assertions.assertEquals("/test2.png",user.getProfilePicLink());
        Assertions.assertEquals("Tomasi",user.getName());
        Assertions.assertEquals("Jeremy",user.getFirstName());
        Assertions.assertEquals("test@test.fr",user.getEmail());
    }
}
