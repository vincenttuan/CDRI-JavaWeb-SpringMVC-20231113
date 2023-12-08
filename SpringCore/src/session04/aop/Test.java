package session04.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//執行時要加入: JVM 啟動參數
//--add-opens java.base/java.lang=ALL-UNNAMED
//允許 Java 9 以上版本使用反射(java.lang.reflect)內部 API
public class Test {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-aop.xml");
		Calc calc = ctx.getBean("calcImpl", CalcImpl.class);
		System.out.println(calc.add(20, 10));
		System.out.println(calc.div(50, 20));
		
	}

}
