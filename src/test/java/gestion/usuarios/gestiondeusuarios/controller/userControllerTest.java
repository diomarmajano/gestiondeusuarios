package gestion.usuarios.gestiondeusuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.hamcrest.Matchers;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import gestion.usuarios.gestiondeusuarios.model.Usuarios;
import gestion.usuarios.gestiondeusuarios.service.userService;

@WebMvcTest(userController.class)
public class userControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

    //Prueba para la peticion HHTP GET
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

    //Prueba para la peticion HHTP GET con el id del usuario
    @Test
    public void testGetUserById() throws Exception {
        Long userId = 1L;
        when(usuariosServiceMock.findUserById(userId)).thenReturn(java.util.Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/{id}", userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre", Matchers.is(user.getNombre())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.apellido", Matchers.is(user.getApellido())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is(user.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is(user.getPassword())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.direccion", Matchers.is(user.getDireccion())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rol", Matchers.is(user.getRol())));
}

    //Prueba para la peticion HHTP POST
    @Test
    public void createUser() throws Exception {
        when(usuariosServiceMock.createUser(user)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre", Matchers.is(user.getNombre())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.apellido", Matchers.is(user.getApellido())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is(user.getEmail())))     
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is(user.getPassword())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.direccion", Matchers.is(user.getDireccion())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rol", Matchers.is(user.getRol())));
            }

}
