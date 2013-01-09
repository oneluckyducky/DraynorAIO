package fishing.misc;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.bot.Context;

public class Methods {
	
	public static void mousekeyDrop() {
		Item[] items = Inventory.getItems();
		for (int col = 0; col < 4; col++) {
			int mouseX = (Inventory.getItemAt(col).getWidgetChild()
					.getCentralPoint().x + Random.nextInt(-10, 10));
			int rowOffset = Random.nextInt(-10, 10);
			int mouseY = (Inventory.getItemAt(col).getWidgetChild()
					.getCentralPoint().y + rowOffset);
			Mouse.move(mouseX, mouseY);
			for (int row = 0; row < 7; row++) {
				if (items[row * 4 + col].getId() != -1
						&& items[row * 4 + col].getId() != Const.FISHING_BAIT
						&& items[row * 4 + col].getId() != 314) {
					Mouse.hop(mouseX, Inventory.getItemAt(row * 4 + col)
							.getWidgetChild().getCentralPoint().y
							+ rowOffset);
					//Task.sleep(20);
					Mouse.click(false);
					while (Menu.isOpen() == false) {
						Task.sleep(10);
					}
					String[] acts = Menu.getActions();
					for (int a = 0; a < acts.length; a++) {
						if (acts[a].contains("Drop")) {
							Mouse.hop(mouseX, Menu.getLocation().y + 25 + a
									* 20);
						//	Task.sleep(20);
							Mouse.click(true);
						}
					}
				}
			}
		}
	}

	public static boolean interact(NPC n, String action, String name) {
		if (name.length() == 0) {
			if (n.click(false) && Menu.isOpen() && Menu.contains(action)) {
				return Menu.select(action);
			}
		} else {
			if (n.click(false) && Menu.isOpen() && Menu.contains(action, name)) {
				return Menu.select(action, name);
			}
		}
		return false;
	}

	public static boolean inventoryReady() {
		return !Inventory.isFull() && Variables.mode == 0 ? true : Inventory
				.getItem(Const.FISHING_BAIT) != null;
	}

	public static boolean bankContains(int id) {
		return Bank.isOpen() && Bank.getItemCount(id) > 0;

	}

	public static boolean isInFishingArea() {
		return fishing.misc.Const.FISHING_AREA.contains(Players.getLocal()
				.getLocation());

	}

	public static void stopBait() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(),
						"Out of bait, stopping script!");

			}

		});
		Task.sleep(5000);
		Game.logout(true);
		Context.get().getScriptHandler().shutdown();
	}

}
