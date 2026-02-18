package main.java.service;

import main.java.model.Usuario;
import main.java.respository.UsuarioRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class UsuarioService {

    protected UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean salvar(Usuario usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }

        if (eNuloOuVazio(usuario.getNome())
                || eNuloOuVazio(usuario.getEmail())
                || eNuloOuVazio(usuario.getSenha())
                || eNuloOuVazio(usuario.getCpf())
                || eNuloOuVazio(usuario.getEndereco())
                || eNuloOuVazio(usuario.getSobrenome())
        ) {
            throw new IllegalArgumentException("Nenhum campo pode ser nulo ou vazio");
        }

        if (usuario.getSenha().length() < 8) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres");
        }

        if (!usuario.getCpf().matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve conter exatamente 11 dígitos numéricos");
        }

        if (usuario.getCpf().isEmpty()) {
            throw new IllegalArgumentException("CPF não deve estar em branco");
        }

        if (usuarioRepository.existePorEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado no sistema");
        }

        if (usuarioRepository.existePorCpf(usuario.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado no sistema");
        }

        if (usuario.getDataNascimento() == null) {
            throw new IllegalArgumentException("Data de nascimento não pode ser nula");
        }

        if (usuario.getDataNascimento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento não pode ser futura");
        }

        if (usuario.getDataNascimento().plusYears(18).isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Usuário deve ser maior de 18 anos");
        }

        if (usuario.getFotoPerfil() != null) {

            long tamanhoMaximo = 200L * 1024 * 1024;

            try {
                long tamanhoFoto = usuario.getFotoPerfil().length();

                if (tamanhoFoto > tamanhoMaximo) {
                    throw new IllegalArgumentException("Foto de perfil excede 200MB");
                }

            } catch (SQLException e) {
                throw new RuntimeException("Erro ao acessar dados da foto", e);
            }
        }


        usuarioRepository.salvar(usuario);
        return true;
    }

    public boolean atualizar(String cpfAtual, Usuario usuarioAtualizado) {

        Usuario usuarioBanco = usuarioRepository.buscarPorCpf(cpfAtual);

        if (usuarioBanco == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        if (usuarioAtualizado.getCpf() == null || usuarioAtualizado.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser vazio");
        }

        if (!usuarioAtualizado.getCpf().matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido");
        }

        if (!cpfAtual.equals(usuarioAtualizado.getCpf())) {
            if (usuarioRepository.existePorCpf(usuarioAtualizado.getCpf())) {
                throw new IllegalArgumentException("CPF já cadastrado");
            }
        }

        return usuarioRepository.atualizar(usuarioAtualizado);
    }

    public void deletarPorCpf(String cpf) {

        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }

        if (!usuarioRepository.existePorCpf(cpf)) {
            throw new IllegalStateException("Usuário não encontrado para o CPF informado");
        }

        usuarioRepository.deletarPorCpf(cpf);
    }

    private boolean eNuloOuVazio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}