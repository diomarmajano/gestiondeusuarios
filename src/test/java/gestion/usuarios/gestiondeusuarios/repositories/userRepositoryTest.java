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

    private Usuarios user;
    @BeforeEach
    public void setUp() {
        user = new Usuarios();
        user.setNombre("Diomar");
        user.setApellido("Majano");
        user.setEmail("diomar@gmail.com");
        user.setPassword("12345678");
        user.setDireccion("Calle 123");
        user.setRol("admin");
    }

    @AfterEach
    public void tearDown() {
       userRepository.deleteAll();
    }
    //Test Metodo save del repositorio
    @Test
    public void testSaveUser() {
        Usuarios resultado = userRepository.save(user);
        assertNotNull(resultado.getId());
        assertEquals(user.getNombre(), resultado.getNombre());
        assertEquals(user.getApellido(), resultado.getApellido());
        assertEquals(user.getEmail(), resultado.getEmail());
        assertEquals(user.getPassword(), resultado.getPassword());
        assertEquals(user.getDireccion(), resultado.getDireccion());
        assertEquals(user.getRol(), resultado.getRol());
    }

    //Test Para probar Correcta actualizacion de un usuario
    @Test
    public void testUpdateUser(){
        final Long id = 1L;
        user.setId(id);
        user.setNombre("Diomar Rodriguez");
        Usuarios updatedUser = userRepository.save(user);
        assertEquals("Diomar Rodriguez", updatedUser.getNombre());
    }

    //Test Metodo deleteById del repositorio
    @Test
    public void testDeleteUser() {
        Usuarios savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());

        userRepository.deleteById(savedUser.getId());
        boolean exists = userRepository.findById(savedUser.getId()).isPresent();
        assertEquals(false, exists);

    }
}
