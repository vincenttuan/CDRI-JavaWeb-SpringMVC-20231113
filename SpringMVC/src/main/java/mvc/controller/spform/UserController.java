package mvc.controller.spform;

import java.util.List;

import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		//model.addAttribute("_method", "POST"); // 
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
		model.addAttribute("_method", "PUT");
		return "spform/user";
	}
	
	@GetMapping("/delete/{id}") // Get method 刪除
	public String deleteUser(@PathVariable("id") Integer id) {
		int rowcount = userDao.deleteUserById(id);
		System.out.println("delete User rowcount = " + rowcount);
		return "redirect:/mvc/user/"; // 重導到 user 首頁
	}
	
	@DeleteMapping("/{id}") // Delete method 刪除
	//@ResponseBody
	public String deleteUser2(@PathVariable("id") Integer id) {
		int rowcount = userDao.deleteUserById(id);
		System.out.println("delete User rowcount = " + rowcount);
		//return "rowcount = " + rowcount;
		return "redirect:/mvc/user/"; // 重導到 user 首頁
	}
	
	@PostMapping("/") // 新增 User
	public String addUser(User user) {
		int rowcount = userDao.addUser(user);
		System.out.println("add User rowcount = " + rowcount);
		return "redirect:/mvc/user/"; // 重導到 user 首頁
	}
	
	@PutMapping("/") // 修改 User
	public String updateUser(User user) {
		int rowcount = userDao.updateUserById(user.getId(), user);
		System.out.println("update User rowcount = " + rowcount);
		return "redirect:/mvc/user/"; // 重導到 user 首頁
	}
	
	
}
