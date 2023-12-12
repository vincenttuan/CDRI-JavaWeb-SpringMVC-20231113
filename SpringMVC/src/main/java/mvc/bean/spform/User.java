package mvc.bean.spform;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;

// 對應 spring form 的表單資訊
public class User {
	private Integer id; // 序號
	private String name; // 姓名
	private Integer age; // 年齡
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birth; // 生日
	
	private String resume; // 履歷
	
	private Integer educationId; // 教育程度Id(給表單用)
	private EducationData education; // 教育程度(給List呈現用)
	
	private Integer sexId; // 性別Id(給表單用)
	private SexData sex; // 性別(給List呈現用)
	
	private Integer[] interestIds; // 興趣Ids(給表單用)
	private List<InterestData> interests; // 興趣(給List呈現用)
	
	// ---------------------------------------------------------
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public Integer getEducationId() {
		return educationId;
	}
	public void setEducationId(Integer educationId) {
		this.educationId = educationId;
	}
	public EducationData getEducation() {
		return education;
	}
	public void setEducation(EducationData education) {
		this.education = education;
	}
	public Integer getSexId() {
		return sexId;
	}
	public void setSexId(Integer sexId) {
		this.sexId = sexId;
	}
	public SexData getSex() {
		return sex;
	}
	public void setSex(SexData sex) {
		this.sex = sex;
	}
	public Integer[] getInterestIds() {
		return interestIds;
	}
	public void setInterestIds(Integer[] interestIds) {
		this.interestIds = interestIds;
	}
	public List<InterestData> getInterests() {
		return interests;
	}
	public void setInterests(List<InterestData> interests) {
		this.interests = interests;
	}
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	
	
}
