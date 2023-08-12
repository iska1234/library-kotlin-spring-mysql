package pe.idat.service;

import java.util.Collection;

import pe.idat.entity.Libros;

public interface LibroService 
{
	//se define los servicios para el controlador
	public abstract void insert(Libros libros);
	public abstract void update(Libros libros);
	public abstract void delete(Integer libroId);
	public abstract Libros findById(Integer libroId);
	public abstract Collection<Libros> findAll();

	public  abstract Collection<Object[]> findAll_withPrestamos();
}
