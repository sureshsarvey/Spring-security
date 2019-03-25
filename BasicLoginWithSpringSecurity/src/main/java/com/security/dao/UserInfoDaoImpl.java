package com.security.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.model.UserInfo;

@Repository
@Transactional
public class UserInfoDaoImpl implements UserInfoDao {
	@Autowired
	private SessionFactory sessionFactory;

	public UserInfo getUser(String username) {
		 UserInfo userInfo = new UserInfo();
		 List<?> list = sessionFactory.getCurrentSession().createQuery("from UserInfo u where u.userName = ?")
		 .setParameter(1, username).getResultList();
		 if(!list.isEmpty())
		 {
			  userInfo = (UserInfo) list.get(0);
		 }
		 
		// TODO Auto-generated method stub
		return userInfo;
	}


}
