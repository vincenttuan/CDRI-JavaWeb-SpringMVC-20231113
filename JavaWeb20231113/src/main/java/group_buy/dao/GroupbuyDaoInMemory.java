package group_buy.dao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import group_buy.entity.Cart;
import group_buy.entity.CartItem;
import group_buy.entity.Product;
import group_buy.entity.User;

// In-Memory
public class GroupbuyDaoInMemory implements GroupBuyDao {
	
	// In-Memory List
	private static List<User> users = new CopyOnWriteArrayList<>();
	private static List<Product> products = new CopyOnWriteArrayList<>();
	private static List<Cart> carts = new CopyOnWriteArrayList<>();
	private static List<CartItem> cartItems = new CopyOnWriteArrayList<>();
	
	@Override
	public List<User> findAllUsers() {
		return users;
	}

	@Override
	public void addUser(User user) {
		users.add(user);
	}

	@Override
	public Boolean updateUserPasswordById(Integer userId, String newPassword) {
		/*
		Optional<User> userOpt = users.stream()
									  .filter(user -> user.getUserId().equals(userId))
									  .findAny();
		if(userOpt.isPresent()) {
			userOpt.get().setPassword(newPassword);
			return true;
		}
		return false;
		*/
		return users.stream()
				  .filter(user -> user.getUserId().equals(userId))
				  .peek(user -> user.setPassword(newPassword))
				  .findAny()
				  .isPresent();
	}

	@Override
	public Optional<User> findUserByName(String username) {
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
	public Boolean updateProductLaunch(Boolean isLaunch) {
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
	public List<Cart> findAllCarts() {
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
	public List<Cart> findCartsByUserIdAndCheckoutStatus(Integer userId, Boolean isCheckout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cart> findNoneCheckoutCartsByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
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
	public Boolean removeCartItemById(Integer itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateCartItemQuantityById(Integer itemId, Integer quantity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 
	
}
