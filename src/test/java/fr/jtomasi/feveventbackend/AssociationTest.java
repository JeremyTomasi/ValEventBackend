package fr.jtomasi.feveventbackend;

import fr.jtomasi.feveventbackend.api.model.Asso;
import fr.jtomasi.feveventbackend.api.repository.AssoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql(scripts = "/insert.sql")
@Sql(scripts = "/cleanup.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AssociationTest {
    @Autowired
    private AssoRepository assoRepository;

    @Test
    public void findById(){
        Asso asso = assoRepository.findAssoById(1);
        Assertions.assertNotNull(asso);
    }

    @Test
    public void testGetAssoNotCreated(){
        Asso asso = assoRepository.findAssoById(3);
        Assertions.assertNull(asso);
    }

    @Test
    public void findByEmail(){
        Asso asso = assoRepository.findAssoByEmail("sphimx@sphimx.com");
        Assertions.assertNotNull(asso);
    }

    @Test
    public void getAssoName(){
        Asso asso = assoRepository.findAssoById(1);
        Assertions.assertEquals("SPHIMX",asso.getName());
    }

    @Test
    public void getAssoEmail(){
        Asso asso = assoRepository.findAssoById(1);
        Assertions.assertEquals("sphimx@sphimx.com",asso.getEmail());
    }

    @Test
    public void getProfilePicLink(){
        Asso asso = assoRepository.findAssoById(1);
        Assertions.assertEquals("/test.png",asso.getProfilePicLink());
    }

    @Test
    public void getDescription(){
        Asso asso = assoRepository.findAssoById(1);
        Assertions.assertEquals("Association des etudiants en sciences",asso.getDescription());
    }
}
