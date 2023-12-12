package mvc.bean.spform;

import com.google.gson.Gson;

// 興趣資料
public class InterestData {
	private Integer id;
	private String name;
	
	public InterestData( ) {
		
	}
	
	public InterestData(Integer id, String name) {
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
		return new Gson().toJson(this);
	}
}
