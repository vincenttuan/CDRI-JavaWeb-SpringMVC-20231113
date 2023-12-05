package mvc.controller;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mvc.bean.Person;

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
	 * 路徑: "/age?age=17&age=21&age=20"
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/age?age=17&age=21&age=20
	 * 計算平均年齡
	 * */
	@GetMapping(value = "/age", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getAverageAge(@RequestParam("age") List<Integer> ages) {
		double avg = ages.stream().mapToInt(Integer::intValue).average().getAsDouble();
		return String.format("平均年齡 = %.1f", avg);
	}
	
	/*
	 * 5. Lab 練習: 得到多筆 score 資料
	 * 路徑: "/exam?score=80&score=100&score=50&score=70&score=30"
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/exam?score=80&score=100&score=50&score=70&score=30
	 * 請自行設計一個方法，此方法可以
	 * 印出: 最高分=?、最低分=?、平均=?、總分=?、及格分數=?、不及格=?
	 * (支援中文字印出)     
	 * */
	@GetMapping(value = "/exam", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getExamInfo(@RequestParam("score") List<Integer> scores) {
		IntSummaryStatistics stat = scores.stream().mapToInt(Integer::intValue).summaryStatistics();
		//List<Integer> passList = scores.stream().filter(score -> score >= 60).collect(Collectors.toList());
		//List<Integer> failList = scores.stream().filter(score -> score < 60).collect(Collectors.toList());
		// 利用 Collectors.partitioningBy() 來分組
		Map<Boolean, List<Integer>> resultMap = scores.stream()
				.collect(Collectors.partitioningBy(score -> score >= 60));
		
		return String.format("最高分=%d、最低分=%d、平均=%.1f、總分=%d、及格分數=%s、不及格=%s", 
				stat.getMax(), stat.getMin(), stat.getAverage(), stat.getSum(), resultMap.get(true), resultMap.get(false));
		
	}
	
	/*
	 * 6. 多筆參數資料轉 Map
	 * 路徑: "/personMap?name=John&age=18&score=80&pass=true
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/personMap?name=John&age=18&score=80&pass=true
	 * */
	@GetMapping("/personMap")
	@ResponseBody
	public String getPersonMap(@RequestParam Map<String, String> personMap) {
		return "personMap = " + personMap;
	}
	
	/*
	 * 7. 多筆參數資料轉 bean (例如: person)
	 * 路徑: "/person?name=John&age=18&score=80&pass=true
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/person?name=John&age=18&score=80&pass=true
	 * */
	@GetMapping("/person")
	@ResponseBody
	public String getPerson(Person person) {
		return "person = " + person;
	}
	
	/*
	 * 8. 路徑參數 @PathVairable
	 * 路徑: "/java_score/75?name=John
	 * 路徑: "/java_score/45?name=Mary
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/java_score/75?name=John
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/java_score/45?name=Mary
	 * */
	@GetMapping("/java_score/{score}")
	@ResponseBody
	public String getJavaScore(@PathVariable("score") Integer score, @RequestParam("name") String name) {
		return String.format("%s's java score: %d, pass: %b", name, score, score >= 60);
	}
	
	
}
