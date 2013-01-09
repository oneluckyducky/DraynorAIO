package spawner.misc;

import java.awt.Point;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import main.DraynorAIO;
import main.Summoning;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class Methods {

	public static boolean isFamiliarSummoned() {
		return Settings.get(1786) == 5760;
	}

	
	
	public static boolean openBank() {
		final NPC banker = NPCs.getNearest(main.global.Const.BANKERS);
		if (banker != null) {
			if (banker.click(false) && Menu.isOpen() && Menu.contains("Bank")) {
				return Menu.select("Bank", "Banker");
			}
		}
		return false;
	}

	public static boolean isInBank() {
		return main.global.Const.BANK_AREA.contains(Players.getLocal().getLocation());
	}

	public static void stop() {
		main.global.Methods.s("Out of scrolls");
		Bank.close();
		Task.sleep(300);
		Game.logout(true);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
						"Ran for: " + DraynorAIO.timer.toElapsedString()
								+ "\nPapayas Spawned: " + Variables.collected
								+ "\nProfit: " + Variables.profit);
			}
		});
		stop();
	}

	public static boolean openSummoning() {
		final WidgetChild s = Widgets.get(747).getChild(5);
		final Point p = s.getAbsoluteLocation();

		Mouse.hop(p.x, p.y);
		return Mouse.click(true);
	}

	public static int getSpecialLeft() {
		return Summoning.getSpecialPoints();
	}

	public static String getScrollsLeftString() {
		return Integer.toString(getScrollsLeft());
	}

	public static int getScrollsLeft() {
		if(Tabs.getCurrent() != Tabs.INVENTORY) {
			String amount = Widgets.get(662).getChild(66).getText();
			if (amount.contains("K")) {
				amount = amount.replace("K", "000");
			}
			return Integer.parseInt(amount);
		} else {
			final Item scrolls = Inventory.getItem(12423);
			if(scrolls != null) {
				return scrolls.getStackSize();
			}
		}
		return -1;
	}

	public static String getTimeLeft() {
		return Widgets.get(662).getChild(43).getText();
	}
}
