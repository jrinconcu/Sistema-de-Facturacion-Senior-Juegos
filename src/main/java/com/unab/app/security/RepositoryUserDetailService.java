package com.unab.app.security;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unab.app.dao.IUsuarioDAO;
import com.unab.app.models.Role;
import com.unab.app.models.Usuario;

@Service
public class RepositoryUserDetailService implements UserDetailsService {
	
	private Logger logger=LoggerFactory.getLogger(RepositoryUserDetailService.class);

	@Autowired
	private IUsuarioDAO usuarioDao;
	
	@SuppressWarnings("unused")
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario=usuarioDao.findByUsername(username);
		
		logger.info(username);
		logger.info(usuario.getUsername());
		logger.info(usuario.getPassword());
		
		if(usuario == null) {
			logger.error("Error en el login, el usuario "+username+" no existe en el sistema" );
			throw new UsernameNotFoundException("Error en el login, el usuario "+username+" no existe en el sistema");
		}
		
		logger.info("TEST 1");
		
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		
		for(Role role: usuario.getRoles()) {
			logger.info("Role: "+role.getAuthority());
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		
		logger.info("TEST 1");
		
		if(authorities.isEmpty()) {
			logger.error("Error en el login, el usuario "+username+" no tiene roles asignados en el sistema" );
			throw new UsernameNotFoundException("Error en el login, el usuario "+username+" no tiene roles asignados en el sistema");
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

}
