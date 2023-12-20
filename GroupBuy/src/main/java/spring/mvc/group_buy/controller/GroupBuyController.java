package spring.mvc.group_buy.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	@ResponseBody
	public String login(@RequestParam("username") String username, 
						 @RequestParam("password") String password, 
						HttpSession session) {
		// 根據 username 查找 user 物件
		Optional<User> userOpt = dao.findUserByUsername(username);
		if(userOpt.isPresent()) {
			User user = userOpt.get();
			// 比對 password
			if(user.getPassword().equals(password)) {
				return "login: ok";
			} else {
				return "login: password fail";
			}
		} else {
			return "login: username not found";
		}
	}
	
}
