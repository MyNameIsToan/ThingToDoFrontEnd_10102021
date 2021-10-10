package com.projectsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectsecurity.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
