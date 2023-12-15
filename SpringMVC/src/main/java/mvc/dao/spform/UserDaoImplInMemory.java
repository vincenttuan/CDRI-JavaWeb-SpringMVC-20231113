package mvc.dao.spform;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.mchange.v2.codegen.bean.BeangenUtils;

import mvc.bean.spform.InterestData;
import mvc.bean.spform.SexData;
import mvc.bean.spform.User;

@Repository
public class UserDaoImplInMemory implements UserDao {
	// User in Memory 資料庫
	private static List<User> users = new CopyOnWriteArrayList<>();
	private static AtomicInteger atomicId = new AtomicInteger(0); // 序號
	
	@Autowired
	@Qualifier("dataDaoImplInMemory")
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
			/*
			使用反射 (不過效率慢)
			Field[] fields = curUser.getClass().getDeclaredFields(); // 獲取所有欄位
	        for (Field field : fields) {
	            field.setAccessible(true); // 允許訪問私有欄位
	            try {
	                Object value = field.get(user); // 從 user 取得新值
	                if (value != null) {
	                    field.set(curUser, value); // 將新值設置到 curUser
	                }
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }
	        }
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
		users.forEach(user -> {
			// 1.注入 sexData 物件到 user 物件中
			Integer sexId = user.getSexId();
			Optional<SexData> sexDataOpt = dataDao.getSexDataById(sexId);
			sexDataOpt.ifPresent(sexData -> user.setSex(sexData));
			//user.setSex(dataDao.getSexDataById(user.getSexId()).get());
			
			// 2.注入 educationData 物件到 user 物件中
			Integer eduId = user.getEducationId();
			user.setEducation(dataDao.getEducationDataById(eduId).get());
			
			// 3.注入 interests 集合到 user 物件中
			List<InterestData> interests = new ArrayList<>();
			for(Integer interestId : user.getInterestIds()) {
				interests.add(dataDao.getInterestDataById(interestId).get());
			}
			user.setInterests(interests);
		});
		
		return users;
		
	}
	
}
