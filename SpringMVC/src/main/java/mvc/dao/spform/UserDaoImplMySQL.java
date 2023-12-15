package mvc.dao.spform;

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
        String sql = "INSERT INTO user (name, age, birth, resume, educationId, sexId) VALUES (?, ?, ?, ?, ?, ?)";
        
        return jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getBirth(), user.getResume(),
                user.getEducationId(), user.getSexId());
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
