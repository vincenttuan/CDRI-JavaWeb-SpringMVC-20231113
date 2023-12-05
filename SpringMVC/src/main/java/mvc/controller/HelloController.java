package mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String hi(@RequestParam(value = "name", required = true) String name,
					 @RequestParam(value = "age", required = false, defaultValue = "0") Integer age) {
		
		return String.format("Hi %s, %d", name, age);
	}
	
	/*
	 * 3. Lab 練習
	 * 路徑: "/bmi?h=170.0&w=60.0"
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/bmi?h=170.0&w=60.0
	 * 限制: h, w 是一定要有的
	 * 執行結果: h = 170.0, w = 60.0, bmi = 20.76
	 * 請設計~
	 * */
	@GetMapping("/bmi")
	@ResponseBody
	public String bmi(@RequestParam("h") Double h, @RequestParam("w") Double w) {
		double bmi = w / Math.pow(h/100, 2);
		return String.format("h = %.1f, w = %.1f, bmi = %.2f", h, w, bmi);
	}
	
	/*
	 * 4. 同名多筆的資料參數
	 * 路徑: "/age?age=17&age=21&age=20
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/age?age=17&age=21&age=20
	 * 計算平均年齡
	 * */
	@GetMapping("/age")
	@ResponseBody
	public String getAverageAge(@RequestParam("age") List<Integer> ages) {
		double avg = ages.stream().mapToInt(Integer::intValue).average().getAsDouble();
		return String.format("平均年齡 = %.1f", avg);
	}
	
	
	
}
