package session02.proxy;

public class Test {

	public static void main(String[] args) {
		Person man = new PersonProxy(new Man());
		man.work();
		
		Person woman = new PersonProxy(new Woman());
		woman.work();
	}

}
