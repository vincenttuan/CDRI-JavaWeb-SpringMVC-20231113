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
import spring.mvc.group_buy.model.entity.Service;
import spring.mvc.group_buy.model.entity.User;

@Repository
public class GroupBuyDaoMySQL implements GroupBuyDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//	1. 查詢所有使用者(多筆)
	@Override
	public List<User> findAllUsers() {
		String sql = "select userId, username, password, level, authType, authId from user";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
	}

	//	2. 新增使用者
	@Override
	public void addUser(User user) {
		String sql = "insert into user(username, password, level, authType, authId) values(?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getLevel(), user.getAuthType(), user.getAuthId());
	}

	//	3. 修改密碼
	@Override
	public Boolean updateUserPassword(Integer userId, String newPassword) {
		String sql = "update user set password = ? where userId = ?";
		int rowcount = jdbcTemplate.update(sql, newPassword, userId);
		return rowcount > 0;
	}

	//	4. 根據使用者名稱查找使用者(登入用-單筆)	
	@Override
	public Optional<User> findUserByUsername(String username) {
		String sql = "select userId, username, password, level, authType, authId from user where username = ?";
		try {
			User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
			// 查找使用者可以使用的服務(授權)
			String sql2 = "select s.serviceId, s.serviceLocation, s.serviceName, s.serviceUrl "
						+ "from level_ref_service r "
						+ "left join service s on s.serviceId = r.serviceId "
						+ "where r.levelId = ? order by r.sort";
			List<Service> services = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<>(Service.class), user.getLevel());
			user.setServices(services);
			return Optional.ofNullable(user);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	//	5. 根據使用者ID查找使用者(單筆)	
	@Override
	public Optional<User> findUserById(Integer userId) {
		String sql = "select userId, username, password, level, authType, authId from user where userId = ?";
		try {
			User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), userId);
			// 查找使用者可以使用的服務(授權)
			String sql2 = "select s.serviceId, s.serviceLocation, s.serviceName, s.serviceUrl "
						+ "from level_ref_service r "
						+ "left join service s on s.serviceId = r.serviceId "
						+ "where r.levelId = ? order by r.sort";
			List<Service> services = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<>(Service.class), user.getLevel());
			user.setServices(services);
			return Optional.ofNullable(user);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	// 1. 查詢所有商品(多筆)
	@Override
	public List<Product> findAllProducts() {
		String sql = "select productId, productName, price, unit, isLaunch from product";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
	}
	
	// 1. 根據 isLaunch 狀態取得商品資料 
	@Override
	public List<Product> findAllProducts(Boolean isLaunch) {
		String sql = "select productId, productName, price, unit, isLaunch from product where isLaunch = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), isLaunch);
	}

	//	2. 根據產品ID來查找商品(單筆)
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

	//	3. 新增商品
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
	
	//	3. 查詢所有購物車資料(多筆), 無 details 資料
	@Override
	public List<Cart> findAllCart() {
		String sql = "select cartId, userId, isCheckout, checkoutTime from cart";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cart.class));
	}
	
	//	4. 根據購物車ID查找購物車資料(單筆)
	@Override
	public Optional<Cart> findCartById(Integer cartId) {
		try {
			String sql = "select cartId, userId, isCheckout, checkoutTime from cart where cartId = ?";
			Cart cart = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Cart.class), cartId);
			return Optional.ofNullable(cart);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		} 
	}

	//	5. 根據購物車項目ID查找購物車項目資料(單筆)
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
	
	//	6. 根據使用者ID來查找其所有購物車資料(多筆)
	@Override
	public List<Cart> findCartsByUserId(Integer userId) {
		String sql = "select cartId, userId, isCheckout, checkoutTime from cart where userId = ?";
		List<Cart> carts = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cart.class), userId);
		carts.forEach(this::enrichCartWithDetails);
		return carts;
	}
	
	//	7. 根據使用者ID及結帳狀態來查找其所有購物車資料(多筆)
	@Override
	public List<Cart> findCartsbyUserIdAndCheckoutStatus(Integer userId, Boolean isCheckout) {
		String sql = "select cartId, userId, isCheckout, checkoutTime from cart where userId = ? and isCheckout = ?";
		List<Cart> carts = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cart.class), userId, isCheckout);
		//carts.forEach(cart -> enrichCartWithDetails(cart));
		carts.forEach(this::enrichCartWithDetails);
		return carts;
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
	
	// 13. 計算每個使用者所購買的總金額
	@Override
	public List<Map<String, Object>> calculateTotalAmountPerUser() {
		String sql = "SELECT userId, username, total FROM group_buy.usertotalamountview;";
		return jdbcTemplate.queryForList(sql);
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
