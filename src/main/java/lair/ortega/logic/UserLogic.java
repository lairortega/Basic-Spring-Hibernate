package lair.ortega.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lair.ortega.dao.UserDao;
import lair.ortega.exception.ValidationException;
import lair.ortega.model.db.UserEntity;
import lair.ortega.model.response.UserResponse;

@Component
public class UserLogic{

	@Autowired
	private UserDao userDao;

	public List<UserResponse> list(){
		List<UserResponse> responseUser = null;
		/*
		 * Convierte la lista de: List<lair.ortega.model.db.User>
		 * a una nueva lista de: List<lair.ortega.model.response.User>
		 * */
		responseUser = userDao.findAll()
			.stream()
			.map(U -> new UserResponse(U))
			.collect(
				Collectors.toList()
			);
		return responseUser;
	}

	public UserResponse find(Integer id) throws ValidationException {
		if(id < 0) {
			throw new ValidationException("Invalid input.");
		}
		UserEntity userFound = userDao.findById(id);
		if(userFound == null) {
			return null;
		}
		return new UserResponse(userFound);
	}

	public void save(UserResponse entity) throws ValidationException {
		if(entity == null) {
			throw new ValidationException("Invalid input.");
		}
		
		if(entity.getPassword() == null || entity.getPassword().isEmpty()) {
			throw new ValidationException("Invalid input.");
		}
		if(entity.getUsername() == null || entity.getUsername().isEmpty()) {
			throw new ValidationException("Invalid input.");
		}
		Integer id = userDao.save(entity.toDB());
		
		entity.setId(id);
	}

	public void update(UserResponse entity) throws ValidationException {
		if(entity == null) {
			throw new ValidationException("Invalid input.");
		}
		
		if(entity.getId() < 0) {
			throw new ValidationException("Invalid input.");
		}
		if(entity.getPassword() == null || entity.getPassword().isEmpty()) {
			throw new ValidationException("Invalid input.");
		}
		if(entity.getUsername() == null || entity.getUsername().isEmpty()) {
			throw new ValidationException("Invalid input.");
		}
		UserEntity userFound = userDao.findById(entity.getId());
		if(userFound == null) {
			throw new ValidationException("User not found.");
		}
		userFound.setPassword(entity.getPassword());
		userFound.setUsername(entity.getUsername());
		userDao.update(userFound);
	}

	public void delete(Integer id) throws ValidationException {
		UserEntity user = userDao.findById(id);
		if(user == null) {
			throw new ValidationException("User not found");
		}
		userDao.delete(user);
	}
	
	public UserResponse login(String username, String password) throws ValidationException {
		if(password == null || password.isEmpty()) {
			throw new ValidationException("Invalid input.");
		}
		if(username == null || username.isEmpty()) {
			throw new ValidationException("Invalid input.");
		}
		UserEntity entity = userDao.login(username, password);
		if(entity == null) {
			return null;
		}
		return new UserResponse(entity);
	}
	public UserResponse login(UserResponse user) throws ValidationException {
		if(user == null) {
			throw new ValidationException("Invalid input.");
		}
		return this.login(
			user.getUsername(), 
			user.getPassword()
		);
	}
	
}
