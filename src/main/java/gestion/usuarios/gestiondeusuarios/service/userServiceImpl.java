package gestion.usuarios.gestiondeusuarios.service;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gestion.usuarios.gestiondeusuarios.repositories.userRepository;
import java.util.List;
import java.util.Optional;
import gestion.usuarios.gestiondeusuarios.model.Usuarios;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private userRepository userRepository;

    @Override
    public Usuarios createUser(Usuarios user) {
        return userRepository.save(user);
    }

    @Override
    public Usuarios updateUser(Long id, Usuarios user) {
        if(userRepository.existsById(id)){
            user.setId(id);
            return userRepository.save(user);
        }
        else {
            return null;
        }
    }

    @Override
    public void deleteUserById(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
        else {
            return;
        }
    }

    @Override
    public List<Usuarios> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public Optional<Usuarios> findUserById(Long id){
        return userRepository.findById(id);
    }

    @Override
    public List<Usuarios> loginSession(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    //el uso de esta anotacion fue estudiada de la documentacion  de: https://www.baeldung.com/spring-postconstruct-predestroy
    @PostConstruct
    public void init(){
        Usuarios user_1 = new Usuarios();
        user_1.setNombre("Gregory");
        user_1.setApellido("Majano");
        user_1.setPassword("gregory1");
        user_1.setEmail("gregory@gmail.cl");
        user_1.setRol("Dueño de mascota");
        user_1.setDireccion("santiago de chile");

        Usuarios user_2 = new Usuarios();
        user_2.setNombre("Margarita");
        user_2.setApellido("Guerra");
        user_2.setPassword("margari");
        user_2.setEmail("margarita@gmail.cl");
        user_2.setRol("Dueño de mascota");
        user_2.setDireccion("santiago de chile");

        Usuarios user_3 = new Usuarios();
        user_3.setNombre("Sebastian");
        user_3.setApellido("Gonzales");
        user_3.setPassword("seba8888");
        user_3.setEmail("sebastian@gmail.cl");
        user_3.setRol("conductor de transporte pet friendly");
        user_3.setDireccion("santiago de chile");

        userRepository.save(user_1);
        userRepository.save(user_2);
        userRepository.save(user_3);
    }
}
