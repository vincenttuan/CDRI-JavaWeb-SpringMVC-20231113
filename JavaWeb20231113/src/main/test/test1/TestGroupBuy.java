package test1;

import group_buy.dao.GroupBuyDao;
import group_buy.dao.GroupbuyDaoInMemory;
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
		
		System.out.println(dao.findAllUsers());
		System.out.println(dao.findAllProducts());
		System.out.println(dao.findAllCarts());
		
		
	}

}
