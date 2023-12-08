package session04.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//切面程式
@Component // 可被 Spring 管理的物件
@Aspect    // 告訴 Spring 此為切面程式
@Order(2)  // 執行順序(愈小愈先被執行)
public class MyLogger2Aspect {
	// 切點方法
	@Pointcut(value = "execution(public Integer session04.aop.CalcImpl.*(..))")
	public void pt1() {};
	
	// 環繞通知
	@Around(value = "pt1()")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		// 建立返回變數值
		Object result = null;
		try {
			// 1. 前置通知
			System.out.println("環繞通知-前置通知");
			
			// 2. 代理執行業務方法 (重要)
			result = proceedingJoinPoint.proceed();
			
			// 3. 返回通知
			System.out.println("環繞通知-返回通知(變更前), result: " + result);
			// 變更返回內容, 若結果小於 0 則以 0 回傳
			if(Integer.parseInt(result+"") < 0) {
				result = Integer.valueOf(0);
			}
			System.out.println("環繞通知-返回通知(變更前), result: " + result);
			
		} catch (Throwable e) {
			// 4. 例外通知
			System.out.println("環繞通知-例外通知, " + e);
		} finally {
			// 5. 後置通知
			System.out.println("環繞通知-後置通知");
		}
		
		return result;
	}
	
}
