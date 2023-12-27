package security.sign;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import security.KeyUtil;

/*
 * 情境說明：
 * 延續「DigitalSignatureCreator」的情境，小王的合作夥伴，小李，收到了小王用數位簽章簽署的「my_contract.txt」文件。
 * 小李想確認這份文件的完整性，以及確定這份合同確實是由小王簽署的。因此，他決定使用數位簽章驗證工具來進行檢查。
 *
 * 本程式「DigitalSignatureVerifier」的目的是驗證「my_contract.txt」文件的數位簽章。
 * 小李透過使用小王的公鑰（publicKey.key）和數位簽章（signature.sig），這個程式可以確認文件的完整性和簽署者的身份。
 * 如果驗證成功，這意味著文件在傳輸過程中沒有被修改，且確實是由小王簽署的。
 */
public class DigitalSignatureVerify {
	public static void main(String[] args) throws Exception {
		// 小王
		// 合約檔位置
		String contractPath = "src/security/sign/my_contract.txt";
		// 公鑰檔位置
		String publicKeyPath = "src/security/sign/publicKey.key";
		// 私鑰檔位置
		String privateKeyPath = "src/security/sign/privateKey.key";
		// 數位簽章檔位置
		String signaturePath = "src/security/sign/signature.sig";
		
		// 公鑰與私鑰
		PublicKey publicKey;
		PrivateKey privateKey;
		
		// 取得公私鑰/建立私公鑰
		if(Files.exists(Paths.get(publicKeyPath)) && Files.exists(Paths.get(privateKeyPath))) {
			// 取得公私鑰
			publicKey = KeyUtil.getPublicKeyFromFile("RSA", publicKeyPath);
			privateKey = KeyUtil.getPrivateKeyFromFile("RSA", privateKeyPath);
		} else {
			// 建立公私鑰
			KeyPair keyPair = KeyUtil.generateRSAKeyPair();
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
			// 保存公私鑰檔案
			KeyUtil.saveKeyToFile(publicKey, publicKeyPath);
			KeyUtil.saveKeyToFile(privateKey, privateKeyPath);
		}
		
		// 數位簽章: 將私鑰(私人印章) 蓋在合約(my_contract.txt)上
		byte[] digitalSignature = KeyUtil.signWithPrivateKey(privateKey, contractPath);
		
		// 保存數位簽章
		KeyUtil.saveSignatureToFile(digitalSignature,  signaturePath);
		
		System.out.println("小王的公鑰(驗證數位簽章用):" + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
		System.out.println("小王的數位簽章:" + Base64.getEncoder().encodeToString(digitalSignature));
		System.out.println("小王的數位簽章位置:" + signaturePath);
		
	}
}
