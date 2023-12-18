package mvc.dao.spform;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mchange.v2.codegen.bean.BeangenUtils;

import mvc.bean.spform.EducationData;
import mvc.bean.spform.InterestData;
import mvc.bean.spform.SexData;
import mvc.bean.spform.User;

@Repository
public class UserDaoImplMySQL implements UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	@Qualifier("dataDaoImplMySQL")
	private DataDaoMySQL dataDao;

	/*
	@Override
    public int addUser(User user) {
		// 新增 user 紀錄
        final String sql = "INSERT INTO user (name, age, birth, resume, educationId, sexId) VALUES (?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        int rowcount = jdbcTemplate.update(conn -> {
        	PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        	pstmt.setString(1, user.getName());
        	pstmt.setInt(2, user.getAge());
        	// java.util.Date 轉 java.sql.Date
        	pstmt.setDate(3, new java.sql.Date(user.getBirth().getTime()));
        	pstmt.setString(4, user.getResume());
        	pstmt.setInt(5,  user.getEducationId());
        	pstmt.setInt(6,  user.getSexId());
        	return pstmt;
        }, keyHolder);
        
        if(rowcount > 0) {
        	int userId = keyHolder.getKey().intValue(); // 得到 id
        	
	        // 新增 user_interest 紀錄
        	String sql2 = "INSERT INTO user_interest(userId, interestId) values(?, ?)";
	        for(Integer interestId : user.getInterestIds()) {
	        	jdbcTemplate.update(sql2, userId, interestId);
	        }
        }
        
        return rowcount;
    }
    */

	@Override
	@Transactional
	public int addUser(User user) {
	    final String insertSql = "INSERT INTO user (name, age, birth, resume, educationId, sexId) VALUES (:name, :age, :birth, :resume, :educationId, :sexId)";
	
	    // 使用 BeanPropertySqlParameterSource 自動映射
	    BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
	
	    // 使用 KeyHolder 來取得自動產生的 ID
	    KeyHolder keyHolder = new GeneratedKeyHolder();
	    
	    // 使用 NamedParameterJdbcTemplate 執行更新操作
	    namedParameterJdbcTemplate.update(insertSql, paramSource, keyHolder, new String[] {"ID"});
	
	    // 從 KeyHolder 獲取生成的 user ID
	    int userId = keyHolder.getKey().intValue();
	
	    // 插入 user 興趣記錄(可以改用 batch update 批次新增來運行)
	    for (Integer interestId : user.getInterestIds()) {
	    	dataDao.addInterestData(userId, interestId);
	    }
	
	    return userId;
	}


    @Override
    public int updateUserById(Integer id, User user) {
    	// 異動 user_interest 資料表
    	dataDao.deleteInterestData(id);
    	
    	Arrays.stream(user.getInterestIds())
    		  .forEach(interestId -> dataDao.addInterestData(id, interestId)); // 逐筆加入
    	
    	// 異動 user 資料表
        String sql3 = "UPDATE user SET name=?, age=?, birth=?, resume=?, educationId=?, sexId=? WHERE id=?";
        return jdbcTemplate.update(sql3, user.getName(), user.getAge(), user.getBirth(), user.getResume(),
                user.getEducationId(), user.getSexId(), id);
    }

    @Override
    public int deleteUserById(Integer id) {
    	// 先刪除 user_interest
    	dataDao.deleteInterestData(id);
    	
    	// 再刪除 user
        String sql2 = "DELETE FROM user WHERE id=?";
        return jdbcTemplate.update(sql2, id);
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        String sql1 = "SELECT id, name, age, birth, resume, educationId, sexId FROM user WHERE id=?";
        User user = jdbcTemplate.queryForObject(sql1, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        // 豐富/增強 user 的其他資料
        enrichUserDetails(user);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAllUsers() {
        String sql = "SELECT id, name, age, birth, resume, educationId, sexId FROM user";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        // 豐富/增強 user 的其他資料
        users.forEach(this::enrichUserDetails); // users.forEach(user -> enrichUserDetails(user));
        return users;
    }
	
    // 豐富/增強 user 的其他資料
    private void enrichUserDetails(User user) {
        List<Integer> interestIds = dataDao.findAllInterestDataIds(user.getId());
        
        //Integer[] interestIdArray = interestIds.toArray(new Integer[0]); // 將 List<Integer> 轉 Integer[]
        //user.setInterestIds(interestIdArray);
        user.setInterestIds(interestIds.toArray(new Integer[0]));
        
        SexData sex = dataDao.getSexDataById(user.getSexId()).get();
        EducationData edu = dataDao.getEducationDataById(user.getEducationId()).get();
        /*
        List<InterestData> interests = new ArrayList<InterestData>();
        for(Integer interestId : user.getInterestIds()) {
        	InterestData interest = dataDao.getInterestDataById(interestId).get();
        	interests.add(interest);
        }
        */
        List<InterestData> interests = Arrays.stream(user.getInterestIds())
        						.map(interestId -> dataDao.getInterestDataById(interestId).get())
        						.collect(Collectors.toList());
        
        user.setSex(sex);
        user.setEducation(edu);
        user.setInterests(interests);
    }
	
	
}
