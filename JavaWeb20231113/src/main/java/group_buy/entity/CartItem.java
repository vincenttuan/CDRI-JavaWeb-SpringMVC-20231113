package group_buy.entity;


/**
4. 購物車明細檔(Detail)
+--------+----------+-----------+------------+
| itemId |  cartId   | productId |  quantity  |
+--------+----------+-----------+------------+
|   1    |   201    |    501    |     10     |
|   2    |   201    |    502    |     8      |
|   3    |   202    |    503    |     5      |
|   4    |   203    |    502    |     8      |
|   5    |   203    |    504    |     20     |
|   6    |   205    |    505    |     15     |
+--------+----------+-----------+------------+
 * */
public class CartItem {
	private Integer itemId; // 明細檔 Id
	private Integer cartId; // 購物車 Id
	private Cart cart; // 購物車物件
	private Integer productId; // 商品 Id
	private Product product; // 商品物件
	private Integer quantity; // 購買數量
	
	public CartItem() {
		
	}

	public CartItem(Integer itemId, Integer cartId, Integer productId, Integer quantity) {
		this.itemId = itemId;
		
		this.cartId = cartId;
		// 利用 cartId 查找 Cart 物件
		
		this.productId = productId;
		// 利用 productId 查找 Product 物件
		
		this.quantity = quantity;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItem [itemId=" + itemId + ", cartId=" + cartId + ", cart=" + cart + ", productId=" + productId
				+ ", product=" + product + ", quantity=" + quantity + "]";
	}
	
	
	
	
}
