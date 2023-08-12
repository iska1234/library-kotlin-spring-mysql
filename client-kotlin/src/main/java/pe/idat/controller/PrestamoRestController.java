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

import pe.idat.entity.Prestamo;
import pe.idat.service.PrestamoService;

@RestController
@RequestMapping("/prestamo")
public class PrestamoRestController {

    @Autowired
    private PrestamoService prestamoService;

    public PrestamoRestController() {
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar_GET() {
        Collection<Prestamo> prestamoDb = prestamoService.findAll();

        if (prestamoDb.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(prestamoDb, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar_GET(@RequestBody Prestamo prestamo) {
        prestamoService.insert(prestamo);
        return new ResponseEntity<>("¡Prestamo registrado!", HttpStatus.CREATED);
    }

    @PutMapping("/editar/{PrestamoId}")
    public ResponseEntity<?> editar_PUT(@RequestBody Prestamo prestamo, @PathVariable Integer prestamoId) {
        Prestamo prestamoDb = prestamoService.findById(prestamoId);

        if (prestamoDb != null) {
            prestamoDb.setCliente(prestamo.getCliente());
            prestamoDb.setItemLibros(prestamo.getItemLibros());
            prestamoDb.setFprestamo(prestamo.getFprestamo());
            prestamoDb.setFlimite(prestamo.getFlimite());

            prestamoService.update(prestamoDb);
            return new ResponseEntity<>("¡Datos del Prestamo actualizado!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Prestamo no encontrado!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrar/{prestamoId}")
    public ResponseEntity<?> borrar_DELETE(@PathVariable Integer prestamoId) {
        Prestamo prestamoDb = prestamoService.findById(prestamoId);

        if (prestamoDb != null) {
            prestamoService.delete(prestamoId);
            return new ResponseEntity<>("¡Prestamo eliminado de la BD!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Prestamo no encontrado!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/{prestamoId}")
    public ResponseEntity<?> buscar_GET(@PathVariable Integer prestamoId) {
        Prestamo prestamoDb = prestamoService.findById(prestamoId);

        if (prestamoDb != null) {
            return new ResponseEntity<>(prestamoDb, HttpStatus.FOUND);
        }

        return new ResponseEntity<>("¡Prestamo no encontrado!", HttpStatus.NOT_FOUND);
    }
}
