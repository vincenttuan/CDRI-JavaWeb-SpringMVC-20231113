package mvc.bean.spform;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.DecimalMax;

import static java.util.stream.Collectors.joining;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;

// 對應 spring form 的表單資訊
public class User {
	private Integer id; // 序號
	
	@NotEmpty(message = "{user.name.notempty}")
	@Size(min = 3, max = 10, message = "{user.name.size}")
	private String name; // 姓名
	
	@NotNull(message = "{user.age.notenull}")
	//@DecimalMin(value = "1", message = "年齡不可以小於 1 歲")
	//@DecimalMax(value = "150", message = "年齡不可以大於 150 歲")
	@Range(min = 1, max = 150, message = "{user.age.range}")
	private Integer age; // 年齡
	
	@NotNull(message = "{user.birth.notnull}")
	@Past(message = "{user.birth.past}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birth; // 生日
	
	@Size(max = 1000, message = "{user.resume.size}")
	private String resume; // 履歷
	
	@NotNull(message = "{user.educationId.notnull}")
	private Integer educationId; // 教育程度Id(給表單用)
	private EducationData education; // 教育程度(給 User Spring List 呈現用)
	
	@NotNull(message = "{user.sexId.notnull}")
	private Integer sexId; // 性別Id(給表單用)
	private SexData sex; // 性別(給 User Spring List 呈現用)
	
	@Size(min = 1, message = "{user.interestIds.size}")
	private Integer[] interestIds; // 興趣Ids(給表單用)
	private List<InterestData> interests; // 興趣(給  User Spring List 呈現用)
	
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
	
	// 顯示所有興趣名稱(給 jstl 使用, 使用名稱: interestNames)
	public String getInterestNames() {
		if(interestIds != null && interests != null) {
			return interests.stream()
							.map(InterestData::getName)
							.collect(joining(" "));
		}
		return "";
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", birth=" + birth + ", resume=" + resume
				+ ", educationId=" + educationId + ", education=" + education + ", sexId=" + sexId + ", sex=" + sex
				+ ", interestIds=" + Arrays.toString(interestIds) + ", interests=" + interests + "]";
	}
	
	
}
