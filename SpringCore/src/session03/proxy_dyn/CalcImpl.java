package session03.proxy_dyn;

public class CalcImpl implements Calc {

	@Override
	public int add(int x, int y) {
		// 業務邏輯
		return x + y;
	}

	@Override
	public int div(int x, int y) {
		// 業務邏輯
		return x / y;
	}

}
