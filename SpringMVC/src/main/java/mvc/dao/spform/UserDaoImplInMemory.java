package mvc.dao.spform;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mchange.v2.codegen.bean.BeangenUtils;

import mvc.bean.spform.User;

@Repository
public class UserDaoImplInMemory implements UserDao {
	// User in Memory 資料庫
	private static List<User> users = new CopyOnWriteArrayList<>();
	private static AtomicInteger atomicId = new AtomicInteger(0); // 序號
	
	@Autowired
	private DataDao dataDao;
	
	@Override
	public int addUser(User user) {
		int id = atomicId.incrementAndGet();
		user.setId(id);
		users.add(user);
		return 1;
	}

	@Override
	public int updateUserById(Integer id, User user) {
		Optional<User> userOpt = getUserById(id);
		if(userOpt.isPresent()) {
			User curUser = userOpt.get(); // 目前在集合中的 user 物件
			// 將 curUser 的屬性資料"逐一"修改
			/*
			curUser.setName(user.getName());
			curUser.setAge(user.getAge());
			curUser.setBirth(user.getBirth());
			curUser.setEducationId(user.getEducationId());
			curUser.setEducation(user.getEducation());
			curUser.setSexId(user.getSexId());
			curUser.setSex(user.getSex());
			curUser.setInterestIds(user.getInterestIds());
			curUser.setInterests(user.getInterests());
			curUser.setResume(user.getResume());
			*/
			// 將 curUser 的屬性資料"逐一"修改 (使用 Spring BeanUtils)
			BeanUtils.copyProperties(user, curUser);
			return 1;
		}
		return 0;
	}

	@Override
	public int deleteUserById(Integer id) {
		Optional<User> userOpt = getUserById(id);
		if(userOpt.isPresent()) {
			users.remove(userOpt.get());
			return 1;
		}
		return 0;
	}

	@Override
	public Optional<User> getUserById(Integer id) {
		Optional<User> userOpt = users.stream().filter(user -> user.getId().equals(id)).findFirst();
		return userOpt;
	}

	@Override
	public List<User> findAllUsers() {
		return users;
	}
	
}
