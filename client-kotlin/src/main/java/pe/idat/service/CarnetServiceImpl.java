package pe.idat.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.idat.entity.Carnet;
import pe.idat.repository.CarnetRepository;

@Service
public class CarnetServiceImpl implements CarnetService{

    @Autowired
    private CarnetRepository repository;

    @Override
    @Transactional
    public void insert(Carnet carnet) {
        repository.save(carnet);
        
    }

    @Override
    @Transactional
    public void update(Carnet carnet) {
        repository.save(carnet);
        
    }

    @Override
    @Transactional
    public void delete(Integer carnetId) {
        repository.deleteById(carnetId);
        
    }

    @Override
    @Transactional(readOnly = true)
    public Carnet findById(Integer carnetId) {
        return repository.findById(carnetId).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Carnet> findAll() {
        return repository.findAll();
    }
    
}
