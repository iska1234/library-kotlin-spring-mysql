package pe.idat.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.idat.entity.Prestamo;
import pe.idat.repository.PrestamoRepository;

@Service
public class PrestamoServiceImpl implements PrestamoService{

    @Autowired
    private PrestamoRepository repository;

    @Override
    @Transactional
    public void insert(Prestamo prestamo) {
        repository.save(prestamo);
    }

    @Override
    @Transactional
    public void update(Prestamo prestamo) {
        repository.save(prestamo);
    }

    @Override
    @Transactional
    public void delete(Integer prestamoId) {
        repository.deleteById(prestamoId);
    }

    @Override
    @Transactional
    public Prestamo findById(Integer prestamoId) {
        return repository.findById(prestamoId).orElse(null);
    }

    @Override
    @Transactional
    public Collection<Prestamo> findAll() {
        return repository.findAll();
    }
    
}
