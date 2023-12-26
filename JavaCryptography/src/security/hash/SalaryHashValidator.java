package security.hash;

import java.util.Scanner;

import security.KeyUtil;

public class SalaryHashValidator {

	public static void main(String[] args) {
		// 先得到已知的 hash
		Scanner scanner = new Scanner(System.in);
		// 4d3910e5b62e48b26033dac3af0dbe9f09f8f4e985cbf07c9f72dfb1412b70ee
		System.out.print("請輸入hash:");
		String knowHash = scanner.next();
		
		// 取得 my_salary.txt 的 hash
		String filepath = "src/security/hash/my_salary.txt";
		String filehash = KeyUtil.generateFileHash("src/security/hash/my_salary.txt");
		
		// 比對 knowHash 與 filehash 是否一致
		if(knowHash.equals(filehash)) {
			System.out.println(filepath + " 是完整的, 沒有被別人修改");
		} else {
			System.out.println(filepath + " 可能已遭受修改");
		}
	}

}
