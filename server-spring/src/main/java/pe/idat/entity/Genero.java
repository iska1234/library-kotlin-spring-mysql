package pe.idat.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "genero")
public class Genero implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer generoId;

    @Column
    private String descripcion;

    @OneToMany(mappedBy = "genero")
    @JsonBackReference
    private Collection<Libros> itemsLibros = new ArrayList<>();

    public Genero() {
    }

    public Genero(Integer generoId, String descripcion) {
        this.generoId = generoId;
        this.descripcion = descripcion;
    }

    public Integer getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Integer generoId) {
        this.generoId = generoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Libros> getItemsLibros() {
        return itemsLibros;
    }

    public void setItemsLibros(Collection<Libros> itemsLibros) {
        this.itemsLibros = itemsLibros;
    }
}
