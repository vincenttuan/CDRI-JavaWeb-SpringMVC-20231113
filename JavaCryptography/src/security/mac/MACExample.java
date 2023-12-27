package security.mac;

import java.util.Arrays;

import javax.crypto.SecretKey;

import security.KeyUtil;

// MAC 訊息驗證碼範例
public class MACExample {

	public static void main(String[] args) throws Exception {
		// 1. 定義要加上 MAC 的訊息
		String message = "2024 新年快樂...";
		
		// 2. 產生一把專用於 Hmac 的密鑰
		SecretKey macKey = KeyUtil.generateKeyForHmac();
		
		// 3. 利用此密鑰(macKey)和訊息(message)生成 MAC 值
		byte[] macValue = KeyUtil.generateMac("HmacSHA256", macKey, message.getBytes());
		
		// 4. 將 MAC 印出
		System.out.println("MAC(Hex):" + KeyUtil.bytesToHex(macValue));
		//----------------------------------------------------------------------------------------
		// 5. 在實際應用中, 接收方會收到訊息(message)與 macValue
		//    此時 message 要與 macKey(雙方統一都要有的密鑰) 產生出 computedMacValue 值進行與 macValue 的比對
		byte[] computedMacValue = KeyUtil.generateMac("HmacSHA256", macKey, message.getBytes());
		
		// 6. 比較是否相同
		if(Arrays.equals(macValue, computedMacValue)) {
			System.out.println("MAC 驗證成功, 來源正確, 訊息:" + message);
		} else {
			System.out.println("MAC 驗證失敗, 來源不正確");
		}
	}

}
