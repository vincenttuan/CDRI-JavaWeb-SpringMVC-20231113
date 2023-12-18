package session01.test;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Person {
	
	private final String name; // 外部變數
	private final int age;
	private final String email;

}
