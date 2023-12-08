package mvc.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {
	
	@Pointcut(value = "execution(* mvc.controller.BookingController.bookingBookRoom(..))")
	public void ptBookingBookRoom() {}
	
	@Pointcut(value = "execution(* mvc.controller.BookingController.cancelBooking(..))")
	public void ptCancelBooking() {}
	
	@Before(value = "ptBookingBookRoom() || ptCancelBooking()")
	public void beforeAdvice(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		System.out.printf("methodName: %s args: %s%n", methodName, Arrays.toString(args));
	}
	
	
}
