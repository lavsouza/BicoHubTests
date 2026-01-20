package main.java;

import main.java.service.UsuarioService;

public class UsuarioNegocio {

	private UsuarioRepositorio rep;
	
	public UsuarioNegocio(UsuarioRepositorio repo) {
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