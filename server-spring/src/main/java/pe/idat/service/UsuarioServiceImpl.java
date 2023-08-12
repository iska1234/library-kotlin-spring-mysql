package pe.idat.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.idat.entity.Usuario;
import pe.idat.repository.UsuarioRepository;



@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	 @Autowired
	    private UsuarioRepository repository;

		
	    @Override
	    @Transactional
	    public void insert(Usuario usuario) {
	        repository.save(usuario);
	    }

	    @Override
	    @Transactional
	    public void update(Usuario usuario) {
	        repository.save(usuario);
	    }

	    @Override
	    @Transactional
	    public void delete(Integer usuarioId) {
	        repository.deleteById(usuarioId);
	    }

	    @Override
	    @Transactional
	    public Usuario findById(Integer usuarioId) {
	        return repository.findById(usuarioId).orElse(null);
	    }

	    @Override
	    @Transactional
	    public Collection<Usuario> findAll() {
	        return repository.findAll();
	    }
	    
	    @Override
		public Collection<Object[]> findAll_withroles() {
			return repository.findAll_withroles();
		}


}
