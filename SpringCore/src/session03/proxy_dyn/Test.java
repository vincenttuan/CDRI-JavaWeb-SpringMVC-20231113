package session03.proxy_dyn;

import session02.proxy.Man;
import session02.proxy.Person;
import session02.proxy.Woman;

public class Test {

	public static void main(String[] args) {
		// 透過動態代理來執行
		Calc calc = (Calc)new DynProxy(new CalcImpl()).getProxy();
		System.out.println(calc.add(20, 10));
		System.out.println(calc.div(20, 10));
		//System.out.println(calc.div(20, 0));
		
		Person woman = (Person)new DynProxy(new Woman()).getProxy();
		woman.work();
		Person man = (Person)new DynProxy(new Man()).getProxy();
		man.work();
	}

}
