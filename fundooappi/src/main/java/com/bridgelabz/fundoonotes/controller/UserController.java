package com.bridgelabz.fundoonotes.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.Userservice;
import com.bridgelabz.fundoonotes.userdto.Userdto;

//import io.swagger.annotations.Api;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
//@Api(value = "fundoonote ")
public class UserController {
@Autowired
	private Userservice userservice;

@PostMapping("users/register")
	public ResponseEntity<Response> register(@RequestBody Userdto userDto) {
		User user = userservice.registration(userDto);
		if (user !=null) {
			return new ResponseEntity<Response>(new Response( HttpStatus.OK.value(),"register sucess"), HttpStatus.OK);
		}
		return new ResponseEntity<Response>(new Response( HttpStatus.BAD_REQUEST.value(),"register is not sucessfull"), HttpStatus.BAD_REQUEST);

	}

@PostMapping("users/login")	
public ResponseEntity<Response> login(@Valid @RequestBody String password,	String email ) {
	if(userservice.login(password, email))
	{
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "login Successfully"),HttpStatus.OK);
	}
	return new ResponseEntity<Response>(new Response(HttpStatus.BAD_REQUEST.value(),"login Unsuccesfull"),HttpStatus.BAD_REQUEST);
}

@PostMapping("users/forgetPassword")	
public ResponseEntity<Response> forgetPassword(@PathVariable("email")	String email ) {
	if(userservice.forgetPassword(email))
	{
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(),"password recovered"),HttpStatus.OK);
	}
	return new ResponseEntity<Response>(new Response(HttpStatus.BAD_REQUEST.value(),"password unable to recover"),HttpStatus.BAD_REQUEST);

	
		}


@PostMapping("users/resetpassword/{token}")
public ResponseEntity<Response> resetPassword(@PathVariable(name="token") String token,@RequestBody User user)
{
	if(userservice.resetPassword(token, user.getPassword())) 
		{
			return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(),"password recovered"),HttpStatus.OK);
		
	
		}
	return new ResponseEntity<Response>(new Response(HttpStatus.BAD_REQUEST.value(),"password unable to recover"),HttpStatus.BAD_REQUEST);

}
@GetMapping("user/getall/{token}")
public ResponseEntity<Response> getAllNotes(@PathVariable(value = "token") String token){
	
	List<User> noteList=userservice.showAllUser(token);
	if(!noteList.isEmpty()) {
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "successfully got"),
				HttpStatus.OK);
		}
	return new ResponseEntity<Response>(new Response(HttpStatus.BAD_REQUEST.value(), " not"),
				HttpStatus.BAD_REQUEST);
	}
@GetMapping("users/varify/{token}")
public String getTocken(@PathVariable(name="token") String token) {
	userservice.parseToken(token);
	return "user is varified";
}
}

		
	









