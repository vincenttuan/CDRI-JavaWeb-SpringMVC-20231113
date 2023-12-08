package session03.proxy_dyn;

import java.lang.reflect.Method;
import java.util.Arrays;

// 集中公用邏輯
// Aspect 切面程式
public class MyPrintArgsAspect {
	
	// before: 前置通知(Advice)
	public static void before(Object object, Method method, Object[] args) {
		System.out.printf("before 實體類別: %s, 方法名稱: %s 方法參數: %s%n", object.getClass().getSimpleName(), method.getName(), Arrays.toString(args));
	}
	
	// exception: 例外通知(Advice)
	public static void throwing(Exception e) {
		System.out.printf("exception: %s%n", e);
	}
	
	// end: 後置通知(Advice)
	public static void end() {
		System.out.println("end ...");
	}
	
}
