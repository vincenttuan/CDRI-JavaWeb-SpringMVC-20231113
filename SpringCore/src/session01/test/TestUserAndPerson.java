package session01.test;

public class TestUserAndPerson {

	public static void main(String[] args) {
		User user1 = new User("John", 18, "John@gmail.com");
		
		User user2 = new User.Builder()
				.name("John")
				.age(18)
				.email("John@gmail.com")
				.build();
		
		User user3 = new User.Builder()
				.name("John")
				.email("John@gmail.com")
				.build();
		
		User user4 = new User.Builder()
				.email("John@gmail.com")
				.build();
		
		Person person = Person.builder()
				.name("John").age(18).email("John@g,ail.com")
				.build();
		
	}

}
