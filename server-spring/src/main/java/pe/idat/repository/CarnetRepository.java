package pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.idat.entity.Carnet;

public interface CarnetRepository extends JpaRepository<Carnet, Integer>{
    
}
