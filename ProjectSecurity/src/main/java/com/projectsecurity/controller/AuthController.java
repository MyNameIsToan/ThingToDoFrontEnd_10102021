package com.projectsecurity.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectsecurity.Utils.JwtUtil;
import com.projectsecurity.Utils.UserPrincipal;
import com.projectsecurity.dto.TokenDTO;
import com.projectsecurity.entity.Token;
import com.projectsecurity.entity.User;
import com.projectsecurity.service.IUserService;
import com.projectsecurity.service.Impl.TokenService;

@RestController
public class AuthController {
	@Autowired
    private IUserService userService;
	
    @Autowired
    private TokenService tokenService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private HttpServletResponse response;
    
    @PostMapping("/register")
    public User register(@RequestBody User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        UserPrincipal userPrincipal = userService.findByUsername(user.getUsername());
        if (null == user || !new BCryptPasswordEncoder().matches(user.getPassword(), userPrincipal.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản hoặc mật khẩu không chính xác");
        }
        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));
        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setCreatedBy(userPrincipal.getUserId());
        tokenService.createToken(token);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token.getToken());
        tokenDTO.setTokenExpDate(token.getTokenExpDate());
        return ResponseEntity.ok(tokenDTO);
    }
    
    @PostMapping("/signout")
    public ResponseEntity<String> hello(){
    	final String authorizationHeader = request.getHeader("Authorization");
    	UserPrincipal user = null;
        Token token = null;
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Token ")) {
            String jwt = authorizationHeader.substring(6);
            user = jwtUtil.getUserFromToken(jwt);
            token = tokenService.findByToken(jwt);
        }
        if (null != user && null != token && token.getTokenExpDate().after(new Date())) {
        	if(tokenService.remove(token) == 0) {
        		 return ResponseEntity.ok("Logout");
        	}else {
        		return ResponseEntity.ok("False");
        	}
        }
       return ResponseEntity.ok("False");
    }
}
