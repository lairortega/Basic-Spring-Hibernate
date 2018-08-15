package lair.ortega.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import lair.ortega.model.db.UserEntity;

@Repository("userDao")
@Transactional
public class UserDao extends AbstractDao<Integer, UserEntity> {
	
	public Integer save(UserEntity entity) {
		return super.save(entity);
	}
	
	public List<UserEntity> findAll(){
		return super.findAll(null);
	}
	public void update(UserEntity entity) {
		super.merge(entity);
	}
	public UserEntity findById(Integer id) {
		return super.findById(id);
	}
	public void delete(UserEntity entity) {
		super.delete(entity);
	}
	
	public UserEntity login(String username, String password) {
		Criteria c = super.createEntityCriteria();
		
		c.add(
			Restrictions.eq("username", username)
		).add(
			Restrictions.eq("password", password)
		);//.setMaxResults(1);
		return super.find(c);
	}
}
