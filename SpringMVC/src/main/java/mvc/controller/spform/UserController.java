package mvc.controller.spform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.bean.spform.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/")
	public String index(@ModelAttribute User user) {
		
		return "spform/user";
	}
	
}
