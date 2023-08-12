package pe.idat.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
	public ResponseEntity<?> listar_GET()
	{
		Collection<Libros> libroDb = libroService.findAll();

		if (libroDb.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(libroDb, HttpStatus.OK);
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
			libroDb.setEstado(libro.getEstado());
			libroDb.setFpublicacion(libro.getFpublicacion());
			libroDb.setEditorial(libro.getEditorial());
			libroDb.setCantidad(libro.getCantidad());
			libroDb.setGenero(libro.getGenero());
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
