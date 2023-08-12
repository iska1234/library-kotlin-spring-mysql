package pe.idat.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;


@Entity
@Table(name = "prestamo")
public class Prestamo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prestamoId;

    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd",iso= DateTimeFormat.ISO.DATE)
    private LocalDate fprestamo;

    @Column
    private LocalDate flimite;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToMany(mappedBy = "itemsPrestamo")
    private Set<Libros> itemLibros = new HashSet<>();

    public Prestamo() {
    }

    public Prestamo(Integer prestamoId, LocalDate fprestamo, LocalDate flimite) {
        this.prestamoId = prestamoId;
        this.fprestamo = fprestamo;
        this.flimite = flimite;
    }

    @PrePersist
    public void prePersist() {
        fprestamo = LocalDate.now();
        flimite = LocalDate.now();
    }

    public Integer getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(Integer prestamoId) {
        this.prestamoId = prestamoId;
    }

    public LocalDate getFprestamo() {
        return fprestamo;
    }

    public void setFprestamo(LocalDate fprestamo) {
        this.fprestamo = fprestamo;
    }

    public LocalDate getFlimite() {
        return flimite;
    }

    public void setFlimite(LocalDate flimite) {
        this.flimite = flimite;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Libros> getItemLibros() {
        return itemLibros;
    }

    public void setItemLibros(Set<Libros> itemLibros) {
        this.itemLibros = itemLibros;
    }

}
