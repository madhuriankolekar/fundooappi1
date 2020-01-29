package com.bridgelabz.fundoonotes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.fundoonotes.userdto.Userdto;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="user_id")
		private Integer userId;

		@Column()
		private String fname;
		@Column()
		private String Lname;
		@Column()
		private String password;
		@Column()
		private String email;
	    public User() {
	    	
	    }
		public User(Userdto userdto){
			this.fname=userdto.getFname();
			this.Lname=userdto.getLname();
			this.email=userdto.getEmail();
			this.password=userdto.getPassword();
		}
		
		
		public String getFname() {
			return fname;
		}
		public void setFname(String fname) {
			this.fname = fname;
		}
		public String getLname() {
			return Lname;
		}

		public void setLname(String lname) {
			this.Lname = lname;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public void setfname() {
			// TODO Auto-generated method stub
			
		}
		public void setlname() {
			// TODO Auto-generated method stub
			
		}
		public void setemail() {
			// TODO Auto-generated method stub
			
		}
		public void setpassword() {
			// TODO Auto-generated method stub
			
		}
		public boolean getStatus() {
			// TODO Auto-generated method stub
			return false;
		}
		public void setStatus(boolean b) {
			// TODO Auto-generated method stub
			
		}


	}



