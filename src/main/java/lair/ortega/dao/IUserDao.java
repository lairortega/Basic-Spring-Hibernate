package lair.ortega.dao;

import lair.ortega.model.db.User;

public interface IUserDao extends IGenericDao<User, Integer> {
	public User login(String username, String password);
}
