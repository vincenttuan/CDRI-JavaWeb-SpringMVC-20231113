package test1;

import java.util.ArrayList;

import group_buy.dao.GroupBuyDao;
import group_buy.dao.GroupbuyDaoInMemory;
import group_buy.entity.Cart;
import group_buy.entity.CartItem;
import group_buy.entity.Product;
import group_buy.entity.User;

public class TestGroupBuy {

	public static void main(String[] args) {
		GroupBuyDao dao = new GroupbuyDaoInMemory();
		// 加入 User
		User user1 = new User(101, "John", "1234", 1);
		User user2 = new User(102, "Mary", "5678", 2);
		dao.addUser(user1);
		dao.addUser(user2);
		
		// 加入 Product
		Product product1 = new Product(201, "雞腳凍", 50, "包", true);
		Product product2 = new Product(202, "可樂", 100, "箱", true);
		Product product3 = new Product(203, "薯條", 30, "包", false);
		dao.addProduct(product1);
		dao.addProduct(product2);
		dao.addProduct(product3);
		
		// 加入購物車
		Cart cart1 = new Cart(301, 101, false);
		cart1.setUser(user1); // 設定關聯
		CartItem item1 = new CartItem(401, 301, 201, 10);
		CartItem item2 = new CartItem(402, 301, 202, 15);
		cart1.setCartItems(new ArrayList<CartItem>()); // 設定 CartItems 集合, 用來放多筆 CartItem
		cart1.getCartItems().add(item1); // 將 CartItem 放入到 cart 的 CartItems 集合中
		cart1.getCartItems().add(item2); // 將 CartItem 放入到 cart 的 CartItems 集合中
		
		dao.addCart(cart1);
		
		System.out.println(dao.findAllUsers());
		System.out.println(dao.findAllProducts());
		System.out.println(dao.findAllCarts());
		
		System.out.println("-------------------------------");
		Cart cart = dao.findNoneCheckoutCartByUserId(101).get();
		System.out.printf("user id: %d username: %s\n", cart.getUserId(), cart.getUser().getUsername());
		for(CartItem item : cart.getCartItems()) {
			//System.out.printf("Id: %d 商品ID: %d 數量: %d \n", item.getItemId(), item.getProductId(), item.getQuantity());
			System.out.printf("Id: %d 商品ID: %d 商品名稱: %s 數量: %d \n", 
					item.getItemId(), 
					item.getProductId(), 
					item.getProduct() == null ? "null" : item.getProduct().getProductName(), 
					item.getQuantity());
		}
		
		
	}

}
