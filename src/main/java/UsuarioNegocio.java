package main.java;

public class UsuarioNegocio {

	private UsuarioRepositorio rep;
	
	public UsuarioNegocio(UsuarioRepositorio repo) {
		this.rep = repo;
	}
	
	public Usuario adicionar(Usuario u) {

		return null;		
	}
	
    public Usuario remover(String cpf) {
        return null;
    }
    
    public Usuario atualizar(Usuario u) {
        return null;
    }
    
    public Usuario buscar(String cpf) {
        return null;
    }
}