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

import pe.idat.entity.Cliente;
import pe.idat.mapper.ClienteMapper;
import pe.idat.service.ClienteService;
import pe.idat.util.ConvertMapper;

@RestController
@RequestMapping("/cliente")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    public ClienteRestController() {
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar_GET() {
        Collection<Cliente> clienteDb = clienteService.findAll();

        Collection<ClienteMapper> clienteMapper = ConvertMapper.toCliente(clienteDb);

        if (clienteDb.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(clienteMapper, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar_GET(@RequestBody Cliente cliente) {
        clienteService.insert(cliente);
        return new ResponseEntity<>("¡Cliente registrado!", HttpStatus.CREATED);
    }

    @PutMapping("/editar/{clienteId}")
    public ResponseEntity<?> editar_PUT(@RequestBody Cliente cliente, @PathVariable Integer clienteId) {
        Cliente clienteDb = clienteService.findById(clienteId);

        if (clienteDb != null) {
            clienteDb.setNombre(cliente.getNombre());
            clienteDb.setCarnet(cliente.getCarnet());
            clienteDb.setDireccion(cliente.getDireccion());
            clienteDb.setTelefono(cliente.getTelefono());
            clienteDb.setCorreo(cliente.getCorreo());

            clienteService.update(clienteDb);
            return new ResponseEntity<>("¡Datos del cliente actualizado!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Cliente no encontrado!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrar/{clienteId}")
    public ResponseEntity<?> borrar_DELETE(@PathVariable Integer clienteId) {
        Cliente clienteDb = clienteService.findById(clienteId);

        if (clienteDb != null) {
            clienteService.delete(clienteId);
            return new ResponseEntity<>("¡Cliente eliminado de la BD!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Cliente no encontrado!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/{clienteId}")
    public ResponseEntity<?> buscar_GET(@PathVariable Integer clienteId) {
        Cliente clienteDb = clienteService.findById(clienteId);

        if (clienteDb != null) {
            return new ResponseEntity<>(clienteDb, HttpStatus.FOUND);
        }

        return new ResponseEntity<>("¡Cliente no encontrado!", HttpStatus.NOT_FOUND);
    }
}
