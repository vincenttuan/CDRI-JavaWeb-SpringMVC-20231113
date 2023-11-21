package group_buy.dao;

import java.util.List;

import group_buy.entity.User;

// GroupBuy Data Access Object API 規格
public interface GroupBuyDao {
	//	使用者-User:
	/**
	 * 1. 查詢所有使用者(多筆)
	 * @return 所有使用者列表
	 */
	List<User> findAllUsers();
	
	/**	
	 * 2. 新增使用者
	 * @param user
	 */
	void addUser(User user);
	
	/**	
	 * 3. 修改密碼
	 * @param userId 使用者編號
	 * @param newPassword 新密碼
	 * @return 是否修改成功(true:成功, false:失敗)
	 */
	Boolean updateUserPasswordById(Integer userId, String newPassword);
	
	//	4. 根據使用者名稱查找使用者(登入用-單筆)
	
	//	5. 根據使用者ID查找使用者(單筆)
	//
	//	商品-Product
	//	1. 查詢所有商品(多筆)
	
	//	2. 根據產品ID來查找商品(單筆)
	//	3. 新增商品
	//	4. 變更商品上架狀態
	//
	//	購物車/購物車項目(Cart/CartItem)
	//	1. 新增購物車資料
	//	2. 新增購物車項目資料
	//	3. 查詢所有購物車資料(多筆)
	//	4. 根據購物車ID查找購物車資料(單筆)
	//	5. 根據購物車項目ID查找購物車項目資料(單筆)
	//	6. 根據使用者ID來查找其所有購物車資料
	//	7. 根據使用者ID及結帳狀態來查找其所有購物車資料
	//	8. 根據使用者ID來查找其未結帳的購物車資料
	//	9. 根據使用者ID將該使用者的購物車設置為已結帳狀態(前台的事件)
	//	10. 根據購物車ID將購物車設置為已結帳狀態(後台的事件)
	//	11. 根據購物車項目ID刪除指定的購物車項目
	//	12. 更新購物車項目的數量
}
