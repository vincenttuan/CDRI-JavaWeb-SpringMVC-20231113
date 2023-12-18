package mvc.dao.spform;

import java.util.List;
import java.util.Optional;

import mvc.bean.spform.EducationData;
import mvc.bean.spform.InterestData;
import mvc.bean.spform.SexData;

public interface DataDao {
	List<EducationData> findAllEducationDatas();
	Optional<EducationData> getEducationDataById(Integer id);
	
	List<SexData> findAllSexDatas();
	Optional<SexData> getSexDataById(Integer id);
	
	List<InterestData> finAllInterestDatas();
	Optional<InterestData> getInterestDataById(Integer id);
	
	
}
