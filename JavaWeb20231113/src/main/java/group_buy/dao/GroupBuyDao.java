package group_buy.dao;

import java.util.List;
import java.util.Optional;

import group_buy.entity.Cart;
import group_buy.entity.CartItem;
import group_buy.entity.Product;
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
	 * 功能說明: 此程式是用來修改密碼用...
	 * @param userId 使用者編號
	 * @param newPassword 新密碼
	 * @return 是否修改成功(true:成功, false:失敗)
	 */
	Boolean updateUserPasswordById(Integer userId, String newPassword);
	
	/**	
	 * 4. 根據使用者名稱查找使用者(登入用-單筆)
	 * @param username
	 * @return 單一使用者物件
	 */
	Optional<User> findUserByName(String username);
	
	/**	
	 * 5. 根據使用者ID查找使用者(單筆)
	 * @param userId
	 * @return 單一使用者物件
	 */
	Optional<User> findUserById(Integer userId);
	
	//
	//	商品-Product
	/**	
	 * 1. 查詢所有商品(多筆)
	 * @return 所有商品列表
	 */
	List<Product> findAllProducts();
	
	/**	
	 * 2. 根據產品ID來查找商品(單筆)
	 * @param productId
	 * @return 單一商品
	 */
	Optional<Product> findProductById(Integer productId);
	
	/**	
	 * 3. 新增商品
	 * @param product
	 */
	void addProduct(Product product);
	
	/**	4. 變更該商品上架狀態
	 * 若要上架將 isLaunch = true, 反之設定為 false
	 * @param productId
	 * @param isLaunch
	 * @return
	 */
	Boolean updateProductLaunchById(Integer productId, Boolean isLaunch);
	
	//
	//	購物車/購物車項目(Cart/CartItem)
	/**	
	 * 1. 新增購物車資料
	 * @param cart
	 */
	void addCart(Cart cart);
	
	/**	
	 * 2. 新增購物車項目資料
	 * @param cartItem
	 */
	void addCartItem(CartItem cartItem);
	
	/**	
	 * 3. 查詢所有購物車資料(多筆)
	 * @return 所有購物車資訊
	 */
	List<Cart> findAllCarts();
	
	/**	
	 * 4. 根據購物車ID查找購物車資料(單筆)
	 * @param cartId
	 * @return 取得單筆購物車資料
	 */
	Optional<Cart> findCartById(Integer cartId);
	
	/**	
	 * 5. 根據購物車項目ID查找購物車項目資料(單筆)
	 * @param itemId
	 * @return 取得單筆購物車明細資料
	 */
	Optional<CartItem> findCartItemById(Integer itemId);
	
	/**	
	 * 6. 根據使用者ID來查找其所有購物車資料(多筆)
	 * 得到該名使用者的所有購物車資料
	 * @param userId
	 * @return 購物車列表資料
	 */
	List<Cart> findCartsByUserId(Integer userId);
	
	/**	
	 * 7. 根據使用者ID及結帳狀態來查找其所有購物車資料
	 * @param userId
	 * @param isCheckout
	 * @return 購物車列表資料
	 */
	List<Cart> findCartsByUserIdAndCheckoutStatus(Integer userId, Boolean isCheckout);
	
	/**	8. 根據使用者ID來查找其未結帳的購物車資料(單筆)
	 * @param userId
	 * @return 購物車資料
	 */
	Optional<Cart> findNoneCheckoutCartByUserId(Integer userId);
	
	/**	
	 * 9. 根據使用者ID將該使用者的購物車設置為已結帳狀態(前台的事件)
	 * @param userId
	 * @return 是否結帳成功
	 */
	Boolean checkoutCartByUserId(Integer userId);
	
	/**	
	 * 10. 根據購物車ID將購物車設置為已結帳狀態(後台的事件)
	 * @param cartId
	 * @return 是否結帳成功
	 */
	Boolean checkoutCartById(Integer cartId);
	
	/**	
	 * 11. 根據購物車項目ID刪除指定的購物車項目
	 * @param itemId
	 * @return 是否刪除成功
	 */
	Boolean removeCartItemById(Integer itemId);
	
	/** 12. 更新購物車項目的數量
	 * @param itemId
	 * @param quantity
	 * @return 數量是否修改成功
	 */
	Boolean updateCartItemQuantityById(Integer itemId, Integer quantity);
}
