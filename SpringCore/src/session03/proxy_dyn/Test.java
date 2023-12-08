package session03.proxy_dyn;

public class Test {

	public static void main(String[] args) {
		// 透過動態代理來執行
		Calc calc = (Calc)new DynProxy(new CalcImpl()).getProxy();
		System.out.println(calc.add(20, 10));
		System.out.println(calc.div(20, 10));

	}

}
