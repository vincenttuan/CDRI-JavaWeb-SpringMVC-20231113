package spring.mvc.group_buy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/group_buy")
public class GroupBuyController {
	
	// 登入首頁
	@GetMapping(value = {"/login", "/", "/login/"})
	public String loginPage() {
		return "group_buy/login";
	}
	
}
