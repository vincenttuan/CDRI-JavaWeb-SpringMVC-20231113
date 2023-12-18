package mvc.dao.spform;

import java.util.List;

public interface DataDaoMySQL extends DataDao  {
	int addInterestData(Integer userId, Integer interestId);
	int deleteInterestData(Integer userId);
	List<Integer> findAllInterestDataIds(Integer userId);
}
