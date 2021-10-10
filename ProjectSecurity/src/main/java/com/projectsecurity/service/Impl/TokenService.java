package com.projectsecurity.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectsecurity.entity.Token;
import com.projectsecurity.repository.TokenRepository;
import com.projectsecurity.service.ITokenService;

@Service
public class TokenService implements ITokenService {
	@Autowired
	private TokenRepository tokenRepository;
	
	@Override
	public Token createToken(Token token) {
		return  tokenRepository.saveAndFlush(token);
	}

	@Override
	public Token findByToken(String token) {
		 return tokenRepository.findByToken(token);
	}

	@Override
	public int remove(Token Token) {
		try{
			tokenRepository.delete(Token);
			return 0;
		}catch(Exception e) {
			return 1;
		}
	}
}
