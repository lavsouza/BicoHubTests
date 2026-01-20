package main.java;

import main.java.respositorie.UsuarioRepository;
import main.java.service.UsuarioService;

public class UsuarioNegocio {

	private UsuarioRepository rep;
	
	public UsuarioNegocio(UsuarioRepository repo) {
		this.rep = repo;
	}
	
	public UsuarioService adicionar(UsuarioService u) {

		return null;		
	}
	
    public UsuarioService remover(String cpf) {
        return null;
    }
    
    public UsuarioService atualizar(UsuarioService u) {
        return null;
    }
    
    public UsuarioService buscar(String cpf) {
        return null;
    }
}