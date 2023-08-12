package pe.idat.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;





@Entity
@Table(name = "rol")
public class Rol implements Serializable{
	
private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer rolId;
    
    @Column
    private String nomrol;

    @Column
    private Boolean estrol;
    
    @ManyToMany(mappedBy="itemsRol",fetch=FetchType.EAGER)
	private Set<Usuario> itemsUsuario=new HashSet<>();

	public Rol() {
		// TODO Auto-generated constructor stub
	}

	public Rol(Integer rolId, String nomrol, Boolean estrol) {
		super();
		this.rolId = rolId;
		this.nomrol = nomrol;
		this.estrol = estrol;
	}

	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	public String getNomrol() {
		return nomrol;
	}

	public void setNomrol(String nomrol) {
		this.nomrol = nomrol;
	}

	public Boolean getEstrol() {
		return estrol;
	}

	public void setEstrol(Boolean estrol) {
		this.estrol = estrol;
	}

	public Set<Usuario> getItemsUsuario() {
		return itemsUsuario;
	}

	public void setItemsUsuario(Set<Usuario> itemsUsuario) {
		this.itemsUsuario = itemsUsuario;
	}
	
	
	
	
	

}
