package lair.ortega.model.response;

public class User {
	
	private Integer id;
	private String username;
	private String password;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User() {}
	
	public User(lair.ortega.model.db.User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
	}
	
	public lair.ortega.model.db.User toDB() {
		lair.ortega.model.db.User user = new lair.ortega.model.db.User();
		
		user.setId(this.getId());
		user.setUsername(this.getUsername());
		user.setPassword(this.getPassword());
		
		return user;
	}
}
