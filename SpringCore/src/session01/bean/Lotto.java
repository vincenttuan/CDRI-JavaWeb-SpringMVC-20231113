package session01.bean;

import java.util.Random;

public class Lotto {
	private Integer number;
	
	public Lotto() {
		Random random = new Random();
		number = random.nextInt(100); // 0~99
	}
	
	public Integer getNumber() {
		return number;
	}
	
}
