package session04.aop;

import org.springframework.stereotype.Component;

@Component // 預設 @Component("calcImpl")
public class CalcImpl implements Calc {

	@Override
	public Integer add(Integer x, Integer y) {
		return x + y;
	}

	@Override
	public Integer div(Integer x, Integer y) {
		return x / y;
	}
	
}
