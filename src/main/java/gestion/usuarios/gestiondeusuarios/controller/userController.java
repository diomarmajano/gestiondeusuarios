package gestion.usuarios.gestiondeusuarios.controller;

import gestion.usuarios.gestiondeusuarios.model.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import gestion.usuarios.gestiondeusuarios.service.userService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class userController  {
    private static final Logger log = LoggerFactory.getLogger(userController.class);

    @Autowired
    private userService userService;

    @PostMapping
    public EntityModel<Usuarios> createUser(@RequestBody Usuarios user){
        Usuarios newUser = userService.createUser(user);
        return EntityModel.of(newUser,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserById(newUser.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers()).withRel("usuarios"));
    }

    @PutMapping("/{id}")
    public EntityModel<Usuarios> updateUser(@PathVariable Long id, @RequestBody Usuarios user){
        Optional<Usuarios> existingUser = userService.findUserById(id);
        if (existingUser.isPresent()) {
            Usuarios updatedUser = userService.updateUser(id, user);
            return EntityModel.of(updatedUser,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers()).withRel("usuarios"));
        } else {
            throw new UserNotFoundExeption("Usuario no encontrado: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public EntityModel<String> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return EntityModel.of("Usuario eliminado con éxito",
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers()).withRel("usuarios"));
    }

    @GetMapping
    public CollectionModel<EntityModel<Usuarios>>getAllUsers() {
        List<Usuarios> usuarios = userService.getAllUsers();
        log.info("GET /usuarios");
        log.info("Retornando todos los usuarios");
        List<EntityModel<Usuarios>> usuariosResources = usuarios.stream()
        .map(usuariosItems -> EntityModel.of(usuariosItems,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserById(usuariosItems.getId())).withSelfRel()
        ))
        .collect(Collectors.toList());

    WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
    CollectionModel<EntityModel<Usuarios>> resources = CollectionModel.of(usuariosResources, linkTo.withRel("usuarios"));

    return resources;
        
    }
    @GetMapping("/{id}")
    public EntityModel<Usuarios> getUserById(@PathVariable Long id){
        Optional<Usuarios> usuario = userService.findUserById(id);
        if (usuario.isPresent()) {
            return EntityModel.of(usuario.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers()).withRel("usuarios"));
        } else {
            throw new UserNotFoundExeption("Usuario  no encontrada: " + id);
        }
       
    }

    @GetMapping("/{email}/{password}")
    public CollectionModel<EntityModel<Usuarios>> getUserByEmailAndPassword(@PathVariable String email, @PathVariable String password){
        List<Usuarios> usuarios = userService.loginSession(email, password);
        List<EntityModel<Usuarios>> usuariosResources = usuarios.stream()
            .map(usuariosItems -> EntityModel.of(usuariosItems,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserById(usuariosItems.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        return CollectionModel.of(usuariosResources, linkTo.withRel("usuarios"));
    }
}