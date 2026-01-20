package main.java;

import main.java.service.UsuarioService;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {

	private List<UsuarioService> usuarios = new ArrayList<UsuarioService>();
	
	
	public UsuarioService inserir(UsuarioService u) {
		this.usuarios.add(u);
		return u;		
	}
	
	public UsuarioService remover(String cpf) {
		UsuarioService user = null;
		for (UsuarioService u: this.usuarios) {
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
		for (UsuarioService u: this.usuarios) {
			if (u.getCpf().equals(cpf)) {
				found = true;
				break;
			}
		}
		return found;
	}
}
