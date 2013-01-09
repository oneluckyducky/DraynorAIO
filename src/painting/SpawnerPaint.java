package painting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.text.DecimalFormat;

import main.DraynorAIO;

import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.tab.Summoning;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.GroundItem;

import spawner.misc.Const;
import spawner.misc.Methods;
import spawner.misc.Variables;

public class SpawnerPaint {

	public static void onRepaint(Graphics g) {

		int experienceGained = Skills.getExperience(Skills.SUMMONING)
				- Variables.startingExperience;

		Variables.casts = Math
				.round(((float) experienceGained / Variables.xpEachCast));

		int profitHour = main.global.Methods.getPerHour(Variables.profit,
				DraynorAIO.startTime);
		int experienceHour = main.global.Methods.getPerHour(experienceGained,
				DraynorAIO.startTime);
		int collectHour = main.global.Methods.getPerHour(Variables.collected,
				DraynorAIO.startTime);
		int castsHour = main.global.Methods.getPerHour(Variables.casts,
				DraynorAIO.startTime);

		String profitHourly = DecimalFormat.getInstance().format(profitHour);
		String experienceHourly = DecimalFormat.getInstance().format(
				experienceHour);
		String collectHourly = DecimalFormat.getInstance().format(collectHour);
		String castsHourly = DecimalFormat.getInstance().format(castsHour);

		// -----------------------------------------------------

		g.drawString(Variables.mode == 0 ? "Fruit gathered: "
				+ Variables.collected + " (" + collectHourly + ")"
				: "Eggs Collected: " + Variables.collected + " ("
						+ collectHourly + ")", 3, 25);
		g.drawString("Profit: " + Variables.profit + " (" + profitHourly + ")",
				3, 38);

		// ----
		g.drawString("Experience Gained: " + experienceGained + " ("
				+ experienceHourly + ")", 145, 12);
		g.drawString("Casts: " + Variables.casts + " (" + castsHourly + ")",
				145, 25);

		// ----
		g.drawString("Summoning Points Left: " + Summoning.getPoints(), 380, 12);
		g.drawString("Special Points Left: " + Methods.getSpecialLeft(), 380,
				25);
		if (Variables.mode == 0) {
			g.drawString("Scrolls Left: > " + Methods.getScrollsLeftString(),
					380, 38);
		} else {
			if (Inventory.getItem(Variables.scrollId) != null) {
				g.drawString(
						"Scrolls Left: "
								+ Inventory.getItem(Variables.scrollId)
										.getStackSize(), 380, 38);
			} else {
				g.drawString("Scrolls Left: " + "0", 380, 38);
			}
		}

		// ----
		g.drawString("Familiar Time Left: " + Methods.getTimeLeft(), 560, 12);
		g.drawString("Items Produced: " + Variables.produced, 560, 25);

		g.setColor(Color.GRAY);
		g.setFont(new Font("Kristen ITC", Font.BOLD, 11));
		g.drawString("Draynor Spawning by OneLuckyDuck", 5, 382);

		// -------------------------------------------

		if (Variables.mode != 2) {
			g.setColor(Color.GREEN);
			for (GroundItem gi : GroundItems.getLoaded()) {
				if (Variables.lootList.contains(gi.getId())) {
					for (Polygon p1 : gi.getBounds()) {
						g.drawPolygon(p1);
					}
				}
			}
			g.setColor(Color.MAGENTA);
			for (Tile t : Const.CAST_AREA.getBoundingTiles()) {
				Point q = Calculations.worldToMap(t.getX(), t.getY());
				g.drawRect(q.x, q.y, 2, 2);
			}
		}

		g.setColor(Color.BLACK);
		for (Tile t1 : main.global.Const.BANK_AREA.getBoundingTiles()) {
			for (Polygon y : t1.getBounds()) {
				g.drawPolygon(y);
			}
		}
	}
}
