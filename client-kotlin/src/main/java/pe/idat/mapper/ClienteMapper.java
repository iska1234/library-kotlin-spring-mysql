package pe.idat.mapper;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pe.idat.entity.Carnet;
import pe.idat.entity.Cliente;
import pe.idat.entity.Prestamo;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Collection;

public class ClienteMapper {
    private Integer clienteId;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String carnet;

    public ClienteMapper() {
    }

    public ClienteMapper(Integer clienteId, String nombre, String direccion, String telefono, String correo, String carnet) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.carnet = carnet;
    }

    public ClienteMapper(Cliente cliente) {

        this(cliente.getClienteId(),
                cliente.getNombre(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getCorreo(),
                cliente.getCarnet().getCodigo());
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }
}
