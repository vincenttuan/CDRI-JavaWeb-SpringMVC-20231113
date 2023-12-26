package security.hash;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

import security.KeyUtil;

public class SimpleAddSaltHash {

	public static void main(String[] args) throws Exception {
		// Hot Code
		String password = "1234";
		
		// 隨機生成一個鹽(salt)
		byte[] salt = new byte[16];
		System.out.println("鹽(填充前):" + Arrays.toString(salt));
		SecureRandom random = new SecureRandom();
		random.nextBytes(salt);
		System.out.println("鹽(填充後):" + Arrays.toString(salt));
		System.out.println("鹽(填充後 Hex):" + KeyUtil.bytesToHex(salt)); // 轉 16 進位
		
		// 1. 獲取 SHA-256 消息摘要物件, 來生成密碼的哈希值
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		// 2. 加鹽
		messageDigest.update(salt);
		// 3. 將密碼轉成 byte[] 後生成哈希
		byte[] hashedBytes = messageDigest.digest(password.getBytes()); // 哈希
		System.out.println("密碼 + 鹽(哈希):" + Arrays.toString(hashedBytes));
		// 4. 將 hashedBytes 轉 Hex (呈現/儲存用)
		System.out.println("密碼 + 鹽(哈希 Hex):" + KeyUtil.bytesToHex(hashedBytes));
		
		
	}

}
