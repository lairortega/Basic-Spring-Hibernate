package lair.ortega.web.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lair.ortega.dao.IUserDao;
import lair.ortega.model.response.User;

@RestController
@RequestMapping(value = "/api/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UsersController {

	@Autowired
	@Qualifier("UserDao")
	private IUserDao dao;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<User>list() {
		List<User> responseUser = new ArrayList<>();
		responseUser = dao.findAll()
			.stream()
			.map(U -> new User(U))
			.collect(
				Collectors.toList()
			);
		return responseUser;
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public User login(@RequestBody User user) {
		User responseUser = new User(
			dao.login(user.getUsername(), user.getPassword())
		);
		return responseUser;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public User add(@RequestBody User user) {
		Integer registered = dao.save(user.toDB());
		User responseUser = new User();
		responseUser.setId(registered);
		return responseUser;
	}
}
