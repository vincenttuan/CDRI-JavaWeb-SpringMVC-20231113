package mvc.controller.spform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.bean.spform.EducationData;
import mvc.bean.spform.User;
import mvc.dao.spform.DataDao;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private DataDao dataDao;
	
	@GetMapping("/")
	public String index(@ModelAttribute User user, Model model) {
		List<EducationData> educations = dataDao.findAllEducationDatas();
		model.addAttribute("educations", educations); // 將教育程度資料傳給 jsp
		return "spform/user";
	}
	
}
