package main.java.service;

import main.java.respositorie.UsuarioRepository;

public class UsuarioService {

    protected UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
}
