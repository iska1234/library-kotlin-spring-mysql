package pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import pe.idat.entity.Libros;

import java.util.Collection;

public interface LibrosRepository extends JpaRepository<Libros,Integer> {

    @Query(value="select lib.libro_id, lib.titulo as 'titulo', prest.prestamo_id from libro_prestamo lb\n" +
            "inner join libros lib on lb.libro_id=lib.libro_id\n" +
            "inner join prestamo prest on lb.prestamo_id=prest.prestamo_id order by lib.libro_id;",nativeQuery=true)
    public abstract Collection<Object[]> findAll_withPrestamos();

}
