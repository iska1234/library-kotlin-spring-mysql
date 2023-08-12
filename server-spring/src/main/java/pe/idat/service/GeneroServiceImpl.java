package pe.idat.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.idat.entity.Genero;

import pe.idat.repository.GeneroRepository;


@Service
public class GeneroServiceImpl implements GeneroService {

	@Autowired
	private GeneroRepository repository;

	@Override
	@Transactional
	public void insert(Genero genero) {		
		repository.save(genero);
	}

	@Override
	@Transactional
	public void update(Genero genero) {
		repository.save(genero);
	}

	@Override
	@Transactional
	public void delete(Integer generoId) {
		repository.deleteById(generoId);
	}

	@Override
	@Transactional(readOnly=true)
	public Genero findById(Integer generoId) {
		return repository.findById(generoId).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Genero> findAll() {
		return repository.findAll();
	}


	
}
