package com.abc.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.abc.entity.MyUser;
import com.abc.entity.Role;
import com.abc.repo.RoleRepo;
import com.abc.repo.UserRepo;

@Component
public class DataInitializeRunner implements CommandLineRunner {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public void run(String... args) throws Exception {
		
		String[] roles = {"normal", "admin", "superadmin"};

		for (String role : roles) {
		    createRolesIfNotExists(role);
		}
		
		createAdminUser();
		createSuperAdminUser();
	}
	
	private void createRolesIfNotExists(String roleName) {
		Role role = roleRepo.findByName(roleName);
		
		if(role==null) {
			Role r = new Role();
			r.setName(roleName);
			roleRepo.save(r);
			System.out.println("role created!!");
		}
		
	}
	
	private void createAdminUser() {
		String email = "admin@gmail.com";
		MyUser existingUser = userRepo.findByEmail(email);
		
		if(existingUser==null) {
			MyUser u = new MyUser();
			u.setFname("tanmay");
			u.setLname("shinde");
			u.setEmail(email);
			u.setPassword(encoder.encode("admin123"));
			
			Role role = roleRepo.findByName("admin");
			List<Role> roles = List.of(role);
			u.setRole(roles);
			userRepo.save(u);
			System.out.println("admin Saved");
		}
	}
	
	
	private void createSuperAdminUser() {
		String email = "super@gmail.com";
		MyUser existingUser = userRepo.findByEmail(email);
		
		if(existingUser==null) {
			MyUser u = new MyUser();
			u.setFname("abc");
			u.setLname("xyz");
			u.setEmail(email);
			u.setPassword(encoder.encode("super123"));
			
			Role role1 = roleRepo.findByName("normal");
			Role role2 = roleRepo.findByName("admin");
			Role role3 = roleRepo.findByName("superadmin");
			
			List<Role> roles = List.of(role1,role2,role3);
			u.setRole(roles);
			userRepo.save(u);
			System.out.println("superadmin Saved");
		}
	}

}
