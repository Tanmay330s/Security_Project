package com.abc.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abc.entity.MyUser;
import com.abc.entity.Role;
import com.abc.repo.RoleRepo;
import com.abc.repo.UserRepo;
import com.abc.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public MyUser saveUser(MyUser myUser) {
		
		String password = myUser.getPassword();
		
		String encodedPwd = encoder.encode(password);
		
		myUser.setPassword(encodedPwd);
		
		Role role = roleRepo.findByName("normal");
		
		List<Role> roles = Arrays.asList(role);
		
		myUser.setRole(roles);
		
		return userRepo.save(myUser);
	}

}
