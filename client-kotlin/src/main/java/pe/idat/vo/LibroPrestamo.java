package pe.idat.vo;

import pe.idat.entity.Libros;
import pe.idat.entity.Prestamo;

public class LibroPrestamo {

    private Libros libros;
    private Prestamo prestamo;
    public LibroPrestamo() {
    }

    public LibroPrestamo(Libros libros, Prestamo prestamo) {
        this.libros = libros;
        this.prestamo = prestamo;
    }

    public Libros getLibros() {
        return libros;
    }

    public void setLibros(Libros libros) {
        this.libros = libros;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
}
