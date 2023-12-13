package mvc.dao.spform;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUserById(Integer id) {
		// TODO Auto-generated method stub
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
