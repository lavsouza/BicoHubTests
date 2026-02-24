package main.java.service;

import main.java.model.Usuario;
import main.java.respository.UsuarioRepository;
import main.java.model.Profissional;
import respository.ProfissionalRepository;
import service.ProfissionalService;

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

        if (cpfAtual == null || cpfAtual.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF atual não pode ser vazio");
        }

        Usuario usuarioBanco = usuarioRepository.buscarPorCpf(cpfAtual);

        if (usuarioBanco == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }


        // ===== CPF =====
        if (usuarioAtualizado.getCpf() != null &&
                !usuarioAtualizado.getCpf().equals(cpfAtual)) {

            if (!usuarioAtualizado.getCpf().matches("\\d{11}")) {
                throw new IllegalArgumentException("CPF inválido");
            }

            if (usuarioRepository.existePorCpf(usuarioAtualizado.getCpf())) {
                throw new IllegalArgumentException("CPF já cadastrado");
            }

            usuarioBanco.setCpf(usuarioAtualizado.getCpf());
        }

        // ===== Nome =====
        /*if (usuarioAtualizado.getNome() != null &&
                !usuarioAtualizado.getNome().trim().isEmpty()) {
            usuarioBanco.setNome(usuarioAtualizado.getNome());
        }*/
        if(usuarioAtualizado.getNome() != null){
            if(!usuarioAtualizado.getNome().trim().isEmpty()){
                usuarioBanco.setNome(usuarioAtualizado.getNome());
            } else{
                throw new IllegalArgumentException("Nome não pode ser vazio.");
            }
        }

        // ===== Sobrenome =====
        /*if (usuarioAtualizado.getSobrenome() != null &&
                !usuarioAtualizado.getSobrenome().trim().isEmpty()) {
            usuarioBanco.setSobrenome(usuarioAtualizado.getSobrenome());
        }*/
        if(usuarioAtualizado.getSobrenome() != null){
            if(!usuarioAtualizado.getSobrenome().trim().isEmpty()){
                usuarioBanco.setSobrenome(usuarioAtualizado.getSobrenome());
            } else{
                throw new IllegalArgumentException("Sobrenome não pode ser vazio.");
            }
        }

        // ===== Email =====
        /*if (usuarioAtualizado.getEmail() != null &&
                !usuarioAtualizado.getEmail().trim().isEmpty()) {
            usuarioBanco.setEmail(usuarioAtualizado.getEmail());
        }*/
        if (usuarioAtualizado.getEmail() != null){
            if(!usuarioAtualizado.getEmail().trim().isEmpty()){
                usuarioBanco.setEmail(usuarioAtualizado.getEmail());
            } else {
                throw new IllegalArgumentException("E-mail não pode estar vazio");
            }
        }

        // ===== Senha =====
        if (usuarioAtualizado.getSenha() != null) {

            if (usuarioAtualizado.getSenha().trim().isEmpty()) {
                throw new IllegalArgumentException("Senha não pode ser vazia");
            }

            if (usuarioAtualizado.getSenha().length() < 8) {
                throw new IllegalArgumentException("Senha deve ter no mínimo 8 caracteres");
            }

            usuarioBanco.setSenha(usuarioAtualizado.getSenha());
        }

        // ===== Data =====
        if (usuarioAtualizado.getDataNascimento() != null) {

            if (usuarioAtualizado.getDataNascimento().isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Data futura inválida");
            }

            if (usuarioAtualizado.getDataNascimento()
                    .plusYears(18).isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Usuário deve ser maior de 18 anos");
            }

            usuarioBanco.setDataNascimento(usuarioAtualizado.getDataNascimento());
        }

        // ===== Endereço =====
        if (usuarioAtualizado.getEndereco() != null &&
                !usuarioAtualizado.getEndereco().trim().isEmpty()) {
            usuarioBanco.setEndereco(usuarioAtualizado.getEndereco());
        }

        // ===== Foto =====
        if (usuarioAtualizado.getFotoPerfil() != null) {

            long tamanhoMaximo = 200L * 1024 * 1024;

            try {
                if (usuarioAtualizado.getFotoPerfil().length() > tamanhoMaximo) {
                    throw new IllegalArgumentException("Foto excede 200MB");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao validar foto", e);
            }

            usuarioBanco.setFotoPerfil(usuarioAtualizado.getFotoPerfil());
        }

        return usuarioRepository.atualizar(usuarioBanco);
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

    public boolean tornarProfissional(ProfissionalService profissionalService, Usuario usuarioNovoProfissional, String docId, String fotoComDocumento, int raioAtendimento){
        if(usuarioNovoProfissional==null || eNuloOuVazio(docId) || eNuloOuVazio(fotoComDocumento) || raioAtendimento<=0){
            throw new IllegalArgumentException("Não devem haver campos vazios.");
        }

        Profissional novoProfissional = new Profissional(usuarioNovoProfissional, docId, fotoComDocumento, 0, raioAtendimento);
        return profissionalService.salvarProfissional(novoProfissional);


    }

    private boolean eNuloOuVazio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }



}