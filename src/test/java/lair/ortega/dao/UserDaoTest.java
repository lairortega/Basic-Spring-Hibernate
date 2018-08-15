package lair.ortega.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lair.ortega.model.db.UserEntity;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=lair.ortega.ApiPeliculaApplication.class)
public class UserDaoTest {
	
	@Autowired
	private UserDao dao;
	
	private void clearDatabase() {
//		for(UserEntity user : dao.findAll()) {
//			dao.delete(user);
//		}
	}
	private UserEntity populateDataBase() {
		UserEntity entity = new UserEntity();
		entity.setPassword(UUID.randomUUID().toString());
		entity.setUsername(UUID.randomUUID().toString());
		
		dao.save(entity);
		return entity;
	}

	@Test
	@Order(value=0)
	public void testSaveUser() {
		UserEntity u = new UserEntity();
		
		u.setUsername("lair");
		u.setPassword("12");
		
		Integer id = dao.save(u);
		assertNotNull(id);
		assertTrue( id > 0 );
		
		this.clearDatabase();
	}
	
	@Test
	@Order(value=1)
	public void testListUsers() {
		
		for(int a = 0; a < 3; a++) {
			this.populateDataBase();
		}
		
		List<UserEntity> users = dao.findAll();
		assertNotNull(users);
		for(UserEntity user : users) {
			assertNotNull(user.getId());
			
			assertNotEquals("", user.getUsername());
			assertNotNull(user.getUsername());
			
			assertNotEquals("", user.getPassword());
			assertNotNull(user.getPassword());
		}
		
		this.clearDatabase();
		
	}
	
	@Test
	@Order(value=2)
	public void testGetOneUser() {
		UserEntity u = this.populateDataBase();
		
		UserEntity user = dao.findById(u.getId());
		assertNotNull(user);
		
		assertNotNull(user.getId());
		
		assertNotEquals("", user.getUsername());
		assertNotNull(user.getUsername());
		
		assertNotEquals("", user.getPassword());
		assertNotNull(user.getPassword());
		
		this.clearDatabase();
	}
	
	@Test
	@Order(value=4)
	public void testUpdateUser() {
		String newPassword = "123";
		String newUserName = "Lair";
		// Crea nueco usuario
		UserEntity u = this.populateDataBase();
		
		Integer id = u.getId();
		// Busca nuevo usuaro
		u = null;
		u = dao.findById(id);
		assertNotNull(u);
		assertTrue( u.getId() > 0 );
		
		u.setPassword(newPassword);
		u.setUsername(newUserName);
		
		dao.update(u);
		
		u = null;
		
		u = dao.findById(id);

		assertEquals(newPassword, u.getPassword());
		assertEquals(newUserName, u.getUsername());
		
		this.clearDatabase();
	}
	
	@Test
	@Order(value=5)
	public void testDeleteUser() {
		UserEntity u = this.populateDataBase();

		Integer id = u.getId();
		assertNotNull(id);
		assertTrue( id > 0 );
		
		UserEntity user = dao.findById(id);
		assertNotNull(user);
		dao.delete(user);
		user = dao.findById(id);
		assertNull(user);
		
		this.clearDatabase();
	}

	@Test
	@Order(value=6)
	public void testLoginStrings() {
		UserEntity u = this.populateDataBase();
		
		String username = u.getUsername();
		String password = u.getPassword();
		
		u = null;
		
		u = dao.login(username, password);
		assertNotNull(u);
		
		assertTrue( u.getId() > 0 );
		
		assertEquals(username, u.getUsername());
		assertEquals(password, u.getPassword());
		
		this.clearDatabase();
	}
	
}
