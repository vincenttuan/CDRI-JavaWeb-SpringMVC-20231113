package spring.mvc.group_buy.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class KeyService {
	
	/**
     * 提供從 context.xml 或 web.xml 中獲取金鑰的功能。
     * 
     * 從 context.xml 的 <Environment name="secretKey"> 中取得金鑰。
     * 或 web.xml 的 <env-entry> 中取得金鑰。
     *
     * @return 從配置中獲取的金鑰對應的 String
     * @throws RuntimeException 當無法從上下文中獲取JWT的金鑰時拋出
     */
    public static String getSecretKeyStringFromContext() {
        try {
            // 1. 建立InitialContext物件，它代表JNDI查找的起始點。
            Context initialContext = new InitialContext();
            
            // 2. 對"java:/comp/env"進行查找。此JNDI上下文用於Java EE中查找應用程序的環境條目和資源引用。
            Context envContext = (Context) initialContext.lookup("java:/comp/env");
            
            // 3. 取得 context.xml 或 web.xml中名為 "secretKey" 的資源的值。
            // 對應到 web.xml：<env-entry-name>secretKey</env-entry-name>
            //       context.xml：<Environment name="secretKey" ...
            String secretKeyBase64 = (String) envContext.lookup("secretKey");

            return secretKeyBase64;
        } catch (NamingException e) {
            throw new RuntimeException("從上下文中獲取JWT金鑰時出錯", e);
        }
    }
}
