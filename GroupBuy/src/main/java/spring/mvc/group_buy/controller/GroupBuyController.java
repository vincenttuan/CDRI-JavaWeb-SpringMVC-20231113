package spring.mvc.group_buy.controller;

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
				model.addAttribute("loginMessage", "密碼錯誤");
				return "group_buy/login";
			}
		} else {
			model.addAttribute("loginMessage", "無此使用者");
			return "group_buy/login";
		}
	}
	
	// 前台團購首頁
	@RequestMapping("/frontend/main")
	@ResponseBody
	public String frontendMain() {
		return "Login OK";
	}
	
}
