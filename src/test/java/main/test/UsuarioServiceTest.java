package test.java.main.test;

import main.java.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.*;


import main.java.model.Usuario;
import main.java.respository.UsuarioRepository;

import java.sql.Blob;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void testeCadastrarUsuarioDadosValidos() {
        Usuario u = new Usuario(
                "João",
                "Silva",
                "joao.silva@gmail.com",
                "silvinha1989",
                "11111111185",
                LocalDate.parse("1989-11-09"),
                "IFPE Recife"
        );

        when(usuarioRepository.existePorEmail(u.getEmail())).thenReturn(false);
        when(usuarioRepository.existePorCpf(u.getCpf())).thenReturn(false);
        when(usuarioRepository.salvar(u)).thenReturn(true);

        boolean resultado = usuarioService.salvar(u);

        Assertions.assertTrue(resultado);

        verify(usuarioRepository, times(1)).salvar(u);
    }

    @Test
    void testeCadastrarUsuarioCpfVazio() {
        Usuario u = new Usuario(
                "João",
                "Silva",
                "joao.silva@gmail.com",
                "silvinha1989",
                "",
                LocalDate.parse("1989-11-09"),
                "IFPE Recife"
        );

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.salvar(u)
        );

        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void testeCadastrarUsuarioNomeVazio() {
        Usuario u = new Usuario(
                "",
                "Silva",
                "joao.silva@gmail.com",
                "silvinha1989",
                "11111111185",
                LocalDate.parse("1989-11-09"),
                "IFPE Recife"
        );

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.salvar(u)
        );

        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void testeCadastrarUsuarioSobrenomeVazio() {
        Usuario u = new Usuario(
                "João",
                "",
                "joao.silva@gmail.com",
                "silvinha1989",
                "11111111185",
                LocalDate.parse("1989-11-09"),
                "IFPE Recife"
        );

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.salvar(u)
        );

        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void testeCadastrarUsuarioEmailJaCadastrado() {
        Usuario u = new Usuario(
                "João",
                "Silva",
                "emailjaexistente@gmail.com",
                "silvinha1989",
                "11111111185",
                LocalDate.parse("1989-11-09"),
                "IFPE Recife"
        );

        when(usuarioRepository.existePorEmail(u.getEmail())).thenReturn(true);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.salvar(u)
        );

        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void testeCadastrarUsuarioCPFJaCadastrado() {
        Usuario u = new Usuario(
                "João",
                "Silva",
                "emailjaexistente@gmail.com",
                "silvinha1989",
                "11111111185",
                LocalDate.parse("1989-11-09"),
                "IFPE Recife"
        );

        when(usuarioRepository.existePorCpf(u.getCpf())).thenReturn(true);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.salvar(u)
        );

        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void testeCadastrarUsuarioDataNascimentoMaiorQueAtual() {
        Usuario u = new Usuario(
                "João",
                "Silva",
                "joao.silva@gmail.com",
                "silvinha1989",
                "11111111185",
                LocalDate.parse("3050-01-01"),
                "IFPE Recife"
        );

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.salvar(u)
        );

        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void testeCadastrarUsuarioMenorDeIdade() {
        Usuario u = new Usuario(
                "João",
                "Silva",
                "joao.silva@gmail.com",
                "silvinha1989",
                "11111111185",
                LocalDate.now().minusYears(17),
                "IFPE Recife"
        );

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.salvar(u)
        );

        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void testeCadastrarUsuarioSenhaForaPadrao() {
        Usuario u = new Usuario(
                "João",
                "Silva",
                "joao.silva@gmail.com",
                "silva",
                "11111111185",
                LocalDate.parse("1989-01-01"),
                "IFPE Recife"
        );

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.salvar(u)
        );

        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void testeCadastrarUsuarioFotoPerfilGrande() throws Exception {

        Usuario u = new Usuario(
                "João",
                "Silva",
                "joao.silva@gmail.com",
                "senhaSegura123",
                "11111111185",
                LocalDate.now().minusYears(25),
                "IFPE Recife"
        );

        Blob blobMock = mock(Blob.class);

        // Simulando 201MB
        when(blobMock.length()).thenReturn(201L * 1024 * 1024);

        u.setFotoPerfil(blobMock);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.salvar(u)
        );

        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void testeAtualizarUsuarioCpfSucesso() {

        String cpfAntigo = "11111111185";

        Usuario usuarioBanco = new Usuario(
                "João",
                "Silva",
                "joao@gmail.com",
                "senha123",
                cpfAntigo,
                LocalDate.now().minusYears(30),
                "Recife"
        );

        Usuario usuarioAlterado = new Usuario(
                "João",
                "Silva",
                "joao@gmail.com",
                "senha123",
                "11111112385",
                LocalDate.now().minusYears(30),
                "Recife"
        );

        when(usuarioRepository.buscarPorCpf(cpfAntigo))
                .thenReturn(usuarioBanco);
        when(usuarioRepository.existePorCpf("11111112385"))
                .thenReturn(false);
        when(usuarioRepository.atualizar(usuarioAlterado))
                .thenReturn(true);

        boolean resultado = usuarioService.atualizar(cpfAntigo, usuarioAlterado);
        Assertions.assertTrue(resultado);

        verify(usuarioRepository, times(1)).atualizar(usuarioAlterado);
    }

    @Test
    void testeAtualizarUsuarioCpfJaExistente() {

        String cpfAntigo = "11111111185";
        String cpfJaExistente = "99999999999";

        Usuario usuarioBanco = new Usuario(
                "João",
                "Silva",
                "joao@gmail.com",
                "senha123",
                cpfAntigo,
                LocalDate.now().minusYears(30),
                "Recife"
        );

        Usuario usuarioAlterado = new Usuario(
                "João",
                "Silva",
                "joao@gmail.com",
                "senha123",
                cpfJaExistente,
                LocalDate.now().minusYears(30),
                "Recife"
        );

        when(usuarioRepository.buscarPorCpf(cpfAntigo))
                .thenReturn(usuarioBanco);

        when(usuarioRepository.existePorCpf(cpfJaExistente))
                .thenReturn(true);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.atualizar(cpfAntigo, usuarioAlterado)
        );

        verify(usuarioRepository, never()).atualizar(any());
    }

    @Test
    void testeAtualizarUsuarioCpfVazio() {

        String cpfAntigo = "11111111185";

        Usuario usuarioBanco = new Usuario(
                "João",
                "Silva",
                "joao@gmail.com",
                "senha123",
                cpfAntigo,
                LocalDate.now().minusYears(30),
                "Recife"
        );

        Usuario usuarioAlterado = new Usuario(
                "João",
                "Silva",
                "joao@gmail.com",
                "senha123",
                "",
                LocalDate.now().minusYears(30),
                "Recife"
        );

        when(usuarioRepository.buscarPorCpf(cpfAntigo))
                .thenReturn(usuarioBanco);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.atualizar(cpfAntigo, usuarioAlterado)
        );

        verify(usuarioRepository, never()).atualizar(any());
    }

}
