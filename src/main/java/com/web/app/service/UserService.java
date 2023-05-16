package com.web.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.web.app.model.NewUser;
import com.web.app.model.Roles;
import com.web.app.model.User;
import com.web.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MailService mailService;
	
	public NewUser createUser(NewUser newUser) {
    	System.out.println(newUser.getPassword()+" is it equal to "+newUser.getConfirm());
    	if(newUser.getPassword().equals(newUser.getConfirm())) {
    		User user = new User();
    		if(newUser.getRole()==null) {
    			user.setRole(Roles.USER);
    			user.setFirstname(newUser.getFirstname());
                user.setLastname(newUser.getLastname());
                user.setFirstname(newUser.getFirstname());
                user.setEmail(newUser.getEmail());
                user.setUsername(newUser.getUsername());
                user.setPassword(newUser.getPassword());
                userRepository.save(user);
                mailService.newAccountEmail(user);
    		}else {
    			user.setRole(Roles.ADMIN);
    			user.setFirstname(newUser.getFirstname());
                user.setLastname(newUser.getLastname());
                user.setFirstname(newUser.getFirstname());
                user.setEmail(newUser.getEmail());
                user.setUsername(newUser.getUsername());
                user.setPassword(newUser.getPassword());
                userRepository.save(user);
                mailService.newAccountEmail(user);
    		}            
    	}else {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password Must Match");
    	}
		return newUser;
    }
	
	public User logUser(User user) {
    	User userfound = userRepository.checkUsername(user.getUsername());
    	System.out.println(user.getPassword()+" "+userfound.getPassword());
    	if(user.getPassword().equals(userfound.getPassword())) {
    		return userfound;
    	} else {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Incorrect Password");
    	}
    } 
}
