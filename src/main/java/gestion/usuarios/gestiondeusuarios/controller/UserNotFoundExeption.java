package gestion.usuarios.gestiondeusuarios.controller;

public class UserNotFoundExeption extends RuntimeException {
    public UserNotFoundExeption(String message) {
        super(message);
    }
}
