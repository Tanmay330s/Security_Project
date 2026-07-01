package com.abc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.entity.Role;
import java.util.List;


public interface RoleRepo extends JpaRepository<Role, Integer> {

	Role findByName(String name);
}
