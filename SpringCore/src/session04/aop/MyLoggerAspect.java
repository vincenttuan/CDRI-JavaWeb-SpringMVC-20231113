package session04.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 切面程式
@Component // 可被 Spring 管理的物件
@Aspect    // 告訴 Spring 此為切面程式
@Order(1)  // 執行順序(愈小愈先被執行)
public class MyLoggerAspect {
	
	//前置通知 value = Pointcut(語法: Spring EL)
	@Before(value = "execution(public Integer session04.aop.CalcImpl.add(Integer, Integer))") // 指定切點
	public void beforeAdvice(JoinPoint joinPoint) { // joinPoint 連接點
		String methodName = joinPoint.getSignature().getName(); // 方法名稱
		Object[] args = joinPoint.getArgs(); // 方法參數
		System.out.printf("前置通知 Log: %s, %s%n", methodName, Arrays.toString(args));
	}
	
}
