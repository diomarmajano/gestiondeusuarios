package gestion.usuarios.gestiondeusuarios.repositories;
import gestion.usuarios.gestiondeusuarios.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface userRepository extends JpaRepository<Usuarios, Long> {
    //se crea un metodo propio para simular un acceso por email y contraseña
    List<Usuarios> findByEmailAndPassword(String email, String password);
}
