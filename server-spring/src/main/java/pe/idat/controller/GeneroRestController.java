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

import pe.idat.entity.Genero;
import pe.idat.service.GeneroService;





@RestController
@RequestMapping("/genero")
public class GeneroRestController {

	@Autowired
	private GeneroService generoService;

	public GeneroRestController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/listar")
	public ResponseEntity<?> listar_GET() {
		Collection<Genero> generoDb = generoService.findAll();

		if (generoDb.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(generoDb, HttpStatus.OK);
	}

	@PostMapping("/registrar")
	public ResponseEntity<?> registrar_GET(@RequestBody Genero genero) {
		generoService.insert(genero);
		return new ResponseEntity<>("¡Libro registrado!", HttpStatus.CREATED);
	}

	@PutMapping("/editar/{generoId}")
	public ResponseEntity<?> editar_PUT(@RequestBody Genero genero, @PathVariable Integer generoId) {
		Genero generoDb = generoService.findById(generoId);

		if (generoDb != null) {
			generoDb.setDescripcion(genero.getDescripcion());
			generoService.update(generoDb);
			return new ResponseEntity<>("¡Datos del libro actualizado!", HttpStatus.OK);
		}

		return new ResponseEntity<>("¡Libro no encontrado!", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/borrar/{generoId}")
	public ResponseEntity<?> borrar_DELETE(@PathVariable Integer generoId) {
		Genero generoDb = generoService.findById(generoId);

		if (generoDb != null) {
			generoService.delete(generoId);
			return new ResponseEntity<>("¡Libro eliminado de la BD!", HttpStatus.OK);
		}

		return new ResponseEntity<>("¡Libro no encontrado!", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/buscar/{generoId}")
	public ResponseEntity<?> buscar_GET(@PathVariable Integer generoId) {
		Genero generoDb = generoService.findById(generoId);

		if (generoDb != null) {
			return new ResponseEntity<>(generoDb, HttpStatus.FOUND);
		}

		return new ResponseEntity<>("¡Libro no encontrado!", HttpStatus.NOT_FOUND);
	}
}
