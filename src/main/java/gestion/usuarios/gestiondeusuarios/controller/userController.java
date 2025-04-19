package gestion.usuarios.gestiondeusuarios.controller;

import gestion.usuarios.gestiondeusuarios.model.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import gestion.usuarios.gestiondeusuarios.service.userService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class userController {

    @Autowired
    private userService userService;

    @PostMapping
    public Usuarios createUser(@RequestBody Usuarios user){
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public Usuarios updateUser(@PathVariable Long id, @RequestBody Usuarios user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
    }

    @GetMapping
    public List<Usuarios> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public Optional<Usuarios> getUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @GetMapping("/{email}/{password}")
    public List<Usuarios>getUserByEmailAndPassword(@PathVariable String email, @PathVariable String password){
        return userService.logginSesion(email, password);
    }
}
