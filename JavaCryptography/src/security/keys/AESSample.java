package security.keys;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.spec.SecretKeySpec;

import security.KeyUtil;

public class AESSample {
	
	// 建立一個 AES key(256bits, 32bytes)
	private static final String KEY = "0123456789abcdef0123456789abcdef"; // 32 個字
	
	// 建立 SecureRandom
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();
	
	public static void main(String[] args) throws Exception {
		System.out.println("AES-256 加密範例:");
		String orginalText = "Hello1234哈囉"; // 原始明文
		System.out.println("原始明文: " + orginalText);
		System.out.println("------------------------------------------------------------");
		
		// 利用 AES 進行加密
		// 1. 建立 AES 密鑰規範
		SecretKeySpec aesKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
		// 2. ECB 模式
		System.out.println("ECB 模式");
		byte[] encryptedECB = KeyUtil.encryptWithAESKey(aesKeySpec, orginalText);
		System.out.println("加密後的訊息: " + Arrays.toString(encryptedECB));
		System.out.println("加密後的訊息(Base64): " + Base64.getEncoder().encodeToString(encryptedECB));
		
		// 利用 AES 進行解密
		Scanner scanner = new Scanner(System.in);
		System.out.print("請將加密訊息放入:");
		String base64 = scanner.next();
		// 將 base64 轉 byte[]
		byte[] base64Bytes = Base64.getDecoder().decode(base64);
		// 將 base64 進行解密
		String decryptedECB = KeyUtil.decryptWithAESKey(aesKeySpec, base64Bytes);
		System.out.println("解密後的訊息: " + decryptedECB);
	}
}
