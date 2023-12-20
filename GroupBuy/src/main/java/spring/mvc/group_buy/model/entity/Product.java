package spring.mvc.group_buy.model.entity;

/**
1. 商品
Pack(盒), Box(箱), Bottle(瓶), Bag(包), Dozen(打)
+-----------+-------------+-------+--------+----------+
| productId | productName | price |  unit  | isLaunch |
+-----------+-------------+-------+--------+----------+
|    501    |   Coffee    |  300  |  Pack  |   true   |
|    502    |  Green Tea  |  150  |  Box   |   false  |
|    503    |   Honey     |  200  | Bottle |   false  |
|    504    |   Sugar     |  100  |  Bag   |   true   |
|    505    |   Milk      |  450  |  Dozen |   true   |
+-----------+-------------+-------+--------+----------+
*/
public class Product {
	private Integer productId; // 商品代號
	private String productName; // 商品名稱
	private Integer price; // 商品價格 
	private String unit; // 單位
	private Boolean isLaunch; // 是否上架 
	
	public Product() {
		
	}
	
	public Product(Integer productId, String productName, Integer price, String unit, Boolean isLaunch) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.unit = unit;
		this.isLaunch = isLaunch;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Boolean getIsLaunch() {
		return isLaunch;
	}

	public void setIsLaunch(Boolean isLaunch) {
		this.isLaunch = isLaunch;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price + ", unit="
				+ unit + ", isLaunch=" + isLaunch + "]";
	}
	
	
	
}
