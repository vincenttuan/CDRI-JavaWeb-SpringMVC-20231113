package test;

public class Test {

	public static void main(String[] args) {
		Object a = Integer.valueOf(10);
		Integer b = Integer.valueOf(10);
		System.out.println(a == b);
		System.out.println(a.equals(b));

	}

}
