package fishing.misc;

import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

public class Const {
	public final static int FISHING_BAIT = 313;
	public final static int[] FISH_IDS = { 327, 345, 317, 321 };

	public final static Area FISHING_AREA = new Area(new Tile[] {
			new Tile(3078, 3236, 0), new Tile(3081, 3236, 0),
			new Tile(3084, 3235, 0), new Tile(3087, 3235, 0),
			new Tile(3090, 3235, 0), new Tile(3091, 3232, 0),
			new Tile(3091, 3229, 0), new Tile(3091, 3226, 0),
			new Tile(3091, 3223, 0), new Tile(3091, 3220, 0),
			new Tile(3091, 3217, 0), new Tile(3089, 3214, 0),
			new Tile(3086, 3214, 0) });
}
