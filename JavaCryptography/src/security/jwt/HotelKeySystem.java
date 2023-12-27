package security.jwt;

import java.util.Date;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;

import security.KeyUtil;

/**
 * HotelKeySystem 模擬一家酒店的房卡管理系統。
 * 在這個情境中，當房客入住酒店時，他們會得到一張「房間卡」，這張卡具有短暫的時效性，使他們能夠在有限的時間內進入房間。
 * 然而，這些房間卡有可能過期，特別是在長時間的住宿中。
 * 當房間卡過期時，房客可以到前台，前台擁有一台 Unified「房間卡產生器」，它可以製作新的房間卡。這台機器具有較長的時效性。
 * 這種模式類似於網路安全中的 Access Token 和 Refresh Token。房間卡相當於 Access Token，而房間卡產生器則相當於 Refresh Token。
 * 
 * 程式實作主要流程：
 * 1. 生成主要的機密鑰匙 (masterKey)。
 * 2. 創建並簽署「房間卡產生器」(Refresh Token)。
 * 3. 創建並簽署「房間卡」(Access Token)。
 * 4. 驗證「房間卡」是否過期。
 * 5. 若「房間卡」過期，使用「房間卡產生器」重新簽署新的「房間卡」。
 * 6. 模擬「房間卡產生器」過期後的情況。
 */
public class HotelKeySystem {
	private static String masterKey; // 金鑰
	
	// 創建並簽署「房間卡」(Access Token)
	private static String createRoomCard(String guest, String roomNo) throws JOSEException {
		JWTClaimsSet roomCard = new JWTClaimsSet.Builder() 
				.subject(guest) // 房客的身份
				.issuer("https://hotel.com") // 飯店發行單位
				.claim("room", roomNo)
				.expirationTime(new Date(new Date().getTime() + 5000)) // 設定房卡有效時間，例如：5秒(5000ms)
				.build(); // 建立房卡
		String signedRoomCard = KeyUtil.signJWT(roomCard, masterKey); // 將房卡簽章
		return signedRoomCard;
	}
	
	// 創建並簽署「房間卡產生器」(Refresh Token)。
	private static String createRoomCardGenerator() throws JOSEException {
		JWTClaimsSet roomCardGenerator = new JWTClaimsSet.Builder()
				.subject("frontDesk") // 前台的身份
				.issuer("https://hotel.com") // 飯店發行單位
				.claim("authority", "create room card") // 可以放入一些授權的資訊
				.expirationTime(new Date(new Date().getTime() + 60_000)) // 設定房卡產生器有效時間，例如：1 分鐘
				.build(); // 建立房卡產生器
		String signedRoomCardGenerator = KeyUtil.signJWT(roomCardGenerator, masterKey); // 將房卡產生器簽章
		return signedRoomCardGenerator;
	}
	
	public static void main(String[] args) {
		 // 1. 生成主要的機密鑰匙 (masterKey)。
		
		 // 2. 創建並簽署「房間卡產生器」(Refresh Token)。
		
		 // 3. 創建並簽署「房間卡」(Access Token)。
		
		 // 4. 驗證「房間卡」是否過期。
		
		 // 5. 若「房間卡」過期，使用「房間卡產生器」重新簽署新的「房間卡」。
		
		 // 6. 模擬「房間卡產生器」過期後的情況。
	}
}
