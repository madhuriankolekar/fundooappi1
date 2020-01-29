package com.bridgelabz.fundoonotes.service;
import java.util.List;
import javax.validation.Valid;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.userdto.Userdto;

public interface Userservice {
	User registration(Userdto user);
	
	boolean login(String password, String email);

	boolean forgetPassword(String emailId);

	boolean resetPassword(String emailId, String password);
	// boolean isVarified(@Valid String email);


	List<User> showAllUser(String token);

	void parseToken(String token);

}
