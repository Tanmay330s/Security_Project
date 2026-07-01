package com.abc.config;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abc.entity.MyUser;
import com.abc.entity.Role;
import com.abc.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MyUser user = repo.findByEmail(username);
		
		if(user != null) {
			List<Role> roles = user.getRole();
			List<SimpleGrantedAuthority> authority = roles.stream().map(r->new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
		return new User(user.getEmail(),user.getPassword(),authority);
	}
		else {
			throw new UsernameNotFoundException("Invalid Username or Password");
		}
	}

}
