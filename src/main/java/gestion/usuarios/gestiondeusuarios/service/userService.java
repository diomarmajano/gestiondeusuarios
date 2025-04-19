package gestion.usuarios.gestiondeusuarios.service;

import gestion.usuarios.gestiondeusuarios.model.Usuarios;

import java.util.List;
import java.util.Optional;

public interface userService {
    Usuarios createUser(Usuarios user);
    Usuarios updateUser(Long id, Usuarios user);
    void deleteUserById(Long id);
    public List<Usuarios> getAllUsers();
    Optional<Usuarios> findUserById(Long id);
    public List<Usuarios> logginSesion(String email, String password);
}
