package spring.mvc.group_buy.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.group_buy.model.dao.GroupBuyDao;
import spring.mvc.group_buy.model.entity.Cart;
import spring.mvc.group_buy.model.entity.CartItem;
import spring.mvc.group_buy.model.entity.Product;
import spring.mvc.group_buy.model.entity.User;

@Controller
@RequestMapping("/group_buy")
public class GroupBuyController {
	
	@Autowired
	private GroupBuyDao dao;
	
	// 登入首頁
	@GetMapping(value = {"/login", "/", "/login/"})
	public String loginPage() {
		return "group_buy/login";
	}
	
	// 前台登入處理
	@PostMapping("/login")
	public String login(@RequestParam("username") String username, 
						 @RequestParam("password") String password, 
						HttpSession session, Model model) {
		// 根據 username 查找 user 物件
		Optional<User> userOpt = dao.findUserByUsername(username);
		if(userOpt.isPresent()) {
			User user = userOpt.get();
			// 比對 password
			if(user.getPassword().equals(password)) {
				session.setAttribute("user", user); // 將 user 物件放入到 session 變數中
				return "redirect:/mvc/group_buy/frontend/main"; // OK, 導向前台首頁
			} else {
				session.invalidate(); // session 過期失效
				model.addAttribute("loginMessage", "密碼錯誤");
				return "group_buy/login";
			}
		} else {
			session.invalidate(); // session 過期失效
			model.addAttribute("loginMessage", "無此使用者");
			return "group_buy/login";
		}
	}
	
	// 登出
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/mvc/group_buy/login";
	}
	
	// 前台團購首頁
	@GetMapping("/frontend/main")
	public String frontendMain(Model model) {
		List<Product> products = dao.findAllProducts();
		model.addAttribute("products", products);
		return "group_buy/frontend/main";
	}
	
	// 前台購物完成頁
	@PostMapping("/frontend/addToCart")
	public String addToCart(@RequestParam("productId") Integer productId,
							@RequestParam("quantity") Integer quantity,
							HttpSession session, Model model) {
		// 1. 先找到 user 登入者
		User user = (User)session.getAttribute("user");
		// 2. 找到 user 的尚未結帳的購物車
		Cart cart = null;
		Optional<Cart> cartOpt = dao.findNoneCheckoutCartByUserId(user.getUserId());
		if(cartOpt.isPresent()) {
			cart = cartOpt.get(); // 取得該 user 的購物車
		} else { 
			cart = new Cart(); // 建立新的購物車
			cart.setUserId(user.getUserId());
			dao.addCart(cart); // 將購物車存放到資料表
			
			// 新增之後馬上又要查詢, 建議可以下達一個 delay
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				
			}
			
			// 再抓一次該使用者的購物車(目的是要得到剛才新增 cart 的 cartId)
			cart = dao.findNoneCheckoutCartByUserId(user.getUserId()).get();
		}
		
		// 建立購物項目
		CartItem cartItem = new CartItem();
		cartItem.setCartId(cart.getCartId());
		cartItem.setProductId(productId);
		cartItem.setQuantity(quantity);
		// 新增購物車項目
		dao.addCartItem(cartItem);
		
		model.addAttribute("product", dao.findProductById(productId).get()); // 商品物件
		model.addAttribute("quantity", quantity);
		return "group_buy/frontend/result";
	}
	
	// 購物車頁面
	@GetMapping("/frontend/cart")
	public String cartPage(HttpSession session, Model model) {
		// 1. 先找到 user 登入者
		User user = (User)session.getAttribute("user");
		// 2. 找到 user 的尚未結帳的購物車
		Optional<Cart> cartOpt = dao.findNoneCheckoutCartByUserId(user.getUserId());
		cartOpt.ifPresent(cart -> {
			// 3. 計算購物車總金額
			int total = cart.getCartItems().stream()
							.mapToInt(item -> item.getQuantity() * item.getProduct().getPrice())
							.sum();
			model.addAttribute("cart", cart);
			model.addAttribute("total", total);
		});
		
		return "group_buy/frontend/cart";
	}
	
	// 購物車結帳
	@GetMapping("/frontend/checkout")
	public String checkout(HttpSession session, Model model) {
		User user = (User)session.getAttribute("user");
		
		return "group_buy/frontend/finish";
	}
	
}
