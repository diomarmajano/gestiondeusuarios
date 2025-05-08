package gestion.usuarios.gestiondeusuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import gestion.usuarios.gestiondeusuarios.model.Usuarios;
import gestion.usuarios.gestiondeusuarios.service.userService;

@WebMvcTest(userController.class)
public class userControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private userService usuariosServiceMock;

    private Usuarios user;

    @BeforeEach
    public void setUp() {
        user = new Usuarios();
        user.setNombre("Diomar");
        user.setApellido("Cruz");
        user.setEmail("diomar@gmail.com");
        user.setPassword("12345678");
        user.setDireccion("Calle 123");
        user.setRol("admin");
    }

    @Test
    public void testGetAllUser() throws Exception {
        List<Usuarios> usuarios = Arrays.asList(user);

        when(usuariosServiceMock.getAllUsers()).thenReturn(usuarios);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuariosList[0].nombre", Matchers.is(user.getNombre())))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuariosList[0].apellido", Matchers.is(user.getApellido())))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuariosList[0].email", Matchers.is(user.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuariosList[0].password", Matchers.is(user.getPassword())))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuariosList[0].direccion", Matchers.is(user.getDireccion())))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuariosList[0].rol", Matchers.is(user.getRol())));
    }
}
