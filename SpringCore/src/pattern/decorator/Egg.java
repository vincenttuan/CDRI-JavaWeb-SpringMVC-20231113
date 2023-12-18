package pattern.decorator;

public class Egg extends SideDish {

	public Egg(Food food) {
		super(food);
		name = "蛋";
		price = 20;
	}
	
}
