package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import com.bridgelabz.fundoonotes.model.User;

public interface Repo {
	 List<User> getAllUser();
	 User saveUser(User user);
	User getUserById(Integer id);
	 boolean isVarified(User user);
	 User update(Integer id);
	
	
	
	
}
