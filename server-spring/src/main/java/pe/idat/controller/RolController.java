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

import pe.idat.entity.Rol;
import pe.idat.service.RolService;



@RestController
@RequestMapping("/rol")
public class RolController {

	

	@Autowired
    private RolService rolService;

	public RolController() {
		// TODO Auto-generated constructor stub
	}

    @GetMapping("/listar")
    public ResponseEntity<?> listar_GET() {
        Collection<Rol> rolDb = rolService.findAll();

        if (rolDb.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(rolDb, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar_GET(@RequestBody Rol rol) {
    	rolService.insert(rol);
        return new ResponseEntity<>("¡Prestamo registrado!", HttpStatus.CREATED);
    }

    @PutMapping("/editar/{rolId}")
    public ResponseEntity<?> editar_PUT(@RequestBody Rol rol, @PathVariable Integer rolId) {
    	Rol rolDb = rolService.findById(rolId);

        if (rolDb != null) {
        	rolDb.setNomrol(rol.getNomrol());
        	rolDb.setEstrol(rol.getEstrol());
        	
        	
        	rolService.update(rolDb);
            return new ResponseEntity<>("¡Datos del Prestamo actualizado!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Prestamo no encontrado!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrar/{rolId}")
    public ResponseEntity<?> borrar_DELETE(@PathVariable Integer rolId) {
    	Rol rolDb = rolService.findById(rolId);

        if (rolDb != null) {
        	rolService.delete(rolId);
            return new ResponseEntity<>("¡Prestamo eliminado de la BD!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Prestamo no encontrado!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/{rolId}")
    public ResponseEntity<?> buscar_GET(@PathVariable Integer rolId) {
    	Rol rolDb = rolService.findById(rolId);

        if (rolDb != null) {
            return new ResponseEntity<>(rolId, HttpStatus.FOUND);
        }

        return new ResponseEntity<>("¡Prestamo no encontrado!", HttpStatus.NOT_FOUND);
    }
}
