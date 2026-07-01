package com.abc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.entity.MyUser;
import java.util.List;


public interface UserRepo extends JpaRepository<MyUser, Integer> {
	
	MyUser findByEmail(String email);

}
