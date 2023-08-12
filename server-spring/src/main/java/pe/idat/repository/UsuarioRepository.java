package pe.idat.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.idat.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

	
	@Query(value="select usu.user_id, usu.nomuser as 'usuario', rl.role_id, rl.nomrol as 'rol' from users_roles pp\r\n"
			+ "inner join users usu on pp.user_id=usu.user_id\r\n"
			+ "inner join roles rl on pp.rol_id=rol.rol_id order by user_id\r\n",nativeQuery=true)
	public abstract Collection<Object[]> findAll_withroles();

}
