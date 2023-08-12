package pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.idat.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    
}
