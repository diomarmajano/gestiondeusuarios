package gestion.usuarios.gestiondeusuarios.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import gestion.usuarios.gestiondeusuarios.repositories.userRepository;
import gestion.usuarios.gestiondeusuarios.model.Usuarios;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

@ExtendWith(MockitoExtension.class)
public class userServiceTest {

    @InjectMocks
    private userServiceImpl userServiceImpl;

    @Mock
    private userRepository userRepository;

    //Nos aseguramos de limpiar, eliminando todos los usuarios de la base de datos antes de cada prueba.
    //con esto evitamos que los datos de una prueba afecten a otra.
    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
       userRepository.deleteAll();
    }

    //Arrange: Se crea un objeto de la clase Usuarios y se le asignan valores a sus atributos.
    //Luego se utiliza Mockito para simular el comportamiento del repositorio de usuarios,
    //especificando que cuando se llame al método save del repositorio, se devolverá el objeto user.
    @Test
    public void saveUsuario() {
        Usuarios user = new Usuarios();
        user.setNombre("Diomar");
        user.setApellido("Cruz");
        user.setEmail("diomar@gmail.com");
        user.setPassword("12345678");
        user.setDireccion("Calle 123    ");
        user.setRol("admin");
        when(userRepository.save(any(Usuarios.class))).thenReturn(user);
                
        //Act: Se llama al método createUser del servicio y se le pasa el objeto user como parámetro.
        Usuarios resultado = userServiceImpl.createUser(user);

        //Assert: Se verifica que el resultado del servicio sea el usuario creado
        assertEquals(user.getNombre(), resultado.getNombre());
        assertEquals(user.getApellido(), resultado.getApellido());
        assertEquals(user.getEmail(), resultado.getEmail());
        assertEquals(user.getPassword(), resultado.getPassword());
        assertEquals(user.getDireccion(), resultado.getDireccion());
        assertEquals(user.getRol(), resultado.getRol());
    }

    @Test
    public void updateUsuario() {
        // Arrange: Crear un usuario inicial y simular su actualización
        Long userId = 1L; // ID del usuario existente

        Usuarios updatedUser = new Usuarios();
        updatedUser.setId(userId);
        updatedUser.setNombre("Diomar Updated");
        updatedUser.setApellido("Rodriguez Updated");
        updatedUser.setEmail("diomar_updated@gmail.com");
        updatedUser.setPassword("87654321");
        updatedUser.setDireccion("Calle 456");
        updatedUser.setRol("user");

        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.save(any(Usuarios.class))).thenReturn(updatedUser);

        // Act: Llamar al método updateUser del servicio
        Usuarios resultado = userServiceImpl.updateUser(userId, updatedUser);

        // Assert: Verificar que los valores del usuario se hayan actualizado correctamente
        assertEquals(updatedUser.getNombre(), resultado.getNombre());
        assertEquals(updatedUser.getApellido(), resultado.getApellido());
        assertEquals(updatedUser.getEmail(), resultado.getEmail());
        assertEquals(updatedUser.getPassword(), resultado.getPassword());
        
    }
}
