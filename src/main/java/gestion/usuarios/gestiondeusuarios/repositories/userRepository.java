package gestion.usuarios.gestiondeusuarios.repositories;

import gestion.usuarios.gestiondeusuarios.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface userRepository extends JpaRepository<Usuarios, Long> {
    //
    List<Usuarios> findByEmailAndPassword(String email, String password);

}
