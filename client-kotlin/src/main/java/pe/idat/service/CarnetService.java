package pe.idat.service;

import java.util.Collection;

import pe.idat.entity.Carnet;

public interface CarnetService {

    public abstract void insert(Carnet carnet);
    public abstract void update(Carnet carnet);
    public abstract void delete(Integer carnetId);
    public abstract Carnet findById(Integer carnetId);
    public abstract Collection<Carnet> findAll();
    
}
