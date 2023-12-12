package mvc.bean.spform;

import com.google.gson.Gson;

// 性別資料
public class SexData {
	private Integer id;
	private String name;
	
	public SexData( ) {
		
	}
	
	public SexData(Integer id, String name) {
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
