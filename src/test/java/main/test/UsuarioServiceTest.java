package test.java.main.test;

import main.java.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import main.java.model.Usuario;
import main.java.respository.UsuarioRepository;

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
                "Vilmar",
                "teste@gmail.com",
                "12345678",
                "12345678910",
                "EnderecoTeste"
        );

        when(usuarioRepository.existePorCpf(u.getCpf())).thenReturn(false);
        when(usuarioRepository.existePorEmail(u.getEmail())).thenReturn(false);
        when(usuarioRepository.salvar(u)).thenReturn(true);

        boolean resultado = usuarioService.salvar(u);

        Assertions.assertTrue(resultado);

        verify(usuarioRepository, times(1)).salvar(u);
    }


    @Test
    void deveExcluirUsuarioQuandoCpfExiste() {

        String cpf = "12345678901";

        when(usuarioRepository.existePorCpf(cpf)).thenReturn(true);

        Assertions.assertDoesNotThrow(() ->
                usuarioService.deletarPorCpf(cpf)
        );

        verify(usuarioRepository, times(1)).deletarPorCpf(cpf);
    }

    @Test
    void deveDetectarUsuarioComNomeNulo() {
        Usuario u = new Usuario(null, "abc12345@gmail.com", "12345678", "12345678901", "enderecoTeste");

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.salvar(u));

        verify(usuarioRepository, never()).salvar(any(Usuario.class));
    }

    @Test
    void deveImpedirCadastroDeUsuarioComEmailExistente() {
        Usuario u = new Usuario(
                "Joao",
                "teste@gmail.com",
                "12345678",
                "12345678901",
                "EnderecoTeste"
        );

        when(usuarioRepository.existePorEmail(u.getEmail())).thenReturn(true);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> usuarioService.salvar(u));

        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void deveImpedirCadastroDeUsuarioComCpfExistente() {
        Usuario u = new Usuario(
                "Joao",
                "teste@gmail.com",
                "12345678",
                "12345678901",
                "EnderecoTeste"
        );

        when(usuarioRepository.existePorCpf(u.getCpf())).thenReturn(true);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> usuarioService.salvar(u));

        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void deveImpedirCadastroDeUsuarioComCpfInvalido() {
        Usuario a = new Usuario("Joao", "teste@gmail.com", "12345678", "12", "EnderecoTeste");

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.salvar(a));

        verify(usuarioRepository, never()).salvar(any(Usuario.class));
    }

    @Test
    void deveImpedirCadastroComSenhaFraca() {
        Usuario a = new Usuario("Joao", "teste@gmail.com", "123", "12345678901", "EnderecoTeste");

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.salvar(a));

        verify(usuarioRepository, never()).salvar(any(Usuario.class));
    }

    @Test
    void deveImpedirCadastroComEnderecoVazio() {
        Usuario a = new Usuario("Joao", "teste@gmail.com", "12345678", "12345678901", null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.salvar(a));

        verify(usuarioRepository, never()).salvar(any(Usuario.class));
    }


}
