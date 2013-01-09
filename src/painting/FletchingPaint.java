package painting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import main.DraynorAIO;

import org.powerbot.game.api.methods.tab.Skills;

import fletching.misc.Variables;

public class FletchingPaint {

	public static void onRepaint(Graphics g) {

		int expGain = (Skills.getExperience(Skills.FLETCHING) - Variables.startXp);
		int expHour = main.global.Methods.getPerHour(expGain,
				DraynorAIO.startTime);
		int actionHour = main.global.Methods.getPerHour(Variables.actions,
				DraynorAIO.startTime);

		String expGained = DecimalFormat.getInstance().format(expGain);
		String expHourly = DecimalFormat.getInstance().format(expHour);
		String actionHourly = DecimalFormat.getInstance().format(actionHour);

		g.drawString("Experience Gained: " + expGained + "  (" + expHourly
				+ ")", 3, 25);
		g.drawString(Variables.action == 0 ? "Bows Cut: " + Variables.actions
				+ "  (" + actionHourly + ")" : "Bows Strung: "
				+ Variables.actions + "  (" + actionHourly + ")", 3, 38);

		g.setColor(Color.GRAY);
		g.setFont(new Font("Kristen ITC", Font.BOLD, 11));
		g.drawString("Draynor Fletching by OneLuckyDuck", 5, 382);

	}
}
