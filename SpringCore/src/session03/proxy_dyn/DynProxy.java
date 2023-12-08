package session03.proxy_dyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

// 動態代理
public class DynProxy {
	// 被代理的對象
	private Object object;
	
	public DynProxy(Object object) { // 注入被代理的對象
		this.object = object;
	}
	
	// 取得被代理的物件
	public Object getProxy() {
		Object proxyObj = null;
		// 1. 載入類別器
		ClassLoader loader = object.getClass().getClassLoader();
		// 2. 被代理的物件所實作的介面
		Class<?>[] interfaces = object.getClass().getInterfaces();
		// 3. 處理代理的實現
		InvocationHandler handler = (Object proxy, Method method, Object[] args) -> {
			Object result = null;
			
			// 公用邏輯 1 begin
			
			// 公用邏輯 2 begin
			System.out.printf("寫入到檔案中...%n");
			// 公用邏輯 n begin
			
			// 調用被代理物件的業務方法
			result = method.invoke(object, args);
			
			// 公用邏輯 1 end
			
			// 公用邏輯 2 end
			
			// 公用邏輯 n end
			
			
			//System.out.println("方法執行後的邏輯...");
			return result;
		};
		
		// 4. 得到代理物件
		proxyObj = Proxy.newProxyInstance(loader, interfaces, handler);
		return proxyObj;
	}
	
}





