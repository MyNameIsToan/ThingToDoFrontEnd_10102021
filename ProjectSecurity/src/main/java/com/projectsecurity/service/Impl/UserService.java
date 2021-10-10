package com.projectsecurity.service.Impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectsecurity.Utils.UserPrincipal;
import com.projectsecurity.entity.User;
import com.projectsecurity.repository.UserRepository;
import com.projectsecurity.service.IUserService;

@Service
public class UserService implements IUserService {
	@Autowired
    private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		User SubUser = userRepository.findByUsername(user.getUsername());
		if(SubUser != null)
		{
			return null;
		}
		return userRepository.saveAndFlush(user);
	}

	@Override
	public UserPrincipal findByUsername(String username) {
		 User user = userRepository.findByUsername(username);
	        UserPrincipal userPrincipal = new UserPrincipal();
	        if (null != user) {
	            Set<String> authorities = new HashSet<>();
	            if (null != user.getRoles()) user.getRoles().forEach(r -> {
	                authorities.add(r.getRoleKey());
	                r.getPermissions().forEach(p -> authorities.add(p.getPermissionKey()));
	            });

	            userPrincipal.setUserId(user.getId());
	            userPrincipal.setUsername(user.getUsername());
	            userPrincipal.setPassword(user.getPassword());
	            userPrincipal.setAuthorities(authorities);
	        }
	        return userPrincipal;
	}
}
