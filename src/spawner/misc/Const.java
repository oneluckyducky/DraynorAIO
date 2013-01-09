package spawner.misc;

import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.GroundItem;

public class Const {

	public final static int OBELISK_ID = 29944;

	

	public final static Area TOTAL_AREA = new Area(new Tile[] {
			new Tile(3081, 3256, 0), new Tile(3101, 3252, 0),
			new Tile(3103, 3210, 0), new Tile(3086, 3220, 0),
			new Tile(3081, 3239, 0), new Tile(3074, 3249, 0) });

	

	public final static Area CAST_AREA = new Area(new Tile[] {
			new Tile(3086, 3247, 0), new Tile(3086, 3266, 0),
			new Tile(3096, 3254, 0), new Tile(3097, 3254, 0),
			new Tile(3096, 3253, 0), new Tile(3099, 3253, 0),
			new Tile(3099, 3247, 0) });

	public final static Area CAST_AREA2 = new Area(new Tile[] {
			new Tile(3097, 3247, 0), new Tile(3092, 3247, 0),
			new Tile(3091, 3247, 0), new Tile(3090, 3247, 0),
			new Tile(3089, 3247, 0), new Tile(3086, 3249, 0),
			new Tile(3085, 3249, 0), new Tile(3085, 3250, 0),
			new Tile(3085, 3252, 0), new Tile(3085, 3253, 0),
			new Tile(3085, 3254, 0), new Tile(3085, 3255, 0),
			new Tile(3087, 3256, 0), new Tile(3088, 3256, 0),
			new Tile(3091, 3256, 0), new Tile(3092, 3256, 0),
			new Tile(3094, 3256, 0), new Tile(3094, 3255, 0),
			new Tile(3095, 3254, 0), new Tile(3096, 3253, 0),
			new Tile(3097, 3252, 0), new Tile(3097, 3251, 0) });

	public final static Tile[] BANK_TO_OBELISK = new Tile[] {
			new Tile(3092, 3246, 0), new Tile(3089, 3248, 0),
			new Tile(3086, 3249, 0), new Tile(3084, 3246, 0),
			new Tile(3085, 3243, 0), new Tile(3085, 3240, 0),
			new Tile(3086, 3237, 0), new Tile(3087, 3234, 0),
			new Tile(3089, 3231, 0), new Tile(3090, 3228, 0),
			new Tile(3091, 3225, 0), new Tile(3091, 3222, 0) };

	public final static Tile[] OBELISK_TO_BANK = new Tile[] {
			new Tile(3090, 3222, 0), new Tile(3090, 3225, 0),
			new Tile(3089, 3228, 0), new Tile(3088, 3231, 0),
			new Tile(3087, 3234, 0), new Tile(3086, 3237, 0),
			new Tile(3085, 3240, 0), new Tile(3084, 3243, 0),
			new Tile(3084, 3246, 0), new Tile(3085, 3249, 0),
			new Tile(3088, 3249, 0), new Tile(3091, 3248, 0),
			new Tile(3091, 3245, 0), new Tile(3094, 3243, 0) };

	public final static Filter<GroundItem> LOOT_FILTER = new Filter<GroundItem>() {
		@Override
		public boolean accept(GroundItem g) {
			for(int i : Variables.lootList) {
				return g.getId() == i && g.getLocation().canReach();
			}
			return false;
		}
	};

}
