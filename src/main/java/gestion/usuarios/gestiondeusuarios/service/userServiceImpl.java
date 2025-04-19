package gestion.usuarios.gestiondeusuarios.service;

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
    public List<Usuarios> logginSesion(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

}
