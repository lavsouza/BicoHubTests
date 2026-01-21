package main.java.respositorie;

import main.java.model.Usuario.java;
import main.java.service.UsuarioService;

import java.util.ArrayList;
import java.util.List;

public interface UsuarioRepository {

    void salvar(Usuario usuario);
    void deletarPorCpf(String cpf);
    boolean existePorEmail(String email);
    boolean existePorCpf(String cpf);
    Usuario buscarPorCpf(String cpf); // Opcional, dependendo da sua lógica

}