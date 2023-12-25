package security.keys;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import security.KeyUtil;

public class RSASample {

	public static void main(String[] args) throws Exception {
		// 1. 生成 RSA 密鑰對(公鑰/私鑰)
		KeyPair keyPair = KeyUtil.generateRSAKeyPair(); // RSA-2048
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		
		String originalMessage = "聖誕快樂 X'mas";
		System.out.println("1. 原始訊息:" + originalMessage);
		
		// 2. 利用公鑰進行加密
		byte[] encryptedBytes = KeyUtil.encryptWithPublicKey(publicKey, originalMessage.getBytes());
		
		// 3. 編碼轉 base64
		String encryptedBytesBase64 = Base64.getEncoder().encodeToString(encryptedBytes);
		System.out.println("2. 加密後的訊息(base64編碼):" + encryptedBytesBase64);
		
		System.out.println("網路上傳遞的訊息: " + encryptedBytesBase64);
		// 4. 利用私鑰進行解密
		System.out.println("解密中...");
		Thread.sleep(3000);
		// 解密程序:
		// 將 encryptedBytesBase64 進行 base64 解碼
		// 將解碼後的資料 透過 decryptWithPrivateKey 方法 + privateKey私鑰 進行解密
		byte[] decryptedBytes = KeyUtil.decryptWithPrivateKey(privateKey, Base64.getDecoder().decode(encryptedBytesBase64.getBytes()));
		String decryptedString = new String(decryptedBytes); // 將 byte[] 轉 String
		System.out.println("3. 解密後的訊息:" + decryptedString);
		
	}

}
