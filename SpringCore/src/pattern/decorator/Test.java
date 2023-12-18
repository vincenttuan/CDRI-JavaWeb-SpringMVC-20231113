package pattern.decorator;

public class Test {

	public static void main(String[] args) {
		Food food = new Bread();
		food = new Ham(food);
		food = new Ham(food);
		food = new Ham(food);
		food = new Tuna(food);
		food = new Egg(food);
		food = new Egg(food);
		food = new Lettue(food);
		
		// 結帳
		System.out.printf("結帳:\n");
		System.out.printf("商品: %s\n", food.getName());
		System.out.printf("總價: %d\n", food.getPrice());

	}

}
