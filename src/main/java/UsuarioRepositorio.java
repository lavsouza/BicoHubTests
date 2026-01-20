package main.java;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {

	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	
	public Usuario inserir(Usuario u) {
		this.usuarios.add(u);
		return u;		
	}
	
	public Usuario remover(String cpf) {
		Usuario user = null;
		for (Usuario u: this.usuarios) {
			if (u.getCpf().equals(cpf)) {
				user = u;
				this.usuarios.remove(u);
				break;
			}
		}
		return user;		
	}

	public boolean buscarPorCpf(String cpf) {
		boolean found = false;
		for (Usuario u: this.usuarios) {
			if (u.getCpf().equals(cpf)) {
				found = true;
				break;
			}
		}
		return found;
	}
}
