package mvc.dao.spform;

import java.util.List;

public interface DataDaoMySQL extends DataDao  {
	int addInterestData(Integer userId, Integer interestId);
	int deleteInterestData(Integer userId);
	List<Integer> findAllInterestDataIds(Integer userId);
	
	// "INSERT INTO user_interest(userId, interestId) VALUES (?, ?)"
	// "delete from user_interest where userId = ?"
	// "SELECT interestId FROM web.user_interest where userId = ?"
}
