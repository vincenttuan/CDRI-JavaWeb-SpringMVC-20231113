package session01.bean;

import java.util.Random;

public class Lotto {
	private Integer number;
	
	public Lotto() {
		System.out.println("Lotto 建構子");
		Random random = new Random();
		number = random.nextInt(100); // 0~99
	}
	
	public Integer getNumber() {
		return number;
	}
	
}
