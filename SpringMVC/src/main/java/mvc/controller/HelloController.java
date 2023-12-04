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
	 * 2. ? 帶參數
	 * 路徑: "/hi?name=John&age=18"
	 * 路徑: "/hi?name=Mary&age=20"
	 * 路徑: "/hi?name=Helen"
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/hi?name=John&age=18
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/hi?name=Mary&age=20
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/hi?name=Helen
	 * 限制: name 參數是一定要有的(預設)
	 *      age 參數是不一定要有的, 初始值 = 0
	 * */
	@GetMapping("/hi")
	@ResponseBody
	public String hi() {
		return "Hi SpringMVC";
	}
	
}
