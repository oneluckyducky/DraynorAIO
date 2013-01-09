package main.global;

import java.awt.Image;

import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

public class Const {
	public final static int[] BANKERS = { 4456, 4457, 4458, 4459 };

	public final static Image BAR = main.global.Methods
			.getImage("http://puu.sh/1p9xg.png");
	
	public final static Area BANK_AREA = new Area(new Tile[] {
			new Tile(3088, 3246, 0), new Tile(3097, 3246, 0),
			new Tile(3097, 3240, 0), new Tile(3088, 3240, 0) });
}
