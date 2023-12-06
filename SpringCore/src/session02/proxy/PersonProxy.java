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
		// before: 執行公用邏輯
		System.out.println("出門戴口罩");
		
		// 執行代理對象的業務方法
		try {
			person.work();
		} catch (Exception e) {
			// execption: 例外公用邏輯
			System.out.println(e);
			System.out.println("去買口罩, 並戴上...");
		}
		
		// end: 執行公用邏輯
		System.out.println("回家洗手脫口罩");
	}
	
}

