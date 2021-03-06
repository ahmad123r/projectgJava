package com.Project.auth.services;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Project.auth.models.User;
import com.Project.auth.repositories.RoleRepository;
import com.Project.auth.repositories.UserRepository;

@Service
public class UserService {
	 private UserRepository userRepository;
	    private RoleRepository roleRepository;
	    private BCryptPasswordEncoder bCryptPasswordEncoder;
	    
	    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)     {
	        this.userRepository = userRepository;
	        this.roleRepository = roleRepository;
	        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	    }
	    
	    
	    // 1
	    public void saveWithUserRole(User user) {
	        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        user.setRoles(roleRepository.findByName("ROLE_USER"));
	        userRepository.save(user);
	    }
	     
	     // 2 
	    public void saveUserWithAdminRole(User user) {
	        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
	        userRepository.save(user);
	    }    
	    
	    // 3
	    public User findByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }
	    public User findUserById(Long id) {
            Optional<User> u =userRepository.findById(id);

            if(u.isPresent()) {
                return u.get();
            } else {
                return null;
            }
        }
//	    public Optional<User> findbyid(long id) {
//	    	return userRepository.findById(id);
//	    	
//	    }
	    
	    public Iterable<User> findallusers() {
	    	return userRepository.findAll();
	    }
	    
}
