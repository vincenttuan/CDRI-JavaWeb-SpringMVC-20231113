package session01.bean;

import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// 可被 Spring 掃描並管理
@Component
@Scope("prototype")
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
