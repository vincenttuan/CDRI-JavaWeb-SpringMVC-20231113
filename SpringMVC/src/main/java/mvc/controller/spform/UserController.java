package mvc.controller.spform;

import java.util.List;

import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.bean.spform.EducationData;
import mvc.bean.spform.InterestData;
import mvc.bean.spform.SexData;
import mvc.bean.spform.User;
import mvc.dao.spform.DataDao;
import mvc.dao.spform.UserDao;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	//@Qualifier("dataDaoImplInMemory")
	private DataDao dataDao;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/")
	public String index(@ModelAttribute User user, Model model) {
		List<EducationData> educations = dataDao.findAllEducationDatas();
		List<SexData> sexs = dataDao.findAllSexDatas();
		List<InterestData> interests = dataDao.finAllInterestDatas();
		
		model.addAttribute("educations", educations); // 將教育程度資料傳給 jsp
		model.addAttribute("sexs", sexs); // 將性別資料傳給 jsp
		model.addAttribute("interests", interests); // 將興趣資料傳給 jsp
		model.addAttribute("users", userDao.findAllUsers()); // 取得目前最新 users 資料
		model.addAttribute("submitBtnName", "新增");
		return "spform/user";
	}
	
	@GetMapping("/{id}")
	public String getUser(@PathVariable("id") Integer id, Model model) {
		User user = userDao.getUserById(id).get();
		List<EducationData> educations = dataDao.findAllEducationDatas();
		List<SexData> sexs = dataDao.findAllSexDatas();
		List<InterestData> interests = dataDao.finAllInterestDatas();
		
		model.addAttribute("educations", educations); // 將教育程度資料傳給 jsp
		model.addAttribute("sexs", sexs); // 將性別資料傳給 jsp
		model.addAttribute("interests", interests); // 將興趣資料傳給 jsp
		model.addAttribute("users", userDao.findAllUsers()); // 取得目前最新 users 資料
		model.addAttribute("user", user);
		model.addAttribute("submitBtnName", "修改");
		
		return "spform/user";
	}
	
	@GetMapping("/delete/${ id }")
	public String deleteUser(@PathVariable("id") Integer id) {
		int rowcount = userDao.deleteUserById(id);
		System.out.println("delete User rowcount = " + rowcount);
		return "redirect:/mvc/user/"; // 重導到 user 首頁
	}
	
	@PostMapping("/")
	public String addUser(User user) {
		// 判斷是否有 id
		// 沒有 id 表示是新增, 有 id 表示要進行修改
		if(user.getId() == null) {
			int rowcount = userDao.addUser(user);
			System.out.println("add User rowcount = " + rowcount);
		} else {
			int rowcount = userDao.updateUserById(user.getId(), user);
			System.out.println("update User rowcount = " + rowcount);
		}
		
		return "redirect:/mvc/user/"; // 重導到 user 首頁
	}
	
	
}
