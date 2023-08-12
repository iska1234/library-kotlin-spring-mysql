package pe.idat.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "carnet")
public class Carnet implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer carnetId;
    
    @Column
    private String codigo;

    @Column
    private String tcarnet;

    @Column
    private String nprestamos;

    @OneToOne(mappedBy = "carnet")
    @JsonBackReference
    private Cliente cliente;

    public Carnet() {
    }

    public Carnet(Integer carnetId, String codigo, String tcarnet, String nprestamos) {
        this.carnetId = carnetId;
        this.codigo = codigo;
        this.tcarnet = tcarnet;
        this.nprestamos = nprestamos;
    }

    public Integer getCarnetId() {
        return carnetId;
    }

    public void setCarnetId(Integer carnetId) {
        this.carnetId = carnetId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTcarnet() {
        return tcarnet;
    }

    public void setTcarnet(String tcarnet) {
        this.tcarnet = tcarnet;
    }

    public String getNprestamos() {
        return nprestamos;
    }

    public void setNprestamos(String nprestamos) {
        this.nprestamos = nprestamos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
