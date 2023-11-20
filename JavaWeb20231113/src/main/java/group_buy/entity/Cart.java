package group_buy.entity;

import java.util.List;

/**
3. 購物車主檔(Master)
+--------+----------+-----------+------------+
| cartId |  userId  | cartItems | isCheckout |
+--------+----------+-----------+------------+
|  201   |   101    | [1, 2]    |    true    |
|  202   |   102    | [3]       |    false   |
|  203   |   103    | [4, 5]    |    true    |
|  204   |   103    | []        |    false   |
|  205   |   101    | [6]       |    true    |
+--------+----------+-----------+------------+
*/
public class Cart {
	private Integer cartId; // 購物車 ID
	private Integer userId; // 使用者 ID
	private User user; // 使用者物件
	private List<CartItem> cartItems; // 購物車明細
	private Boolean isCheckout; // 是否結帳
	
	public Cart() {
		
	}
	
	public Cart(Integer cartId, Integer userId, Boolean isCheckout) {
		this.cartId = cartId;
		this.userId = userId;
		// 利用 userId 查找 User 物件
		
		// 利用 cartId 查找 cartItems
		
		this.isCheckout = isCheckout;
	}
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public Boolean getIsCheckout() {
		return isCheckout;
	}
	public void setIsCheckout(Boolean isCheckout) {
		this.isCheckout = isCheckout;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", user=" + user + ", cartItems=" + cartItems
				+ ", isCheckout=" + isCheckout + "]";
	}
	
	
	
}
