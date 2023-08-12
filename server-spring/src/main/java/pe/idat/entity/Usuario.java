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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
	
private static final long serialVersionUID = 1L;
	
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userId;
    
    @Column
    private String nomusu;

    @Column
    private String clausu;

    @Column
    private Boolean estusu;
    
    @ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="users_roles",
	joinColumns = @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="rol_id"))
	private Set<Rol> itemsRol=new HashSet<>();
    
    
    public Usuario() {
		// TODO Auto-generated constructor stub
	}


	public Usuario(Integer userId, String nomusu, String clausu, Boolean estusu) {
		super();
		this.userId = userId;
		this.nomusu = nomusu;
		this.clausu = clausu;
		this.estusu = estusu;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getNomusu() {
		return nomusu;
	}


	public void setNomusu(String nomusu) {
		this.nomusu = nomusu;
	}


	public String getClausu() {
		return clausu;
	}


	public void setClausu(String clausu) {
		this.clausu = clausu;
	}


	public Boolean getEstusu() {
		return estusu;
	}


	public void setEstusu(Boolean estusu) {
		this.estusu = estusu;
	}


	public Set<Rol> getItemsRol() {
		return itemsRol;
	}


	public void setItemsRol(Set<Rol> itemsRol) {
		this.itemsRol = itemsRol;
	}

	
	
    
    
	

}
