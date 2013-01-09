package fletching.misc;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Environment;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;
import org.powerbot.game.bot.Context;

import fletching.misc.Enums.LogTypes;

public class Methods {

	public static void stop(final int i) {
		if (Bank.isOpen()) {
			Bank.close();
		}
		final String name = Environment.getDisplayName() + "-" + "FL"
				+ (char) Random.nextInt(65, 90) + "-"
				+ Integer.toString((Random.nextInt(0, 100)));
		Context.saveScreenCapture(name.toUpperCase());
		Game.logout(true);
		Task.sleep(4000);
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
						"To view your progress report go to this image\n\n"
								+ Environment.getStorageDirectory() + "\\"
								+ name.toUpperCase() + ".png",
						i == 0 ? "Out of bows!" : i == 1 ? "Out of bow string!"
								: "Out of logs!", 0);
			}
		});
		Context.get().getScriptHandler().shutdown();
	}

	public static final boolean playerHasItem(int id) {
		for (Item i : Inventory.getItems()) {
			if (i.getId() == id) {
				return true;
			}
		}
		return false;
	}

	public static final boolean withdraw(int item) {
		Widget bank = Widgets.get(762);
		WidgetChild items = bank.getChild(95);
		int baseX = items.getAbsoluteX(), baseY = items.getAbsoluteY();
		for (WidgetChild child : items.getChildren()) {
			if (child.getChildId() == item) {
				Mouse.click(child.getRelativeX() + baseX
						+ (child.getWidth() / 2), child.getRelativeY() + baseY
						+ (child.getHeight() / 2), false);
				return Menu.select(Variables.action == 0 ? "Withdraw-All"
						: "Withdraw-14");
			}
		}
		return false;
	}

	public static boolean bankContains(int id) {
		return Bank.isOpen() && Bank.getItemCount(id) > 0;

	}
}
