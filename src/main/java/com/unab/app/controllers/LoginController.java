package com.unab.app.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unab.app.models.Usuario;
import com.unab.app.security.SimpleGrantedAuthorityMixin;
import com.unab.app.security.tokenUtil.IJWTService;
import com.unab.app.security.tokenUtil.JWTServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
	
	@Autowired
	private IJWTService jwtService;
	
	@Autowired 
	private DaoAuthenticationProvider daoAuthenticationProvider;
	
	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody Usuario usuario){
		Authentication authenticacion=daoAuthenticationProvider.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(usuario.getUsername(), usuario.getPassword()));
		String token;
		Map<String, Object> map=new HashMap<>();
		try {
			token = jwtService.create(authenticacion);
			@SuppressWarnings("deprecation")
			Claims claims=Jwts.parser()
					.setSigningKey(JWTServiceImpl.SECRET.getBytes()) //obtenemos la llave con la key firmada
					.parseClaimsJws(token)
					.getBody();
			Collection<? extends GrantedAuthority> authorities = Arrays.asList(new ObjectMapper()
					.addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)  //para añadirle los roles al token de autenticacion
					.readValue(claims.get("authorities").toString().getBytes(), SimpleGrantedAuthority[].class));
			
			List<? extends GrantedAuthority> rolesList= authorities.stream().collect(Collectors.toList());
			
			List<String> roles=new ArrayList<>();
			
			rolesList.forEach(t->
				roles.add(t.getAuthority().toString())
			);
			
			System.out.println(roles.getClass());
			
			map.put("token", token);
			map.put("usuario", usuario.getUsername());
			map.put("roles", roles);

			return new ResponseEntity<Map<String, Object>> (map,HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("TEST LOGIN");
			map.put("error", "Usuario o contraseña invalido");
			return new ResponseEntity<Map<String, Object>> (map,HttpStatus.BAD_REQUEST);
		}
		
	}
}
