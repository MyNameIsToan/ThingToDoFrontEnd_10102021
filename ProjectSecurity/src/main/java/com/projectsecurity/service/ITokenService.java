package com.projectsecurity.service;

import com.projectsecurity.entity.Token;

public interface ITokenService {
	Token createToken(Token token);
	Token findByToken(String token);
	int remove(Token Token);
}
