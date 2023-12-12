package mvc.dao.spform;

import java.util.List;
import java.util.Optional;

import mvc.bean.spform.EducationData;

public interface DataDao {
	List<EducationData> findAllEducationDatas();
	Optional<EducationData> getEducationDataById(Integer id);
	
}
