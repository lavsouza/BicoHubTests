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

    //TC005
    @Test
    void testeCadastroUsuarioComDataNascimentoVazia() {

        Usuario usuarioNovo = new Usuario(
                "João",
                "Silva",
                "joao.silva@gmail.com",
                "silvinha89",
                "368.532.600-74",
                null,
                "Recife"
        );

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.salvar(usuarioNovo)
        );

        verify(usuarioRepository, never()).salvar(any());
    }

    //TC006
    @Test
    void testeCadastroUsuarioComEmailVazio(){
        Usuario usuarioNovo = new Usuario(
                "João",
                "Silva",
                null,
                "silvinha89",
                "368.532.600-74",
                LocalDate.now().minusYears(18),
                "Recife"
        );

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.salvar(usuarioNovo)
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

    //TC008
    @Test
    void testeCadastroUsuarioComSenhaVazia() {
        Usuario usuarioNovo = new Usuario(
                "João",
                "Silva",
                "joao.silva@gmail.com",
                null,
                "368.532.600-74",
                LocalDate.now().minusYears(18),
                "Recife"
        );

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.salvar(usuarioNovo)
        );

        verify(usuarioRepository, never()).salvar(any());
    }

    //TC009
    @Test
    void testeCadastroUsuarioComCpfInvalido() {
        Usuario usuarioNovo = new Usuario(
                "João",
                "Silva",
                "joao.silva@gmail.com",
                "silvinha89",
                "1234",
                LocalDate.now().minusYears(18),
                "Recife"
        );

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.salvar(usuarioNovo)
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

        Usuario usuarioAlterado = new Usuario();
        usuarioAlterado.setCpf("11111112385");

        when(usuarioRepository.buscarPorCpf(cpfAntigo))
                .thenReturn(usuarioBanco);

        when(usuarioRepository.existePorCpf("11111112385"))
                .thenReturn(false);

        when(usuarioRepository.atualizar(usuarioBanco))
                .thenReturn(true);

        boolean resultado = usuarioService.atualizar(cpfAntigo, usuarioAlterado);

        Assertions.assertTrue(resultado);
        Assertions.assertEquals("11111112385", usuarioBanco.getCpf());

        verify(usuarioRepository, times(1)).atualizar(usuarioBanco);
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

        Usuario usuarioAlterado = new Usuario();
        usuarioAlterado.setCpf(cpfJaExistente);

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

        Usuario usuarioAlterado = new Usuario();
        usuarioAlterado.setCpf("");

        when(usuarioRepository.buscarPorCpf(cpfAntigo))
                .thenReturn(usuarioBanco);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.atualizar(cpfAntigo, usuarioAlterado)
        );

        verify(usuarioRepository, never()).atualizar(any());
    }

    @Test
    void testeAtualizarUsuarioCpfInvalido() {
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

        Usuario usuarioAlterado = new Usuario();
        usuarioAlterado.setCpf("123");

        when(usuarioRepository.buscarPorCpf(cpfAntigo))
                .thenReturn(usuarioBanco);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.atualizar(cpfAntigo, usuarioAlterado)
        );

        verify(usuarioRepository, never()).atualizar(any());
    }

    @Test
    void testeAtualizarUsuarioSobrenomeSucesso() {
        String cpfAntigo = "11111111185";

        Usuario usuarioBanco = new Usuario(
                "João",
                "Silva",
                "joao@gmail.com",
                "senha123",
                cpfAntigo,
                LocalDate.of(1990, 1, 1),
                "Recife"
        );

        Usuario usuarioAlterado = new Usuario();
        usuarioAlterado.setSobrenome("Teste da Silva");

        when(usuarioRepository.buscarPorCpf(cpfAntigo))
                .thenReturn(usuarioBanco);

        when(usuarioRepository.atualizar(usuarioBanco))
                .thenReturn(true);

        boolean resultado = usuarioService.atualizar(cpfAntigo, usuarioAlterado);

        Assertions.assertTrue(resultado);
        Assertions.assertEquals("Teste da Silva", usuarioBanco.getSobrenome());

        verify(usuarioRepository, times(1)).atualizar(usuarioBanco);
    }

    @Test
    void testeAtualizarUsuarioDataNascimentoSucesso() {
        String cpfAntigo = "11111111185";

        Usuario usuarioBanco = new Usuario(
                "João",
                "Silva",
                "joao@gmail.com",
                "senha123",
                cpfAntigo,
                LocalDate.of(1990, 1, 1),
                "Recife"
        );

        Usuario usuarioAlterado = new Usuario();
        usuarioAlterado.setDataNascimento(LocalDate.of(1986, 12, 10));

        when(usuarioRepository.buscarPorCpf(cpfAntigo))
                .thenReturn(usuarioBanco);

        when(usuarioRepository.atualizar(usuarioBanco))
                .thenReturn(true);

        boolean resultado = usuarioService.atualizar(cpfAntigo, usuarioAlterado);

        Assertions.assertTrue(resultado);
        Assertions.assertEquals(LocalDate.of(1986, 12, 10), usuarioBanco.getDataNascimento());

        verify(usuarioRepository, times(1)).atualizar(usuarioBanco);
    }

    @Test
    void testeAtualizarUsuarioSenhaVazia() {

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

        Usuario usuarioAlterado = new Usuario();
        usuarioAlterado.setSenha("");

        when(usuarioRepository.buscarPorCpf(cpfAntigo))
                .thenReturn(usuarioBanco);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                usuarioService.atualizar(cpfAntigo, usuarioAlterado)
        );

        verify(usuarioRepository, never()).atualizar(any());
    }

    //TC019
    @Test
    void testeEditarNomeUsuarioComSucesso() {

        Usuario usuarioTeste = new Usuario(
                "João",
                "Silva",
                "joao@gmail.com",
                "senha123",
                "12312312345",
                LocalDate.now().minusYears(18),
                "Recife"
        );

        //usuarioTeste.setNome("Marcos");
        String cpfUsuario = usuarioTeste.getCpf();
        Usuario usuarioNomeAlterado = new Usuario();
        usuarioNomeAlterado.setNome("Marcos");

        when(usuarioRepository.buscarPorCpf(cpfUsuario)).thenReturn(usuarioTeste);
        when(usuarioRepository.atualizar(usuarioTeste)).thenReturn(true);
        boolean resultadoTroca = usuarioService.atualizar(cpfUsuario, usuarioNomeAlterado);

        Assertions.assertTrue(resultadoTroca);
        Assertions.assertEquals("Marcos", usuarioTeste.getNome());

        verify(usuarioRepository, times(1)).atualizar(usuarioTeste);
    }

    //TC020
    @Test
    void testeEditarUsuarioNomeVazio(){
        Usuario usuarioTeste = new Usuario(
                "João",
                "Silva",
                "joao@gmail.com",
                "senha123",
                "12312312345",
                LocalDate.now().minusYears(18),
                "Recife"
        );

        String cpfUsuario = usuarioTeste.getCpf();
        Usuario usuarioNomeVazio = new Usuario();
        usuarioNomeVazio.setNome("");

        when(usuarioRepository.buscarPorCpf(cpfUsuario)).thenReturn(usuarioTeste);

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.atualizar(cpfUsuario, usuarioNomeVazio));

        verify(usuarioRepository, never()).atualizar(any());
    }

    //TC021
    @Test
    void testeEditarUsuarioSobrenomeVazio(){
        Usuario usuarioTeste = new Usuario(
                "João",
                "Silva",
                "joao@gmail.com",
                "senha123",
                "12312312345",
                LocalDate.now().minusYears(18),
                "Recife"
        );

        String cpfUsuario = usuarioTeste.getCpf();
        Usuario usuarioSobrenomeVazio = new Usuario();
        usuarioSobrenomeVazio.setSobrenome("");

        when(usuarioRepository.buscarPorCpf(cpfUsuario)).thenReturn(usuarioTeste);

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.atualizar(cpfUsuario, usuarioSobrenomeVazio));

        verify(usuarioRepository, never()).atualizar(any());
    }

    //TC024
    @Test
    void testeEditarUsuarioDataNascimentoInvalida(){
        Usuario usuarioTeste = new Usuario(
                "João",
                "Silva",
                "joao@gmail.com",
                "senha123",
                "12312312345",
                LocalDate.now().minusYears(18),
                "Recife"
        );

        String cpfUsuario = usuarioTeste.getCpf();
        Usuario usuarioDataInvalida = new Usuario();
        usuarioDataInvalida.setDataNascimento(LocalDate.now().plusYears(1));

        when(usuarioRepository.buscarPorCpf(cpfUsuario)).thenReturn(usuarioTeste);
        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.atualizar(cpfUsuario, usuarioDataInvalida));

        verify(usuarioRepository, never()).atualizar(any());
    }

    //TC025 - depreciado, não há possibilidade da data de nascimento ser editada como vazia
   /*@Test
    void testeEditarUsuarioDataNascimentoVazia(){
        Usuario usuarioTeste = new Usuario(
                "João",
                "Silva",
                "joao@gmail.com",
                "senha123",
                "12312312345",
                LocalDate.now().minusYears(18),
                "Recife"
        );

        String cpfUsuario = usuarioTeste.getCpf();
        Usuario usuarioDataNascimentoVazia = new Usuario();
        usuarioDataNascimentoVazia.setDataNascimento(null);

        when(usuarioRepository.buscarPorCpf(cpfUsuario)).thenReturn(usuarioTeste);
        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.atualizar(cpfUsuario, usuarioDataNascimentoVazia));

        verify(usuarioRepository, never()).atualizar(any());
    }*/

    //TC026
    @Test
    void testeEditarUsuarioEmailVazio() {
        Usuario usuarioTeste = new Usuario(
                "João",
                "Silva",
                "joao@gmail.com",
                "senha123",
                "12312312345",
                LocalDate.now().minusYears(18),
                "Recife"
        );

        String cpfUsuario = usuarioTeste.getCpf();
        Usuario usuarioEmailVazio = new Usuario();
        usuarioEmailVazio.setEmail("");

        when(usuarioRepository.buscarPorCpf(cpfUsuario)).thenReturn(usuarioTeste);
        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.atualizar(cpfUsuario, usuarioEmailVazio));

        verify(usuarioRepository, never()).atualizar(any());
    }

    //TC033
    @Test
    void testeCadastraUsuarioComoProfissional(){

    }

}
