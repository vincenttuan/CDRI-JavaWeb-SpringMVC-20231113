package pattern.decorator;

// 主元件
public abstract class Food {
	protected String name;
	protected int price;
	
	public abstract String getName();
	public abstract int getPrice();
}
