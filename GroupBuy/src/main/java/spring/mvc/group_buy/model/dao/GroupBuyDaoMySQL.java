package spring.mvc.group_buy.model.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.mvc.group_buy.model.entity.Cart;
import spring.mvc.group_buy.model.entity.CartItem;
import spring.mvc.group_buy.model.entity.Product;
import spring.mvc.group_buy.model.entity.User;

@Repository
public class GroupBuyDaoMySQL implements GroupBuyDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<User> findAllUsers() {
		String sql = "select userId, username, password, level from user";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
	}

	@Override
	public void addUser(User user) {
		String sql = "insert into user(username, password, level) values(?, ?, ?)";
		jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getLevel());
	}

	@Override
	public Boolean updateUserPassword(Integer userId, String newPassword) {
		String sql = "update user set password = ? where userId = ?";
		int rowcount = jdbcTemplate.update(sql, newPassword, userId);
		return rowcount > 0;
	}

	@Override
	public Optional<User> findUserByUsername(String username) {
		String sql = "select userId, username, password, level from user where username = ?";
		try {
			User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
			return Optional.ofNullable(user);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<User> findUserById(Integer userId) {
		String sql = "select userId, username, password, level from user where userId = ?";
		try {
			User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), userId);
			return Optional.ofNullable(user);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Product> findAllProducts() {
		String sql = "select productId, productName, price, unit, isLaunch from product";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
	}

	@Override
	public Optional<Product> findProductById(Integer productId) {
		String sql = "select productId, productName, price, unit, isLaunch from product where productId = ?";
		try {
			Product product = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), productId);
			return Optional.ofNullable(product);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public void addProduct(Product product) {
		String sql = "insert into product(productName, price, unit, isLaunch) values(?, ?, ?, ?)";
		jdbcTemplate.update(sql, product.getProductName(), product.getPrice(), product.getUnit(), product.getIsLaunch());
	}
	
	//	4. 變更商品上架狀態
	@Override
	public Boolean updateProductLaunch(Integer productId, Boolean isLaunch) {
		String sql = "update product set isLaunch = ? where productId = ?";
		return jdbcTemplate.update(sql, isLaunch, productId) > 0;
	}

	//	購物車/購物車項目(Cart/CartItem)
	//	1. 新增購物車資料
	@Override
	public void addCart(Cart cart) {
		String sql = "insert into cart(userId, isCheckOut) values(?, ?)";
		jdbcTemplate.update(sql, cart.getUserId(), cart.getIsCheckout());
	}
	
	//	2. 新增購物車項目資料
	@Override
	public void addCartItem(CartItem cartItem) {
		String sql1 = "select count(*) as count from cartItem where cartId = ? and productId = ?";
		int count = jdbcTemplate.queryForObject(sql1, Integer.class, cartItem.getCartId(), cartItem.getProductId());
		if(count == 0) {
			String sql2 = "insert into cartItem(cartId, productId, quantity) values(?, ?, ?)";
			jdbcTemplate.update(sql2, cartItem.getCartId(), cartItem.getProductId(), cartItem.getQuantity());
		} else {
			String sql3 = "update cartItem set quantity = quantity + ? where cartId = ? and productId = ?";
			jdbcTemplate.update(sql3, cartItem.getQuantity(), cartItem.getCartId(), cartItem.getProductId());
		}
	}

	@Override
	public List<Cart> findAllCart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Cart> findCartById(Integer cartId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<CartItem> findCartItemById(Integer itemId) {
		try {
			String sql = "select itemId, cartId, productId, quantity from cartitem where itemId = ?";
			CartItem cartItem = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(CartItem.class), itemId);
			// 注入 Cart 物件
			cartItem.setCart(findCartById(cartItem.getCartId()).get());
			return Optional.ofNullable(cartItem);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Cart> findCartsByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cart> findCartsbyUserIdAndCheckoutStatus(Integer userId, Boolean isCheckout) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//	8. 根據使用者ID來查找其未結帳的購物車資料(單筆)
	@Override
	public Optional<Cart> findNoneCheckoutCartByUserId(Integer userId) {
		try {
			String sql = "select cartId, userId, isCheckout, checkoutTime from cart "
					+ "where userId = ? and (isCheckout = false or isCheckout is null)";
			Cart cart = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Cart.class), userId);
			if(cart != null) {
				enrichCartWithDetails(cart);
			}
			return Optional.ofNullable(cart);
		
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	//	9. 根據使用者ID將該使用者的購物車設置為已結帳狀態(前台的事件)
	@Override
	public Boolean checkoutCartByUserId(Integer userId) {
		String sql = "update cart set isCheckout = true where userId = ? and (isCheckout = false or isCheckout is null)";
		return jdbcTemplate.update(sql, userId) == 1;
	}
	
	//	10. 根據購物車ID將購物車設置為已結帳狀態(後台的事件)
	@Override
	public Boolean checkoutCartById(Integer cartId) {
		String sql = "update cart set isCheckout = true where cartId = ? and (isCheckout = false or isCheckout is null)";
		return jdbcTemplate.update(sql, cartId) == 1;
	}
	
	//	11. 根據購物車項目ID刪除指定的購物車項目
	@Override
	public Boolean removeCartItemById(Integer cartItemId) {
		String sql = "delete from cartItem where itemId = ?";
		return jdbcTemplate.update(sql, cartItemId) == 1;
	}
	
	//	12. 更新購物車項目的數量
	@Override
	public Boolean updateCartItemQuantity(Integer cartItemId, Integer quantity) {
		String sql = "update cartItem set quantity = ? where itemId = ?";
		return jdbcTemplate.update(sql, quantity, cartItemId) == 1;
	}

	@Override
	public List<Map<String, Object>> calculateTotalAmountPerUser() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 為 cart 注入 cartItem
	// details: 使用者物件(user) 與 購物車明細(cartItems), 以及購物車明細的商品資料
	private void enrichCartWithDetails(Cart cart) {
		// 注入 user
		//findUserById(cart.getUserId()).ifPresent(user -> cart.setUser(user));
		findUserById(cart.getUserId()).ifPresent(cart::setUser);
		
		// 查詢 cartItems 並注入
		String sqlItems = "select itemId, cartId, productId, quantity from cartitem where cartId = ?";
		List<CartItem> cartItems = jdbcTemplate.query(sqlItems, new BeanPropertyRowMapper<>(CartItem.class), cart.getCartId());
		// 根據 productId 找到 product 並注入
		cartItems.forEach(cartItem -> {
			findProductById(cartItem.getProductId()).ifPresent(cartItem::setProduct);
		});
		cart.setCartItems(cartItems);
	}

}
