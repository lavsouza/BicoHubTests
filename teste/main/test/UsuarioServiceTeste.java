package main.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.model.Usuario;
import main.java.respositorie.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTeste {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
	public void initTestes() {
		this.rep = mock(UsuarioRepository.class);
	}
	
	@Test
	public void cadastrarUsuario() {

        Usuario u = new Usuario("Vilmar", "teste@gmail.com", "12345678", "12345678910", "EnderecoTeste");
        UsuarioService usuarioService = new UsuarioService(new UsuarioRepository());

        Usuario uCad = usuarioService.salvar(u);

        Assertions.assertNotNull(uCad);
	}
}
