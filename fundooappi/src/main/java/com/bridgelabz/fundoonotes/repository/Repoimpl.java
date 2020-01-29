package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bridgelabz.fundoonotes.model.User;

@Repository
public class Repoimpl implements Repo {

	@Autowired
	private EntityManager entityManager;
    @Override
	public List<User> getAllUser() {
		Session session = entityManager.unwrap(Session.class);
		Query<User> query = session.createQuery("from User", User.class);
		List<User> userList = query.getResultList();
		return userList;
	}
    @Override
	public User saveUser(User user) {
		int status = 0;
		Session session = entityManager.unwrap(Session.class);
		status = (int) session.save(user);
		if (status != 0) {
			return user;
		}
		return null;
	}
@Override
public User getUserById(Integer id) {
		List<User> userList = getAllUser();
		User user = null;
		for (User userObj : userList) {
			if (userObj.getUserId().equals(id)) {
				user = userObj;

			}
		}
		return user;
	}	
    /*(@Override
	public User delete(Integer id) {
		Session session = entityManager.unwrap(Session.class);
		User user = session.get(User.class, id);
		session.delete(user);
		return user;
	}

	/*public User update(Integer id)
	{
		Session session = entityManager.unwrap(Session.class);
		User user1 = session.get(User.class,id);
//		user1.setfname();
//		user1.setlname();
//		user1.setemail();
//		user1.setpassword();
//	
		session.update(user1);
		return user1 ;
	
	}*/

	@Override
	public boolean isVarified(User user) {
		List<User> userList =getAllUser();
		for(User userObj : userList) {
			if(userObj.getStatus()) {
				return true;
			}

		}
		return false;

		
		
	}

	
	@Override
	public User update(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}