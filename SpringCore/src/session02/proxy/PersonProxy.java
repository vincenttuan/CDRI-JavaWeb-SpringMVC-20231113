package session02.proxy;

// 靜態代理
// 代理物件就是執行代理任務
public class PersonProxy implements Person {
	// 代理對象
	private Person person;
	
	// 透過建構子注入代理對象
	public PersonProxy(Person person) {
		this.person = person; 
	}
	
	@Override
	public void work() {
		// before: 執行公用方法
		System.out.println("出門戴口罩");
		
		// 執行代理對象的業務方法
		person.work();
		
		// end: 執行公用方法
		System.out.println("回家洗手脫口罩");
	}
	
}

