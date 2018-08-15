package lair.ortega.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lair.ortega.exception.ValidationException;
import lair.ortega.exception.ValidationException;
import lair.ortega.logic.UserLogic;
import lair.ortega.model.response.UserResponse;

@RestController
@RequestMapping(value = "/api/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UsersController {
	
	@Autowired
	public UserLogic userLogic;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserResponse>> list() {
		List<UserResponse> list = userLogic.list(); 
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserResponse> find(@PathVariable Integer id) throws ValidationException {
		UserResponse responseUser = null;
		responseUser = userLogic.find(id);
		return new ResponseEntity<>(responseUser, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserResponse> add(@RequestBody UserResponse user) throws ValidationException {
		userLogic.save(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<UserResponse> update(@RequestBody UserResponse user) throws ValidationException {
		userLogic.update(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable Integer id) throws ValidationException {
		userLogic.delete(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<UserResponse> login(@RequestBody UserResponse user) {
		UserResponse responseUser = null;
		try {
			responseUser = userLogic.login(user);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(responseUser, HttpStatus.OK);
	}
}
