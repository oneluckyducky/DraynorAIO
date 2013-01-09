package painting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.text.DecimalFormat;

import main.DraynorAIO;

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.Tile;

import fishing.misc.*;

public class FishingPaint {

	public static void onRepaint(Graphics g) {

		int experienceGained = Skills.getExperience(Skills.FISHING)
				- Variables.startExp;

		int caught = (Variables.anchovies + Variables.herrings
				+ Variables.sardines + Variables.shrimps);

		int experienceHour = main.global.Methods.getPerHour(experienceGained,
				DraynorAIO.startTime);
		int caughtHour = main.global.Methods.getPerHour(caught,
				DraynorAIO.startTime);

		String expGained = DecimalFormat.getInstance().format(experienceGained);
		String fishCaught = DecimalFormat.getInstance().format(caught);
		String experienceHourly = DecimalFormat.getInstance().format(
				experienceHour);
		String caughtHourly = DecimalFormat.getInstance().format(caughtHour);

		g.setColor(Color.GRAY);
		g.drawString("Experience Gained: " + expGained + "  (" + experienceHourly
				+ ")", 3, 25);
		g.drawString("Fish Caught " + fishCaught + "  (" + caughtHourly + ")", 3,
				38);
		if(Variables.mode == 0) {
			g.drawString("Shrimp: " + Variables.shrimps, 287, 12);
			g.drawString("Anchovie: " + Variables.anchovies, 287, 25);
		} else {
			g.drawString("Sardine: " + Variables.sardines, 287, 12);
			g.drawString("Herring: " + Variables.herrings, 287, 25);
		}

		g.setFont(new Font("Kristen ITC", Font.BOLD, 11));
		g.drawString("Draynor Fishing by OneLuckyDuck", 5, 382);

		for (Tile t : fishing.misc.Const.FISHING_AREA.getBoundingTiles()) {
			for (Polygon p : t.getBounds()) {
				g.drawPolygon(p);
			}
		}
	}
}
