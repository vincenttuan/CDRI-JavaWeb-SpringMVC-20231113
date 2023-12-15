package mvc.bean.spform;

public abstract class BaseData {
	
	protected Integer id;
	protected String name;
	
	public BaseData() {
	}
	
	public BaseData(Integer id, String name) {
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
		return "BaseData [id=" + id + ", name=" + name + "]";
	}
	
	
}
