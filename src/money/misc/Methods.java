package money.misc;

import java.awt.Point;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Environment;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.bot.Context;

public class Methods {

	public final static void stop(final int i) {
		if (Bank.isOpen()) {
			Bank.close();
		}
		final String name = Environment.getDisplayName() + "-BF"
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
						i == 0 ? "Out of baskets!" : "Out of fruit!", 0);
			}
		});
		Context.get().getScriptHandler().shutdown();
	}

	public final static void fillBaskets() {
		final Item basket = Inventory.getItem(Constant.BASKET);
		if (basket != null) {
			for (int i = 0; i < 4; i++) {
				Item b = Inventory.getItemAt(i);
				if (b.getId() == Constant.BASKET) {
					Point p = Inventory.getItemAt(i).getWidgetChild()
							.getCentralPoint();
					Mouse.hop(p.x, p.y);
					Mouse.click(true);
					Variables.made++;
					Task.sleep(100);
				}
			}
			Task.sleep(750);
		}
	}

	public static boolean bankContains(int id) {
		return Bank.isOpen() && Bank.getItemCount(id) > 0;

	}
}
