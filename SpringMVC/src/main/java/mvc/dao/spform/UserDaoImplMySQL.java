package mvc.dao.spform;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mchange.v2.codegen.bean.BeangenUtils;

import mvc.bean.spform.InterestData;
import mvc.bean.spform.SexData;
import mvc.bean.spform.User;

@Repository
public class UserDaoImplMySQL implements UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("dataDaoImplMySQL")
	private DataDao dataDao;

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

    @Override
    public int updateUserById(Integer id, User user) {
        String sql = "UPDATE user SET name=?, age=?, birth=?, resume=?, educationId=?, sexId=? WHERE id=?";
        return jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getBirth(), user.getResume(),
                user.getEducationId(), user.getSexId(), id);
    }

    @Override
    public int deleteUserById(Integer id) {
        String sql = "DELETE FROM user WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        String sql = "SELECT id, name, age, birth, resume, educationId, sexId FROM user WHERE id=?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAllUsers() {
        String sql = "SELECT id, name, age, birth, resume, educationId, sexId FROM user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
	
	
	
}
