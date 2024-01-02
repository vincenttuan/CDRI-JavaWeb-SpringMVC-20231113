package spring.mvc.group_buy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.group_buy.util.OAuth2Util;

@Controller
@RequestMapping("/secure/oauth2")
public class SecureCallbackOauth2Controller {
	
	@GetMapping("/login/github")
	@ResponseBody
	public String loginGithub() {
		// 向 Github 驗證授權
		String auth = OAuth2Util.AUTH_URL;
		return "redirect:" + auth;
	}
	
	@GetMapping("/callback/github")
	@ResponseBody
	public String callbackGithub(@RequestParam("code") String code) {
		return code;
	}
	
	@GetMapping("/callback/google")
	@ResponseBody
	public String callbackGoogle(@RequestParam("code") String code) {
		return code;
	}
}
