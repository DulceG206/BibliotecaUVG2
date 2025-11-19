package controller;

import model.Usuario;

public class LoginController {

    // Usuario quemado para ejemplo
    private final Usuario usuarioValido = new Usuario("admin", "1234");

    public boolean iniciarSesion(String username, String password) {
        return username.equals(usuarioValido.getUsername()) &&
               password.equals(usuarioValido.getPassword());
    }
}
