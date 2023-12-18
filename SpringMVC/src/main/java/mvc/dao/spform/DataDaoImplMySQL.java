package mvc.dao.spform;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mvc.bean.spform.EducationData;
import mvc.bean.spform.InterestData;
import mvc.bean.spform.SexData;

@Repository
public class DataDaoImplMySQL implements DataDaoMySQL {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String sqlAll = "SELECT itemId as id, itemName as name FROM web.basedata where groupName = ?";
	private final String sqlSingle = "SELECT itemId as id, itemName as name FROM web.basedata where groupName = ? and itemId = ?";
	@Override
	public List<EducationData> findAllEducationDatas() {
		return jdbcTemplate.query(sqlAll, new BeanPropertyRowMapper<>(EducationData.class), "Education");
	}

	@Override
	public Optional<EducationData> getEducationDataById(Integer id) {
		return Optional.of(jdbcTemplate.queryForObject(sqlSingle, new BeanPropertyRowMapper<>(EducationData.class), "Education", id));
	}

	@Override
	public List<SexData> findAllSexDatas() {
		return jdbcTemplate.query(sqlAll, new BeanPropertyRowMapper<>(SexData.class), "Sex");
	}

	@Override
	public Optional<SexData> getSexDataById(Integer id) {
		return Optional.of(jdbcTemplate.queryForObject(sqlSingle, new BeanPropertyRowMapper<>(SexData.class), "Sex", id));
	}

	@Override
	public List<InterestData> finAllInterestDatas() {
		return jdbcTemplate.query(sqlAll, new BeanPropertyRowMapper<>(InterestData.class), "Interest");
	}

	@Override
	public Optional<InterestData> getInterestDataById(Integer id) {
		return Optional.of(jdbcTemplate.queryForObject(sqlSingle, new BeanPropertyRowMapper<>(InterestData.class), "Interest", id));
	}
	
	@Override
	public int addInterestData(Integer userId, Integer interestId) {
		String sql = "INSERT INTO user_interest(userId, interestId) VALUES (?, ?)";
		return jdbcTemplate.update(sql, userId, interestId);
	}

	@Override
	public int deleteInterestData(Integer userId) {
		String sql = "delete from user_interest where userId = ?";
		return jdbcTemplate.update(sql, userId);
	}

	@Override
	public List<Integer> findAllInterestDataIds(Integer userId) {
		String sql = "SELECT interestId FROM web.user_interest where userId = ?";
		return jdbcTemplate.queryForList(sql, Integer.class, userId);
	}
	
}
