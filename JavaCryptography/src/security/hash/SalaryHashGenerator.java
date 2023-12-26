package security.hash;

import security.KeyUtil;

// 針對 my_salary.txt 產生 hash
public class SalaryHashGenerator {
	public static void main(String[] args) {
		String filepath = "src/security/hash/my_salary.txt";
		String filehash = KeyUtil.generateFileHash(filepath);
		
		System.out.println("my_salary.txt hash:" + filehash);
	}
}
