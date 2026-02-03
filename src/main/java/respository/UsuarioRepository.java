package main.java.respository;

import main.java.model.Usuario;

public interface UsuarioRepository {

    boolean salvar(Usuario usuario);
    void deletarPorCpf(String cpf);
    boolean existePorEmail(String email);
    boolean existePorCpf(String cpf);
    Usuario buscarPorCpf(String cpf);
}