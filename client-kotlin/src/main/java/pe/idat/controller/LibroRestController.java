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

import pe.idat.entity.Libros;
import pe.idat.service.LibroService;

@RestController
@RequestMapping("/libro")
public class LibroRestController {
	
	@Autowired
	private LibroService libroService;

	public LibroRestController() {
	}

	@GetMapping("/listar")
	public ResponseEntity<?> listar_GET() {
		Collection<Libros> librosDb = libroService.findAll();

		if (librosDb.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(librosDb, HttpStatus.OK);
	}

	@PostMapping("/registrar")
	public ResponseEntity<?> registrar_GET(@RequestBody Libros libro) {
		libroService.insert(libro);
		return new ResponseEntity<>("¡Libro registrado!", HttpStatus.CREATED);
	}

	@PutMapping("/editar/{libroId}")
	public ResponseEntity<?> editar_PUT(@RequestBody Libros libro, @PathVariable Integer libroId) {
		Libros libroDb = libroService.findById(libroId);

		if (libroDb != null) {
			libroDb.setTitulo(libro.getTitulo());
			libroDb.setAutor(libro.getAutor());
			libroDb.setFpublicacion(libro.getFpublicacion());
			libroDb.setEditorial(libro.getEditorial());
			libroDb.setGenero(libro.getGenero());
			libroDb.setNpaginas(libro.getNpaginas());

			libroService.update(libroDb);
			return new ResponseEntity<>("¡Datos del libro actualizado!", HttpStatus.OK);
		}

		return new ResponseEntity<>("¡Libro no encontrado!", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/borrar/{libroId}")
	public ResponseEntity<?> borrar_DELETE(@PathVariable Integer libroId) {
		Libros libroDb = libroService.findById(libroId);

		if (libroDb != null) {
			libroService.delete(libroId);
			return new ResponseEntity<>("¡Libro eliminado de la BD!", HttpStatus.OK);
		}

		return new ResponseEntity<>("¡Libro no encontrado!", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/buscar/{libroId}")
	public ResponseEntity<?> buscar_GET(@PathVariable Integer libroId) {
		Libros libroDb = libroService.findById(libroId);

		if (libroDb != null) {
			return new ResponseEntity<>(libroDb, HttpStatus.FOUND);
		}

		return new ResponseEntity<>("¡Libro no encontrado!", HttpStatus.NOT_FOUND);
	}
}
