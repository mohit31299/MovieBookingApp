package com.cognizant.UserManagement.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.UserManagement.Entity.User;
import com.cognizant.UserManagement.Exception.UserFoundException;
import com.cognizant.UserManagement.Repository.UserRepository;
import com.cognizant.UserManagement.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDetailServiceImpl userDetailServiceImpl;

	public User saveUser(User user) throws UserFoundException {

		User userTemp = userRepository.findByUsername(user.getUsername());
		if (userTemp != null) {
			throw new UserFoundException("User name " + user.getUsername() + " is already present.");
		} else {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			return userRepository.save(user);
		}

	}

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void updateUser(User user) {
		System.out.println("user: "+user);
		userRepository.updatePassword(user.getPassword(), user.getUsername());
//		return userRepository.save(user);
	}

}
