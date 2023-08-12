package pe.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.idat.service.RolService;
import pe.idat.service.UsuarioService;


@RestController
@RequestMapping("/users_roles")
public class UsuarioRolController {
	
	@Autowired
	public UsuarioService userService;
	
	@Autowired
	public RolService roleService;
	
	public UsuarioRolController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		
		return new ResponseEntity<>(userService.findAll_withroles(),HttpStatus.OK);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody UserRoles userRoles){
		
		Integer userId=userRoles.getUser().getUserId();
		UserEntity userDb=userService.findById(userId);
		
		if(userId!=null) 
		{
			Integer roleId=userRoles.getRole().getRoleId();
			RoleEntity roleDb=roleService.findById(roleId);
			if(roleDb!=null) 
			{
			
				userDb.addRole(roleDb);
				userService.save(userDb);
				return new ResponseEntity<>("¡User-roles registrado exitosamente!",HttpStatus.CREATED);
			}
			return new ResponseEntity<>("¡Error Rol no existe!",HttpStatus.NOT_FOUND);
		
		}
		return new ResponseEntity<>("¡Error Usuario no existe!",HttpStatus.NOT_FOUND);
		
	}

}
