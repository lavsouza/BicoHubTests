package main.java.service;

import main.java.model.Usuario;
import main.java.respositorie.UsuarioRepository;

public class UsuarioService {

    protected UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioService() {
        Usuario usuarioPadrao = new Usuario(
                "Usuario Padrao",
                "user@sistema.com",
                "12345678",
                "00000000000",
                "Endereço padrão"
        );

        this.salvar(usuarioPadrao);
    }

    public void salvar(Usuario usuario) {
        this.usuarioRepository.salvar(usuario);
    }

}
