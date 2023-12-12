package mvc.bean.spform;

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
		return "InterestData [id=" + id + ", name=" + name + "]";
	}
}
