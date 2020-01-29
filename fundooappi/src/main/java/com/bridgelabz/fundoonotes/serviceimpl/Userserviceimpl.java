package com.bridgelabz.fundoonotes.serviceimpl;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.config.Rabbitmqsender;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.Repo;
import com.bridgelabz.fundoonotes.service.Userservice;
import com.bridgelabz.fundoonotes.userdto.Userdto;
import com.bridgelabz.fundoonotes.utility.JMSprovider;
import com.bridgelabz.fundoonotes.utility.JWTprovider;
import com.bridgelabz.fundoonotes.utility.MailObject;
@Service
public class Userserviceimpl implements Userservice {
	@Autowired
	private Repo userRepo;
	@Autowired
	private JWTprovider jwtprovider;
	 @Autowired
	    private Rabbitmqsender rabbitMQSender;
	 @Autowired
	  private MailObject mailObject;
	@Transactional
	@Override
	public User registration(Userdto userDto) {
		User userObj = userDTOToUSer(userDto);
		String email=userDto.getEmail();
		String token=jwtprovider.generateToken(email);
		String url="http://localhost:8080/users/varified/";
		mailObject.setEmail(email);
		mailObject.setMessage(url+token);
		mailObject.setSubject("verification");
        rabbitMQSender.send(mailObject);
		User user = userRepo.saveUser(userObj);
		return user;
}


	public User userDTOToUSer(Userdto userDto) {

		User user = new User();
		user.setFname(userDto.getFname()); 
		user.setLname(userDto.getLname());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		return user;
	}

	

	public boolean matches(String Password, String encodedPassword) {
		boolean status = false;
		if (encodedPassword == null || encodedPassword.length() == 0) {
			if (encodedPassword.equals(Password)) {
				status = true;
			}
		}
		return status;
	}
@Transactional
	public boolean login(String email, String password) {
		List<User> userList = userRepo.getAllUser();
		
		for (User user : userList)
			if (user.getEmail().equalsIgnoreCase(email) && matches(password, user.getPassword())) {
				System.out.println("Email :"+user.getEmail()+",Password :"+user.getPassword());
				if (userRepo.isVarified(user)) {
					return true;
				}
			}
		return false;
	}
@Transactional
@Override
	public boolean forgetPassword(String emailid) {
		List<User> userList = userRepo.getAllUser();
		for (User user : userList) {
			if (user.getEmail().equals(emailid)) {
				String email = user.getEmail();
			 String token=jwtprovider.generateToken(email);
				String url = "http://localhost:8080/api/resetpassword/";
				 JMSprovider.sendEmail(email, "for reset password", url+token);
				return true;
			}
		}
		return false;

	}
@Transactional
public void parseToken(String token) {
	String email=jwtprovider.parseToken(token);
	List<User> userList=userRepo.getAllUser();
	for(User user:userList) {
		String checkEmail=user.getEmail();
		if(email.equals(checkEmail)) {
			user.setStatus(true);
			userRepo.saveUser(user);
		}
	}
}
@Transactional
private List<User> getAllUser( String token) {
	
	 return userRepo.getAllUser();
}
private String encryptPassword(String plainTextPassword) {
	return plainTextPassword;

	}
@Transactional
@Override
public boolean resetPassword(String emailId, String password) {
	String email=jwtprovider.parseToken(emailId);
	List<User> userList=userRepo.getAllUser(); //.getAllUser();
	for(User user :userList) {
		if(user.getEmail().equals(email)) {

			String newPassword=encryptPassword(password);
			user.setPassword(newPassword);
			userRepo.isVarified(user);
			return true;
			}}
	return false;
}



@Override
public List<User> showAllUser(String token) {
	List<User> userList =userRepo.getAllUser();//getAllNotes(token);//.getAllNotes(token);
	if (userList!= null) {
		return userList;
	}
	return null;


}



/*@Override
public boolean isVarified(@Valid String email) {
	// TODO Auto-generated method stub
	return false;
}*/
}
	
	


