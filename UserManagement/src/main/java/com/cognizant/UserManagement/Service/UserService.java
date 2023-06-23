package com.cognizant.UserManagement.Service;

import com.cognizant.UserManagement.Entity.User;
import com.cognizant.UserManagement.Exception.UserFoundException;

public interface UserService {

	User saveUser(User user) throws UserFoundException;

	void updateUser(User user);

	User getUserByUsername(String username);

}
