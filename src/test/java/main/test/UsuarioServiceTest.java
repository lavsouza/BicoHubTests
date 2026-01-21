package test.java.main.test;

import main.java.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import main.java.model.Usuario;
import main.java.respositorie.UsuarioRepository;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;
	
	@Test
	public void testeCadastrarUsuarioDadosValidos() {
        Usuario u = new Usuario("Vilmar", "teste@gmail.com", "12345678", "12345678910", "EnderecoTeste");

        boolean uCad = usuarioService.salvar(u);

        Assertions.assertNotNull(uCad);
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

}
