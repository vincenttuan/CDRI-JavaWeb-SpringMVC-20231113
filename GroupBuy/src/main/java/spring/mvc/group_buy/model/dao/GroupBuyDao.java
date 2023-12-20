package spring.mvc.group_buy.model.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import spring.mvc.group_buy.model.entity.Cart;
import spring.mvc.group_buy.model.entity.CartItem;
import spring.mvc.group_buy.model.entity.Product;
import spring.mvc.group_buy.model.entity.User;

public interface GroupBuyDao {
//	使用者-User:
//	1. 查詢所有使用者(多筆)
	List<User> findAllUsers();
	
//	2. 新增使用者
	void addUser(User user);
	
//	3. 修改密碼
	Boolean updateUserPassword(Integer userId, String newPassword);
	
//	4. 根據使用者名稱查找使用者(登入用-單筆)
	Optional<User> findUserByUsername(String username);
	
//	5. 根據使用者ID查找使用者(單筆)
	Optional<User> findUserById(Integer userId);
//
//	商品-Product
//	1. 查詢所有商品(多筆)
	List<Product> findAllProducts();
	
//	2. 根據產品ID來查找商品(單筆)
	Optional<Product> findProductById(Integer productId);
	
//	3. 新增商品
	void addProduct(Product product);
	
//	4. 變更商品上架狀態
	Boolean updateProductLaunch(Integer productId, Boolean isLaunch);
//
//	購物車/購物車項目(Cart/CartItem)
//	1. 新增購物車資料
	void addCart(Cart cart);
	
//	2. 新增購物車項目資料
	void addCartItem(CartItem cartItem);
	
//	3. 查詢所有購物車資料(多筆)
	List<Cart> findAllCart();
	
//	4. 根據購物車ID查找購物車資料(單筆)
	Optional<Cart> findCartById(Integer cartId);
	
//	5. 根據購物車項目ID查找購物車項目資料(單筆)
	Optional<CartItem> findCartItemById(Integer itemId);
	
//	6. 根據使用者ID來查找其所有購物車資料(多筆)
	List<Cart> findCartsByUserId(Integer userId);
	
//	7. 根據使用者ID及結帳狀態來查找其所有購物車資料(多筆)
	List<Cart> findCartsbyUserIdAndCheckoutStatus(Integer userId, Boolean isCheckout);
	
//	8. 根據使用者ID來查找其未結帳的購物車資料(單筆)
	Optional<Cart> findNoneCheckoutCartByUserId(Integer userId);
	
//	9. 根據使用者ID將該使用者的購物車設置為已結帳狀態(前台的事件)
	Boolean checkoutCartByUserId(Integer userId);
	
//	10. 根據購物車ID將購物車設置為已結帳狀態(後台的事件)
	Boolean checkoutCartById(Integer cartId);
	
//	11. 根據購物車項目ID刪除指定的購物車項目
	Boolean removeCartItemById(Integer cartItemId);
	
//	12. 更新購物車項目的數量
	Boolean updateCartItemQuantity(Integer cartItemId, Integer quantity);
	
	// 13. 計算每個使用者所購買的總金額
	List<Map<String, Object>> calculateTotalAmountPerUser();
	
}
