package spring.mvc.group_buy.model.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<User> findUserById(Integer userId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Product> findAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Product> findProductById(Integer productId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean updateProductLaunch(Integer productId, Boolean isLaunch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCart(Cart cart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return Optional.empty();
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

	@Override
	public Optional<Cart> findNoneCheckoutCartByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Boolean checkoutCartByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean checkoutCartById(Integer cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean removeCartItemById(Integer cartItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateCartItemQuantity(Integer cartItemId, Integer quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> calculateTotalAmountPerUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
