package security.jwt;

import java.util.HashSet;
import java.util.Set;

import com.nimbusds.jwt.JWTClaimsSet;

import security.KeyUtil;

/**
 * JWTRevocationExample 示範了 JWT 的撤銷機制。
 * 
 * 在許多應用場景中，僅僅擁有 JWT 的到期機制可能不足以滿足安全需求。有時，可能需要提前撤銷某個令牌。
 * 這個類別展示了一種可能的撤銷實現方式，即通過維護一個已撤銷令牌的集合來判斷 JWT 是否已被撤銷。
 * 
 * 主要流程如下：
 * 1. 生成 JWT。
 * 2. 撤銷 JWT。
 * 3. 驗證 JWT，並檢查其是否已被撤銷。
 * 
 * 注意：此示例僅供學習參考，未涵蓋所有安全措施和最佳實踐。
 */
public class SimpleRevocationExample {
	
	// 儲存已經撤銷的 JWT（黑名單）
	private static Set<String> revokedTokens = new HashSet();
	
	public static void main(String[] args) throws Exception {
		String signingSecret = KeyUtil.generateSecret(32);
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
				.subject("user")
				.issuer("https://myapp.com")
				.claim("name", "john")
				.build();
		String token = KeyUtil.signJWT(claimsSet, signingSecret);
		System.out.println("token: " + token);
		
		// 模擬 JWT 撤銷
		revokedTokens.add(token); // 將要撤銷的 token 放入到集合中
		
		// 驗證
		if(KeyUtil.verifyJWTSignature(token, signingSecret)) {
			System.out.println("簽名驗證成功");
			// 確認此 token 是否有被撤銷
			if(revokedTokens.contains(token)) {
				System.out.println("此 token 已被撤銷");
			} else {
				System.out.println("此 token 未被撤銷可以正常使用");
				// 可以安心做其他事情 ...
			}
		} else {
			System.out.println("簽名驗證失敗");
		}
	}

}
