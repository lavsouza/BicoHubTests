package main.java.service;

import main.java.model.Usuario;
import main.java.respositorie.UsuarioRepository;

public class UsuarioService {

    protected UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void salvar(Usuario usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }

        if (eNuloOuVazio(usuario.getNome())
                || eNuloOuVazio(usuario.getEmail())
                || eNuloOuVazio(usuario.getSenha())
                || eNuloOuVazio(usuario.getCpf())
                || eNuloOuVazio(usuario.getEndereco())) {
            throw new IllegalArgumentException("Nenhum campo pode ser nulo ou vazio");
        }

        if (usuario.getSenha().length() < 8) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres");
        }

        if (!usuario.getCpf().matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve conter exatamente 11 dígitos numéricos");
        }

        if (usuarioRepository.existePorEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado no sistema");
        }

        if (usuarioRepository.existePorCpf(usuario.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado no sistema");
        }

        usuarioRepository.salvar(usuario);
    }

    private boolean eNuloOuVazio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}