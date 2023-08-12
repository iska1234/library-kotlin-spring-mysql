package pe.idat.service;

import java.util.Collection;

import pe.idat.entity.Rol;


public interface RolService {
	public abstract void insert(Rol rol);
	public abstract void update(Rol rol);
	public abstract void delete(Integer rolId);
	public abstract Rol findById(Integer rolId);
	public abstract Collection<Rol> findAll();

}
