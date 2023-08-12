package pe.idat.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clienteId;

    @Column
    private String nombre;

    @Column
    private String apellido_mat;

    @Column
    private String apelido_pat;

    @Column
    private String dni;

    @Column
    private String direccion;

    @Column
    private String telefono;

    @Column
    private String correo;
    
    @Column
    private String passw;

    @Column
    private Boolean estado;

    @OneToOne
    @JoinColumn(name = "carnet_id", unique = true, nullable = true)
    private Carnet carnet;

    @OneToMany(mappedBy = "cliente")
    @JsonBackReference
    private Collection<Prestamo> itemsPrestamo = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Integer clienteId, String nombre, String apellido_mat, String apelido_pat, String dni, String direccion, String telefono, String correo,String passw, Boolean estado) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellido_mat = apellido_mat;
        this.apelido_pat = apelido_pat;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
        this.passw=passw;
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

    public String getApellido_mat() {
        return apellido_mat;
    }

    public void setApellido_mat(String apellido_mat) {
        this.apellido_mat = apellido_mat;
    }

    public String getApelido_pat() {
        return apelido_pat;
    }

    public void setApelido_pat(String apelido_pat) {
        this.apelido_pat = apelido_pat;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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
    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Carnet getCarnet() {
        return carnet;
    }

    public void setCarnet(Carnet carnet) {
        this.carnet = carnet;
    }

    public Collection<Prestamo> getItemsPrestamo() {
        return itemsPrestamo;
    }

    public void setItemsPrestamo(Collection<Prestamo> itemsPrestamo) {
        this.itemsPrestamo = itemsPrestamo;
    }
}
