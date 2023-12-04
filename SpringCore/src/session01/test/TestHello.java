package session01.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import session01.bean.Hello;
import session01.bean.Lotto;
import session01.config.JavaSpringConfig;
import session01.config.JavaSpringConfig2;

//執行時請加入 VM 參數: --add-opens java.base/java.lang=ALL-UNNAMED
//整體來說，這行命令的意思是：“在運行時，允許所有未命名的模組訪問 java.base 模組中的 java.lang 包”。
//這是為了解決一些框架，如 Spring，在需要訪問這些封裝的API時遇到的反射問題。
public class TestHello {

	public static void main(String[] args) {
		// 傳統: 自行管理物件
		//Hello hello = new Hello();
		//System.out.println(hello.getToday());
		
		// 利用 Spring 來管理物件
		// 使用 JavaSpringConfig(Java 配置) 來取得 bean 物件
		// 1. 得到應用程式的配置環境
		//ApplicationContext ctx1 = new AnnotationConfigApplicationContext(JavaSpringConfig.class);
		//ApplicationContext ctx2 = new AnnotationConfigApplicationContext(JavaSpringConfig2.class);
		//ApplicationContext ctx3 = new ClassPathXmlApplicationContext("beans-config.xml");
		ApplicationContext ctx4 = new ClassPathXmlApplicationContext("beans-config2.xml");
		
		// 2. 取 bean
		Hello hello1 = ctx4.getBean("hello", Hello.class); // bean 的名字, bean 類型
		System.out.println(hello1.getToday());
		
		Lotto lotto1 = ctx4.getBean("lotto", Lotto.class);
		System.out.println(lotto1.getNumber());
		
		Lotto lotto2 = ctx4.getBean("lotto", Lotto.class);
		System.out.println(lotto2.getNumber());
		
	}

}
