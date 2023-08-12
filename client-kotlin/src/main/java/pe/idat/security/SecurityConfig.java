package pe.idat.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.inMemoryAuthentication().withUser("leo").password("{noop}123").roles("Usuario");
		auth.inMemoryAuthentication().withUser("mara").password("{noop}456").roles("Empleado");
		auth.inMemoryAuthentication().withUser("oscar").password("{noop}789").roles("Administrador");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.authorizeHttpRequests()
		    	.antMatchers("/libro/listar").permitAll()
				.antMatchers("/libro/editar/*").hasRole("Administrador")
				.antMatchers("/libro/registrar").hasRole("Administrador")
				.antMatchers("/libro/borrar/*").hasRole("Administrador")

				.antMatchers("/carnet/listar").hasAnyRole("Empleado","Administrador")
				.antMatchers("/carnet/editar/*").hasAnyRole("Empleado","Administrador")
				.antMatchers("/carnet/agregar").hasAnyRole("Empleado","Administrador")
				.antMatchers("/carnet/borrar/*").hasRole("Administrador")

				.antMatchers("/cliente/listar").hasRole("Empleado")
				.antMatchers("/cliente/editar/*").hasAnyRole("Administrador", "Usuario")
				.antMatchers("/cliente/registrar").hasAnyRole("Administrador", "Usuario")
				.antMatchers("/cliente/borrar/*").hasRole("Administrador")

				.antMatchers("/prestamo/listar").hasRole("Administrador")
				.antMatchers("/prestamo/editar/*").hasRole("Empleado")
				.antMatchers("/prestamo/registrar").hasRole("Usuario")
				.antMatchers("/prestamo/borrar/*").hasRole("Administrador");


		http.authorizeHttpRequests().and()
		    .httpBasic();

		http.authorizeHttpRequests().and()
		    .csrf().disable();
	}
}
