package pe.idat.service;

import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.idat.entity.Libros;
import pe.idat.repository.LibrosRepository;

@Service
public class LibroServiceImpl implements LibroService
{
	@Autowired
	private LibrosRepository repository;

	@Override
	@Transactional
	public void insert(Libros libros) {		
		repository.save(libros);
	}

	@Override
	@Transactional
	public void update(Libros libros) {
		repository.save(libros);
	}

	@Override
	@Transactional
	public void delete(Integer librosId) {
		repository.deleteById(librosId);
	}

	@Override
	@Transactional(readOnly=true)
	public Libros findById(Integer librosId) {
		return repository.findById(librosId).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Libros> findAll() {
		return repository.findAll();
	}


}
