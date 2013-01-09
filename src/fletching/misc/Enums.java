package fletching.misc;

public class Enums {

	public static enum LogTypes {
		NOMRAL(1511), OAK(1521), WILLOW(1519), MAPLE(1517), YEW(1515), MAGIC(
				1513);

		private int id;

		private LogTypes(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

	}

	public static enum BowData {
		NORMAL(50, 48, 1511), OAK(54, 56, 1521), WILLOW(60, 58, 1519), MAPLE(
				64, 62, 1517), YEW(68, 66, 1515), MAGIC(72, 70, 1513);
		private int unStrungShortBow, unStrungLongBow, logId;

		private BowData(int unstrungSb, int unstrungLb, int log) {
			this.unStrungShortBow = unstrungSb;
			this.unStrungLongBow = unstrungLb;
			this.logId = log;
		}
		public int getULId() {
			return unStrungLongBow;
		}
		public int getUSId() {
			return unStrungShortBow;
		}
		public int getLogId() {
			return logId;
		}
	}
}
