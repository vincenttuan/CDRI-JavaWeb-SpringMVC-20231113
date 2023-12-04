package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * Base url = http://localhost:8080/SpringMVC/mvc
 * Controller url = Base url + "/hello"  
 *                = http://localhost:8080/SpringMVC/mvc/hello
 * */
@Controller
@RequestMapping("/hello")
public class HelloController {
	
	/**
	 * 1. 取得 Welcome SpringMVC 字串
	 * 路徑: "/welcome"
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/welcome
	 * */
	@GetMapping("/welcome")
	@ResponseBody
	public String welcome() {
		return "Welcome SpringMVC";
	}
	
	/**
	 * 2. 取得 Hi SpringMVC 字串
	 * 路徑: "/hi"
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/hi
	 * */
	@GetMapping("/hi")
	@ResponseBody
	public String hi() {
		return "Hi SpringMVC";
	}
	
}
