/*package pe.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.idat.entity.Libros;
import pe.idat.entity.Prestamo;
import pe.idat.service.LibroService;
import pe.idat.service.PrestamoService;
import pe.idat.vo.LibroPrestamo;

@RestController
@RequestMapping("/libro_prestamo")
public class LibroPrestamoRestController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private PrestamoService prestamoService;

    public LibroPrestamoRestController() {
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar_GET() {
        return new ResponseEntity<>(libroService.findAll_withPrestamos(), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar_POST(@RequestBody LibroPrestamo libroPrestamo){

        Integer libroId = libroPrestamo.getLibros().getLibroId();
        Libros libroDb = libroService.findById(libroId);


        if (libroDb != null){
            Integer prestamoId = libroPrestamo.getPrestamo().getPrestamoId();
            Prestamo prestamoDb = prestamoService.findById(prestamoId);

            if (prestamoDb != null){
                libroDb.addPrestamo(prestamoDb);
                libroService.update(libroDb);

                return new ResponseEntity<>("Libro agregado al Prestamo exitosamente",HttpStatus.CREATED);
            }

            return new ResponseEntity<>("Error",HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>("El libro no existe",HttpStatus.NOT_FOUND);

    }
}
*/