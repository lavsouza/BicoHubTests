package main.java.respositorie;

import main.java.model.Usuario;

public interface UsuarioRepository {

    boolean salvar(Usuario usuario);
    void deletarPorCpf(String cpf);
    boolean existePorEmail(String email);
    boolean existePorCpf(String cpf);
    Usuario buscarPorCpf(String cpf); // Opcional, dependendo da sua lógica

}