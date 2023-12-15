package mvc.dao.spform;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mvc.bean.spform.EducationData;
import mvc.bean.spform.InterestData;
import mvc.bean.spform.SexData;

@Repository
public class DataDaoImplMySQL implements DataDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<EducationData> findAllEducationDatas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<EducationData> getEducationDataById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<SexData> findAllSexDatas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SexData> getSexDataById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<InterestData> finAllInterestDatas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<InterestData> getInterestDataById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
}
