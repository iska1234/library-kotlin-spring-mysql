package pe.idat.vo;

import pe.idat.entity.Rol;
import pe.idat.entity.Usuario;

public class UserRoles {

	private Usuario user;
	private Rol rol;
	
	public UserRoles() {
		// TODO Auto-generated constructor stub
	}

	public UserRoles(Usuario user, Rol rol) {
		this.user = user;
		this.rol = rol;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Rol getRole() {
		return rol;
	}

	public void setRole(Rol rol) {
		this.rol = rol;
	}
	
	
	
}
