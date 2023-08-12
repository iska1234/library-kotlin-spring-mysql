package pe.idat.service;

import java.util.Collection;


import pe.idat.entity.Usuario;



public interface UsuarioService {
	
	public abstract void insert(Usuario usuario);
	public abstract void update(Usuario usuario);
	public abstract void delete(Integer usuarioId);
	public abstract Usuario findById(Integer usuarioId);
	public abstract Collection<Usuario> findAll();
	
	public abstract Collection<Object[]> findAll_withroles();
	//Buscar por usuario
	
	

}
