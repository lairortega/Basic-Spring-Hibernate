package lair.ortega.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

import lair.ortega.exception.ValidationException;
import lair.ortega.model.response.UserResponse;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=lair.ortega.ApiPeliculaApplication.class)
public class UserLogicTest {
	
	@Autowired
	private UserLogic logic;
	
	private void clearDatabase() {
//		for(UserResponse user : logic.list()) {
//			try {
//				logic.delete(user.getId());
//			} catch (ValidationException e) {
//				e.printStackTrace();
//			}
//		}
	}
	private UserResponse populateDataBase() {
		UserResponse entity = new UserResponse();
		entity.setPassword(UUID.randomUUID().toString());
		entity.setUsername(UUID.randomUUID().toString());
		try {
			logic.save(entity);
			return entity;
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Test
	@Order(value = 0)
	public void testSaveUser() {
		UserResponse entity = new UserResponse();
		try {
			entity.setPassword("123");
			entity.setUsername("ABC");
			logic.save(entity);
			assertTrue(entity.getId() > 0);
			
			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como fallida
			 */
			assertTrue(false);
		}
	}
	
	@Test
	@Order(value = 1)
	public void testListUser() {
		for(int a = 0; a < 3; a++) {
			this.populateDataBase();
		}
		
		List<UserResponse> users = logic.list();
		assertNotNull(users);
		assertFalse(users.isEmpty());

		this.clearDatabase();
	}
	
	@Test
	@Order(value = 2)
	public void testGetOneUser() {
		
		UserResponse user = null;
		UserResponse entity = new UserResponse();
		try {
			entity = this.populateDataBase();
			
			user = logic.find(entity.getId());
			assertNotNull(user);
			
			assertNotNull(user.getId());
			
			assertNotEquals("", user.getUsername());
			assertNotNull(user.getUsername());
			
			assertNotEquals("", user.getPassword());
			assertNotNull(user.getPassword());

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como fallida
			 */
			assertTrue(false);
		}
	}
	
	@Test
	@Order(value = 3)
	public void testGetNonExistingUser() {
		UserResponse user = null;
		try {
			user = logic.find(-1);
			assertNull(user);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como exitosa
			 */
			assertTrue(true);
		}
	}
	
	@Test
	@Order(value = 4)
	public void testUpdateUser() {
		UserResponse user = null;
		UserResponse entity = new UserResponse();
		try {
			entity = this.populateDataBase();
			
			user = logic.find(entity.getId());
			String newPassword = UUID.randomUUID().toString();
			user.setPassword(newPassword);
			logic.update(user);
			
			user = null;

			user = logic.find(entity.getId());
			assertEquals(newPassword, user.getPassword());

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como fallida
			 */
			assertTrue(false);
		}
	}
	
	@Test
	@Order(value = 5)
	public void testUpdateNullUser() {
		UserResponse user = null;
		try {
			logic.update(user);
			/*
			 * Si ejecuta todas las instrucciones
			 * la prueba falló,algo salió mal
			 */
			assertTrue(false);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como existosa
			 */
			assertTrue(true);
		}
	}
	
	@Test
	@Order(value = 5)
	public void testUpdateEmptyDataUser() {
		UserResponse user = null;
		UserResponse entity = new UserResponse();
		try {
			entity = this.populateDataBase();
			
			user = logic.find(entity.getId());
			
			user.setUsername("");
			user.setPassword("");
			logic.update(user);
			/*
			 * Si ejecuta todas las instrucciones
			 * la prueba falló,algo salió mal
			 */
			assertTrue(false);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como existosa
			 */
			assertTrue(true);
		}
	}
	
	@Test
	@Order(value = 6)
	public void testUpdateEmptyUsername() {
		UserResponse user = null;
		UserResponse entity = new UserResponse();
		try {
			entity = this.populateDataBase();
			
			user = logic.find(entity.getId());
			user.setUsername("");
			logic.update(user);
			/*
			 * Si ejecuta todas las instrucciones
			 * la prueba falló,algo salió mal
			 */
			assertTrue(false);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como existosa
			 */
			assertTrue(true);
		}
	}
	
	@Test
	@Order(value = 7)
	public void testUpdateEmptypassword() {
		UserResponse user = null;
		UserResponse entity = new UserResponse();
		try {
			entity = this.populateDataBase();
			user = logic.find(entity.getId());
			user.setPassword("");
			logic.update(user);
			/*
			 * Si ejecuta todas las instrucciones
			 * la prueba falló,algo salió mal
			 */
			assertTrue(false);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como existosa
			 */
			assertTrue(true);
		}
	}
	
	@Test
	@Order(value = 8)
	public void testUpdateNullDataUser() {
		UserResponse user = null;
		UserResponse entity = new UserResponse();
		try {
			entity = this.populateDataBase();
			user = logic.find(entity.getId());
			user.setUsername(null);
			user.setPassword(null);
			logic.update(user);
			/*
			 * Si ejecuta todas las instrucciones
			 * la prueba falló,algo salió mal
			 */
			assertTrue(false);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como existosa
			 */
			assertTrue(true);
		}
	}
	
	@Test
	@Order(value = 9)
	public void testUpdateNullUsername() {
		UserResponse user = null;
		UserResponse entity = new UserResponse();
		try {
			entity = this.populateDataBase();
			user = logic.find(entity.getId());
			user.setUsername(null);
			logic.update(user);
			/*
			 * Si ejecuta todas las instrucciones
			 * la prueba falló,algo salió mal
			 */
			assertTrue(false);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como existosa
			 */
			assertTrue(true);
		}
	}
	
	@Test
	@Order(value = 10)
	public void testUpdateNullPassword() {
		UserResponse user = null;
		UserResponse entity = new UserResponse();
		try {
			entity = this.populateDataBase();
			user = logic.find(entity.getId());
			user.setPassword(null);
			logic.update(user);
			/*
			 * Si ejecuta todas las instrucciones
			 * la prueba falló,algo salió mal
			 */
			assertTrue(false);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como existosa
			 */
			assertTrue(true);
		}
	}
	
	@Test
	@Order(value = 11)
	public void testDeleteNoneExistingUser() {
		UserResponse user = null;
		try {
			user = logic.find(-1);
			user.setPassword(null);
			logic.update(user);
			/*
			 * Si ejecuta todas las instrucciones
			 * la prueba falló,algo salió mal
			 */
			assertTrue(false);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como existosa
			 */
			assertTrue(true);
		}
	}
	
	@Test
	@Order(value = 12)
	public void testLoginOkStrings() {
		UserResponse user = null;
		UserResponse entity = new UserResponse();
		try {
			entity = this.populateDataBase();
			user = logic.login(entity.getUsername(), entity.getPassword());
			assertNotNull(user);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como fallida
			 */
			assertTrue(false);
		}
	}
	
	@Test
	@Order(value = 13)
	public void testLoginOkObject() {
		UserResponse u = new UserResponse();
		UserResponse user = null;
		UserResponse entity = new UserResponse();
		try {
			entity = this.populateDataBase();
			u.setPassword(entity.getPassword());
			u.setUsername(entity.getUsername());
			user = logic.login(u);
			assertNotNull(user);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como fallida
			 */
			assertTrue(false);
		}
	}
	
	@Test
	@Order(value = 14)
	public void testLoginErrorObject() {
		UserResponse u = new UserResponse();
		UserResponse user = null;
		UserResponse entity = new UserResponse();
		try {
			entity = this.populateDataBase();
			u.setPassword(entity.getUsername());
			u.setUsername("NoPassword");
			user = logic.login(u);
			assertNull(user);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como fallida
			 */
			assertTrue(false);
		}
	}
	
	@Test
	@Order(value = 15)
	public void testLoginErrorStrings() {
		UserResponse user = null;
		UserResponse entity = new UserResponse();
		try {
			entity = this.populateDataBase();
			user = logic.login(entity.getUsername(), "NoPassword");
			assertNull(user);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como fallida
			 */
			assertTrue(false);
		}
	}
	
	@Test
	@Order(value = 16)
	public void testLoginErrorEmptyStrings() {
		UserResponse user = null;
		try {
			user = logic.login("", "");
			assertNull(user);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como fallida
			 */
			assertTrue(true);
		}
	}
	
	@Test
	@Order(value = 17)
	public void testLoginErrorEmptyUsername() {
		UserResponse user = null;
		try {
			user = logic.login("", "NoPassword");
			assertNull(user);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como fallida
			 */
			assertTrue(true);
		}
	}
	
	@Test
	@Order(value = 18)
	public void testLoginErrorEmptyPassword() {
		UserResponse user = null;
		UserResponse entity = new UserResponse();
		try {
			entity = this.populateDataBase();
			user = logic.login(entity.getUsername(), "");
			assertNull(user);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como fallida
			 */
			assertTrue(true);
		}
	}
	
	@Test
	@Order(value = 19)
	public void testLoginErrorNullStrings() {
		UserResponse user = null;
		try {
			user = logic.login(null, null);
			assertNull(user);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como fallida
			 */
			assertTrue(true);
		}
	}
	
	@Test
	@Order(value = 20)
	public void testLoginErrorNullUsername() {
		UserResponse user = null;
		try {
			user = logic.login(null, "NoPassword");
			assertNull(user);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como fallida
			 */
			assertTrue(true);
		}
	}
	
	@Test
	@Order(value = 21)
	public void testLoginErrorNullPassword() {
		UserResponse user = null;
		UserResponse entity = new UserResponse();
		try {
			entity = this.populateDataBase();
			user = logic.login(entity.getUsername(), null);
			assertNull(user);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como fallida
			 */
			assertTrue(true);
		}
	}
	
	@Test
	@Order(value = 14)
	public void testDeleteExistingUser() {
		UserResponse entity = new UserResponse();
		try {
			entity = this.populateDataBase();
			
			Integer id = entity.getId();
			
			logic.delete(entity.getId());

			entity = null;
			entity = logic.find(id);
			
			assertNull(entity);

			this.clearDatabase();
		} catch (ValidationException e) {
			/*
			 * Si entra al catch marca la prueba como fallida
			 */
			assertTrue(true);
		}
	}
}
