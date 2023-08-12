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

import pe.idat.entity.Carnet;

import pe.idat.service.CarnetService;

@RestController
@RequestMapping("/carnet")
public class CarnetRestController {

    @Autowired
    private CarnetService carnetService;

    public CarnetRestController(){
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar_GET() {
        Collection<Carnet> carnetDb = carnetService.findAll();

        if (carnetDb.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(carnetDb, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar_GET(@RequestBody Carnet carnet) {
        carnetService.insert(carnet);
        return new ResponseEntity<>("¡Carnet registrado!", HttpStatus.CREATED);
    }

    @PutMapping("/editar/{carnetId}")
    public ResponseEntity<?> editar_PUT(@RequestBody Carnet carnet, @PathVariable Integer carnetId) {
        Carnet carnetDb = carnetService.findById(carnetId);

        if (carnetDb != null) {
            carnetDb.setCodigo(carnet.getCodigo());
            carnetDb.setTcarnet(carnet.getTcarnet());
            carnetDb.setNprestamos(carnet.getNprestamos());

            carnetService.update(carnetDb);
            return new ResponseEntity<>("¡Datos del carnet actualizado!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Carnet no encontrado!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrar/{carnetId}")
    public ResponseEntity<?> borrar_DELETE(@PathVariable Integer carnetId) {
        Carnet carnetDb = carnetService.findById(carnetId);

        if (carnetDb != null) {
            carnetService.delete(carnetId);
            return new ResponseEntity<>("¡Carnet eliminado de la BD!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Carnet no encontrado!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/{carnetId}")
    public ResponseEntity<?> buscar_GET(@PathVariable Integer carnetId) {
        Carnet carnetDb = carnetService.findById(carnetId);

        if (carnetDb != null) {
            return new ResponseEntity<>(carnetDb, HttpStatus.FOUND);
        }
        
        return new ResponseEntity<>("¡Carnet no encontrado!", HttpStatus.NOT_FOUND);
    }
    
}
