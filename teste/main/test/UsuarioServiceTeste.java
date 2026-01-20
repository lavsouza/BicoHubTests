package main.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.Usuario;
import main.java.UsuarioRepositorio;

public class UsuarioTeste {

	private UsuarioRepositorio rep;
	
	@BeforeEach
	public void initTestes() {
		this.rep = mock(UsuarioRepositorio.class);
	}
	
	@Test
	public void cadastrarUsuarioJunitTest() {
		
		Usuario u = new Usuario("Vilmar", 25, "11111111111");
		UsuarioNegocio neg = new UsuarioNegocio(new UsuarioRepositorio());			
		
		Usuario uCad = neg.adicionar(u);		
		
		Assertions.assertNotNull(uCad);
		
	}
	
	@Test
	public void cadastrarUsuarioTest() {
		
		Usuario u = new Usuario("Vilmar", 25, "11111111111");
		UsuarioNegocio neg = new UsuarioNegocio(rep);
			
		when(rep.buscarPorCpf(u.getCpf())).thenReturn(false);
		when(rep.inserir(u)).thenReturn(u);
		
		Usuario uCad = neg.adicionar(u);
		
		verify(rep, times(0)).buscarPorCpf(u.getCpf());
		
		Assertions.assertNotNull(uCad);
		
	}
	
	@Test
	public void cadastrarUsuarioIdadeInvalidaTest() {
		
		Usuario u = new Usuario("Vilmar", 10, "11111111111");
		UsuarioNegocio neg = new UsuarioNegocio(rep);
			
		when(rep.buscarPorCpf(u.getCpf())).thenReturn(false);
		when(rep.inserir(u)).thenReturn(u);
		
		Usuario uCad = neg.adicionar(u);
		
		verify(rep, times(1)).buscarPorCpf(u.getCpf());
		verify(rep, times(0)).inserir(u);
		
		Assertions.assertNull(uCad);
		
	}
	
	@Test
	public void cadastrarUsuarioNomeInvalidaTest() {
		
		Usuario u = new Usuario("Vilmartttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt", 18, "11111111111");
		UsuarioNegocio neg = new UsuarioNegocio(rep);
			
		when(rep.buscarPorCpf(u.getCpf())).thenReturn(false);
		when(rep.inserir(u)).thenReturn(u);
		
		Usuario uCad = neg.adicionar(u);
		
		verify(rep, times(0)).inserir(u);
		
		Assertions.assertNull(uCad);
		
	}
	
	@Test
	public void cadastrarUsuarioCPFJaCadastradoTest() {
		
		Usuario u = new Usuario("Vilmar", 18, "11111111111");
		UsuarioNegocio neg = new UsuarioNegocio(rep);
			
		when(rep.buscarPorCpf("11111111111")).thenReturn(true);	
		when(rep.inserir(u)).thenReturn(u);
		
		Usuario uCad = neg.adicionar(u);
		
		verify(rep, times(0)).inserir(u);
		
		Assertions.assertNull(uCad);
		
	}
}
