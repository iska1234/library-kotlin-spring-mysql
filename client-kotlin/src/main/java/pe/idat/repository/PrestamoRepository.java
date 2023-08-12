package pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.idat.entity.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer>{
    
}
