package gestion.usuarios.gestiondeusuarios.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import gestion.usuarios.gestiondeusuarios.model.Usuarios;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
public class userRepositoryTest {
    @Autowired
    private userRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
       userRepository.deleteAll();
    }

    @Test
    public void testSaveUser() {
        // Arrange: Se crea un objeto de la clase Usuarios y se le asignan valores a sus atributos.
        Usuarios user = new Usuarios();
        user.setNombre("Diomar");
        user.setApellido("Cruz");
        user.setEmail("diomar@gmail.com");
        user.setPassword("12345678");
        user.setDireccion("Calle 123");
        user.setRol("admin");

        Usuarios resultado = userRepository.save(user);
        assertNotNull(resultado.getId());
        assertEquals(user.getNombre(), resultado.getNombre());
        assertEquals(user.getApellido(), resultado.getApellido());
        assertEquals(user.getEmail(), resultado.getEmail());
        assertEquals(user.getPassword(), resultado.getPassword());
        assertEquals(user.getDireccion(), resultado.getDireccion());
        assertEquals(user.getRol(), resultado.getRol());
    }
}
