package fr.jtomasi.feveventbackend;

import fr.jtomasi.feveventbackend.api.model.Asso;
import fr.jtomasi.feveventbackend.api.repository.AssoRepository;
import fr.jtomasi.feveventbackend.api.service.AssoService;
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
public class AssociationTest {

    private AssoService assoService;

    @Mock
    private AssoRepository assoRepository;

    @BeforeEach
    void setUp(){
        Asso asso = new Asso(1,"SPHIMX","sphimx@sphimx.com","Association des etudiants en sciences","/test.png");
        Mockito.when(assoRepository.findAssoById(1))
                .thenReturn(asso);

        Mockito.when(assoRepository.findAssoByEmail("sphimx@sphimx.com"))
                .thenReturn(asso);

        assoService = new AssoService(assoRepository);
    }

    @Test
    public void findById(){
        Asso asso = assoService.getAssoById(1);
        Assertions.assertNotNull(asso);
    }

    @Test
    public void testGetAssoNotCreated(){
        Asso asso = assoService.getAssoById(3);
        Assertions.assertNull(asso);
    }

    @Test
    public void findByEmail(){
        Asso asso = assoService.getAssoByEmail("sphimx@sphimx.com");
        Assertions.assertNotNull(asso);
    }

    @Test
    public void getAssoName(){
        Asso asso = assoService.getAssoById(1);
        Assertions.assertEquals("SPHIMX",asso.getName());
    }

    @Test
    public void getAssoEmail(){
        Asso asso = assoService.getAssoById(1);
        Assertions.assertEquals("sphimx@sphimx.com",asso.getEmail());
    }

    @Test
    public void getProfilePicLink(){
        Asso asso = assoService.getAssoById(1);
        Assertions.assertEquals("/test.png",asso.getProfilePicLink());
    }

    @Test
    public void getDescription(){
        Asso asso = assoService.getAssoById(1);
        Assertions.assertEquals("Association des etudiants en sciences",asso.getDescription());
    }

    @Test
    public void testUpdateAssoName(){
        assoService.updateAsso(1,"SPHIMX2",null,null,null);

        Asso asso = assoService.getAssoById(1);
        Assertions.assertEquals("SPHIMX2",asso.getName());
        Assertions.assertEquals("sphimx@sphimx.com",asso.getEmail());
        Assertions.assertEquals("Association des etudiants en sciences",asso.getDescription());
        Assertions.assertEquals("/test.png",asso.getProfilePicLink());
    }

    @Test
    public void testUpdateAssoEmail(){
        assoService.updateAsso(1,null,"sphimx2@sphimx2.com",null,null);

        Asso asso = assoService.getAssoById(1);
        Assertions.assertEquals("sphimx2@sphimx2.com",asso.getEmail());
        Assertions.assertEquals("SPHIMX",asso.getName());
        Assertions.assertEquals("Association des etudiants en sciences",asso.getDescription());
        Assertions.assertEquals("/test.png",asso.getProfilePicLink());
    }

    @Test
    public void testUpdateAssoDescription(){
        assoService.updateAsso(1,null,null,"test",null);

        Asso asso = assoService.getAssoById(1);
        Assertions.assertEquals("test",asso.getDescription());
        Assertions.assertEquals("SPHIMX",asso.getName());
        Assertions.assertEquals("sphimx@sphimx.com",asso.getEmail());
        Assertions.assertEquals("/test.png",asso.getProfilePicLink());
    }

    @Test
    public void testUpdateAssoProfilePicLink(){
        assoService.updateAsso(1,null,null,null,"/test2.png");

        Asso asso = assoService.getAssoById(1);
        Assertions.assertEquals("/test2.png",asso.getProfilePicLink());
        Assertions.assertEquals("SPHIMX",asso.getName());
        Assertions.assertEquals("sphimx@sphimx.com",asso.getEmail());
        Assertions.assertEquals("Association des etudiants en sciences",asso.getDescription());
    }
}
