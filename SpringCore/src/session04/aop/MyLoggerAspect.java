package session04.aop;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 切面程式
@Component // 可被 Spring 管理的物件
@Aspect    // 告訴 Spring 此為切面程式
@Order(1)  // 執行順序(愈小愈先被執行)
public class MyLoggerAspect {
	// 切點方法
	@Pointcut(value = "execution(public Integer session04.aop.CalcImpl.*(..))")
	public void pt1() {};
	
	@Pointcut(value = "execution(public Integer session04.aop.CalcImpl.add(Integer, Integer))")
	public void pt2() {};

	@Pointcut(value = "execution(* session04.aop.CalcImpl.add(Integer, Integer))")
	public void pt3() {};

	@Pointcut(value = "execution(* session04.aop.*.*(..))")
	public void pt4() {};

	@Pointcut(value = "execution(* *(..))")
	public void pt5() {};
	
	//前置通知 value = Pointcut(語法: Spring EL)
	//@Before(value = "execution(public Integer session04.aop.CalcImpl.add(Integer, Integer))") // 指定切點
	//@Before(value = "execution(public Integer session04.aop.CalcImpl.*(..))") // 指定切點
	//@Before(value = "pt1()")
	@Before(value = "pt1() && !pt2()") // &&, ||, !
	public void beforeAdvice(JoinPoint joinPoint) { // joinPoint 連接點
		String methodName = joinPoint.getSignature().getName(); // 方法名稱
		Object[] args = joinPoint.getArgs(); // 方法參數
		System.out.printf("前置通知 Log: %s, %s%n", methodName, Arrays.toString(args));
	}
	
	// 後置通知(不論是否發生異常都會執行)
	@After(value = "pt1()")
	public void afterAdvice(JoinPoint joinPoint) { // joinPoint 連接點 
		String methodName = joinPoint.getSignature().getName(); // 方法名稱
		System.out.printf("後置通知 Log: %s %s%n", methodName, new Date());
	}
	
	
	
}
