package pe.idat.service;

import java.util.Collection;

import pe.idat.entity.Cliente;

public interface ClienteService {

    public abstract void insert(Cliente cliente);
    public abstract void update(Cliente cliente);
    public abstract void delete(Integer clienteId);
    public abstract Cliente findById(Integer clienteId);
    public abstract Collection<Cliente> findAll();
}
