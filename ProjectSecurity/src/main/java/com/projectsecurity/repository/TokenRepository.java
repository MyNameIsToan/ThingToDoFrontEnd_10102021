package com.projectsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectsecurity.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long>{
	Token findByToken(String token);
}
