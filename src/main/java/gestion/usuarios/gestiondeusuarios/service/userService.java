package gestion.usuarios.gestiondeusuarios.service;
import gestion.usuarios.gestiondeusuarios.model.Usuarios;
import java.util.List;
import java.util.Optional;

public interface userService {
    Usuarios createUser(Usuarios user);
    Usuarios updateUser(Long id, Usuarios user);
    void deleteUserById(Long id);
    List<Usuarios> getAllUsers();
    Optional<Usuarios> findUserById(Long id);
    List<Usuarios> loginSession(String email, String password);
}
