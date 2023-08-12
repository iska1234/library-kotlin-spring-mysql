package pe.idat.entity;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "libros")
public class Libros implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer libroId;

	@Column
	private String titulo;

	@Column
	private String autor;

	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	private LocalDate fpublicacion;

	@Column
	private String editorial;

	@Column
	private String genero;

	@Column
	private Integer npaginas;

	@ManyToMany
	@JsonBackReference
	@JoinTable(name = "libro_prestamo", 
		joinColumns = @JoinColumn(name = "libro_id"), 
		inverseJoinColumns = @JoinColumn(name = "prestamo_id"))
	private Set<Prestamo> itemsPrestamo = new HashSet<>();

	public Libros() {
	}

	public Libros(Integer libroId, String titulo, String autor, LocalDate fpublicacion, String editorial, String genero,
			Integer npaginas) {
		this.libroId = libroId;
		this.titulo = titulo;
		this.autor = autor;
		this.fpublicacion = fpublicacion;
		this.editorial = editorial;
		this.genero = genero;
		this.npaginas = npaginas;
	}

	public void addPrestamo(Prestamo prestamo){
		itemsPrestamo.add(prestamo);
	}

	@PrePersist
	public void prePersist() {
		fpublicacion = LocalDate.now();
	}

	public Integer getLibroId() {
		return libroId;
	}

	public void setLibroId(Integer libroId) {
		this.libroId = libroId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public LocalDate getFpublicacion() {
		return fpublicacion;
	}

	public void setFpublicacion(LocalDate fpublicacion) {
		this.fpublicacion = fpublicacion;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getNpaginas() {
		return npaginas;
	}

	public void setNpaginas(Integer npaginas) {
		this.npaginas = npaginas;
	}

	public Set<Prestamo> getItemsPrestamo() {
		return itemsPrestamo;
	}

	public void setItemsPrestamo(Set<Prestamo> itemsPrestamo) {
		this.itemsPrestamo = itemsPrestamo;
	}
	
}
