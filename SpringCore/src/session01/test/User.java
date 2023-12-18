package session01.test;

public class User {
	
	private final String name; // 外部變數
	private final int age;
	private final String email;
	
	public User(String name, int age, String email) {
		this.name = name;
		this.age = age;
		this.email = email;
	}

	public User(Builder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.email = builder.email;
	}
	
	public static class Builder {
		private String name; // 內部變數
		private int age;
		private String email;
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder age(int age) {
			this.age = age;
			return this;
		}
		
		public Builder email(String email) {
			this.email = email;
			return this;
		}
		
		public User build() {
			return new User(this);
		}
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}
	
	
}
