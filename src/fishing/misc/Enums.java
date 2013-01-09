package fishing.misc;

public class Enums {

	public static enum FISH {
		SARDINE(327, 20, main.global.Methods.getPrice(327)), HERRING(345, 30,
				main.global.Methods.getPrice(345)), SHRIMP(317, 10,
				main.global.Methods.getPrice(317)), ANCHOVIE(321, 40, main.global.Methods.getPrice(321)); 
		
		int id, exp, price;

		FISH(int id, int exp, int price) {
			this.id = id;
			this.exp = exp;
			this.price = price;
		}
		
		public int getId() {
			return id;
		}
		
		public int getExp() {
			return exp;
		}
		
		public int getPrice() {
			return price;
		}
	}

}
