package pe.idat.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.idat.entity.Usuario;
import pe.idat.service.UsuarioService;



@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {

	
	@Autowired
    private UsuarioService usuarioService;

    public UsuarioRestController() {
		// TODO Auto-generated constructor stub
	}

    @GetMapping("/listar")
    public ResponseEntity<?> listar_GET() {
        Collection<Usuario> usuarioDb = usuarioService.findAll();

        if (usuarioDb.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(usuarioDb, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar_GET(@RequestBody Usuario usuario) {
    	usuarioService.insert(usuario);
        return new ResponseEntity<>("¡Prestamo registrado!", HttpStatus.CREATED);
    }

    @PutMapping("/editar/{PrestamoId}")
    public ResponseEntity<?> editar_PUT(@RequestBody Usuario usuario, @PathVariable Integer usuarioId) {
    	Usuario usuarioDb = usuarioService.findById(usuarioId);

        if (usuarioDb != null) {
        	usuarioDb.setNomusu(usuario.getNomusu());
        	usuarioDb.setClausu(usuario.getClausu());
        	usuarioDb.setEstusu(usuario.getEstusu());
        	
        	usuarioService.update(usuarioDb);
            return new ResponseEntity<>("¡Datos del Prestamo actualizado!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Prestamo no encontrado!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrar/{usuarioId}")
    public ResponseEntity<?> borrar_DELETE(@PathVariable Integer usuarioId) {
    	Usuario prestamoDb = usuarioService.findById(usuarioId);

        if (prestamoDb != null) {
        	usuarioService.delete(usuarioId);
            return new ResponseEntity<>("¡Prestamo eliminado de la BD!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Prestamo no encontrado!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/{usuarioId}")
    public ResponseEntity<?> buscar_GET(@PathVariable Integer usuarioId) {
    	Usuario prestamoDb = usuarioService.findById(usuarioId);

        if (prestamoDb != null) {
            return new ResponseEntity<>(usuarioId, HttpStatus.FOUND);
        }

        return new ResponseEntity<>("¡Prestamo no encontrado!", HttpStatus.NOT_FOUND);
    }
}
