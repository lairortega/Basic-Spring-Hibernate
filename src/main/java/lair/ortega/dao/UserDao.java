package lair.ortega.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import lair.ortega.model.db.User;

@Repository("UserDao")
public class UserDao extends GenericDao<User, Integer> implements IUserDao{

	@Override
	public User login(String username, String password) {
		Criteria c = this.createCriteria();
		c.add(
			Restrictions.eq("username", username)
		).add(
			Restrictions.eq("password", password)
		);
		return (User)c.uniqueResult();
	}

}
