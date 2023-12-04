package session01.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import session01.bean.Hello;
import session01.config.JavaSpringConfig;

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
		ApplicationContext ctx1 = new AnnotationConfigApplicationContext(JavaSpringConfig.class);
		// 2. 取 bean
		Hello hello1 = ctx1.getBean("hello", Hello.class); // bean 的名字, bean 類型
		System.out.println(hello1.getToday());
		
	}

}
