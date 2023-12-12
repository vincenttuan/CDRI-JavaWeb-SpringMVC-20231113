package mvc.dao.spform;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import mvc.bean.spform.EducationData;

@Repository
public class DataDaoImplInMemory implements DataDao {

	@Override
	public List<EducationData> findAllEducationDatas() {
		// 小學, 國中, 高中, 大學, 研究所
		List<EducationData> educationDatas = Arrays.asList(
				new EducationData(1, "小學"),
				new EducationData(2, "國中"),
				new EducationData(3, "高中"),
				new EducationData(4, "大學"),
				new EducationData(5, "研究所"));
		return educationDatas;
	}

	@Override
	public Optional<EducationData> getEducationDataById(Integer id) {
		List<EducationData> educationDatas = findAllEducationDatas();
		return educationDatas.stream().filter(edu -> edu.getId().equals(id)).findFirst();
	}
	
}
