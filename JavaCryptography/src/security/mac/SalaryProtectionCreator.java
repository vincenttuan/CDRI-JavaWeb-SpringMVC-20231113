package security.mac;

import javax.crypto.SecretKey;

import security.KeyUtil;

/*
 * 情境描述：
 * 在一家大型企業，HR部門每月都會發送電子薪資明細給員工。
 * 為了確保薪資明細的安全性，HR部門決定對薪資明細檔案進行雜湊和 MAC 保護。
 * 雜湊保護確保薪資明細的完整性，而MAC則確保薪資明細確實來自HR部門，並未被其他部門或外部攻擊者更改。
 * 薪資檔案位置   : src/security/mac/my_salary.txt
 * macKey檔案位置: src/security/mac/macKey.key
 * */
public class SalaryProtectionCreator {
	public static void main(String[] args) throws Exception {
		// HR:
		String filePath = "src/security/mac/my_salary.txt";
		String keyPath = "src/security/mac/macKey.key";
		
		System.out.println("HR 生成:");
		System.out.println("真對薪資檔案位置:src/security/mac/my_salary.txt 生成 macValue(Hex)");
		
		// 針對 filePath 檔案內容生成雜湊
		String fileHash = KeyUtil.generateFileHash(filePath);
		System.out.println("File Hash(my_salary.txt):" + fileHash);
		
		// 生成 MAC Key
		SecretKey macKey = KeyUtil.generateKeyForHmac();
		// 儲存 MAC Key
		KeyUtil.saveSecretKeyToFile(macKey, keyPath);
		
		// 得到 macValue(Hex)
		String macValueHex = KeyUtil.generateMac("HmacSHA256", macKey, filePath);
		System.out.println("macValue(Hex):" + macValueHex);
		
		System.out.println("-------------------------------------------------------");
		// 員工會得到
		System.out.println("員工會得到:");
		System.out.println("薪資檔案位置:" + filePath);
		System.out.println("薪資macValue(Hex):" + macValueHex);
		
		
	}
}
