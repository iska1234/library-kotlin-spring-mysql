package pe.idat.service;

import java.util.Collection;

import pe.idat.entity.Prestamo;

public interface PrestamoService {

    public abstract void insert(Prestamo prestamo);
    public abstract void update(Prestamo prestamo);
    public abstract void delete(Integer prestamoId);
    public abstract Prestamo findById(Integer prestamoId);
    public abstract Collection<Prestamo> findAll();
    
}
