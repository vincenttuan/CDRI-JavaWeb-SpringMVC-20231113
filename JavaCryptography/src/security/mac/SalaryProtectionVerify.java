package security.mac;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.crypto.SecretKey;

import security.KeyUtil;

/*
 * 員工已知或得到的資訊
 * 薪資檔案位置   : src/security/mac/my_salary.txt
 * macKey檔案位置: src/security/mac/macKey.key
 * 薪資macValue(Hex):f1fdce7103fe59fa3b03723e12310b5d9d809eb1f7da3d5eb3e61f8edafc1d62
 * */
public class SalaryProtectionVerify {
	public static void main(String[] args) throws Exception {
		
		String filePath = "src/security/mac/my_salary.txt";
		String keyPath = "src/security/mac/macKey.key";
		String macValueFromHR = "f1fdce7103fe59fa3b03723e12310b5d9d809eb1f7da3d5eb3e61f8edafc1d62";
		
		SecretKey macKey = KeyUtil.getSecretKeyFromFile("HmacSHA256", keyPath);
		// 使用相同的 macKey 對 filePath 生成 macValue
		String computedMacValueHex = KeyUtil.generateMac("HmacSHA256", macKey, filePath);
		
		// 驗證 MAC 
		if(macValueFromHR.equals(computedMacValueHex)) {
			System.out.println("MAC驗證成功 ! 薪資明細是來自於 HR");
			// 讀取檔案內容
			System.out.println(Files.readString(Paths.get(filePath)));
		} else {
			System.out.println("MAC驗證失敗 ! 來源不正確");
		}
		
	}
}
