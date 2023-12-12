package mvc.bean.spform;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

// 對應 spring form 的表單資訊
public class User {
	private Integer id; // 序號
	private String name; // 姓名
	private Integer age; // 年齡
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birth; // 生日
	
	private String resume; // 履歷
	
	private Integer educationId; // 教育程度Id
	private EducationData education; // 教育程度
	
	private Integer sexId; // 性別Id
	private SexData sex; // 性別
	
	private Integer interestId; // 興趣Id
	private List<InterestData> interests; // 興趣
	
}
