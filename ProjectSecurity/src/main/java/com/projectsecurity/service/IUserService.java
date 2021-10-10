package com.projectsecurity.service;

import com.projectsecurity.Utils.UserPrincipal;
import com.projectsecurity.entity.User;

public interface IUserService {
	User createUser(User user);
	UserPrincipal findByUsername(String username);
}
