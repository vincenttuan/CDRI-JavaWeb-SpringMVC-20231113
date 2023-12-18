package session01.test;

public class TestUser {

	public static void main(String[] args) {
		User user = new User.Builder()
				.name("John")
				.age(18)
				.email("John@gmail.com")
				.build();
		
		
	}

}
