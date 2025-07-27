package com.narendra.SpringAIDemo.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.narendra.models.UserEntity;
import com.narendra.models.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    
    @Autowired
    private UserRepository repo;


   
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repo.findById(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserPrincipal(user);
    }



	public String register(UserEntity user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPass(encoder.encode(user.getPass()));

        repo.save(user);
        return "register";
	}
    
}
    

