package session03.proxy_dyn;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

// 紀錄下達的方法參數
// Aspect
public class MyLoggerAspect {
	
	// 前置通知 (Advice)
	public static void before(Object object, Method method, Object[] args) {
		String path = "src/session03/proxy_dyn/log.txt";
		String content = Arrays.toString(args);
		try {
			Files.write(
					Paths.get(path), // 存檔路徑
					content.getBytes(StandardCharsets.UTF_8), // 轉 byte[] 
					StandardOpenOption.CREATE, // 若文件不存在則自動建立檔案
					StandardOpenOption.APPEND  // 在文件末尾添加資料
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
