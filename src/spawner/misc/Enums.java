package spawner.misc;

public class Enums {

	public static enum PouchTypes {
		SPIDER(12059), BAT(12033), COBRA(12015);
		private int id;

		PouchTypes(int id) {
			this.setId(id);
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}

	public static enum ScrollTypes {
		EGG_SPAWN(12428), FRUITFALL(12423), OPH_INCUBATION(12436);
		private int id;

		ScrollTypes(int id) {
			this.setId(id);
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}

	public static enum Fruit {
		BANANA(1963), LEMON(2102), PAPAYA(5972), LIME(2120), ORANGE(2108), WATERMELON(
				5982), COCONUT(5974);
		private int id;

		Fruit(int id) {
			this.setId(id);
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}

	public static enum Eggs {
		RED_SPIDER_EGG(223), CHICKEN_EGG(1944), COCKATRICE_EGG(12109);
		private int id;

		Eggs(int id) {
			this.setId(id);
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}

}
