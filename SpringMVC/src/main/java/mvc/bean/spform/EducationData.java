package mvc.bean.spform;

// 教育程度資料
public class EducationData {
	private Integer id;
	private String name;
	
	public EducationData( ) {
		
	}
	
	public EducationData(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

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

	@Override
	public String toString() {
		return "EducationData [id=" + id + ", name=" + name + "]";
	}
	
}
