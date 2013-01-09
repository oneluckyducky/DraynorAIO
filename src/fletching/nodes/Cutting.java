package fletching.nodes;

import main.global.Const;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import fletching.misc.Methods;
import fletching.misc.Variables;

public class Cutting extends Node {

	@Override
	public boolean activate() {
		// main.global.Methods.s("cu activate");
		return main.global.Methods.isIn()
				&& Methods.playerHasItem(Variables.logType)
				&& Variables.action == 0;
	}

	@Override
	public void execute() {
		if (Tabs.getCurrent() != Tabs.INVENTORY) {
			Tabs.INVENTORY.open();
			Task.sleep(600);
		}
		final Item log = Inventory.getItem(Variables.logType);
		if (log != null) {
			if (log.getWidgetChild().click(false) && Menu.isOpen()
					&& Menu.contains("Craft")) {
				Menu.select("Craft");
				Task.sleep(250);

			}
			final Timer timer1 = new Timer(1500);
			final Widget fletch = Widgets.get(1370); // 38
			final WidgetChild chooseKnife = Widgets.get(1179, 33);
			while (timer1.isRunning() && !fletch.getChild(38).validate()
					&& !chooseKnife.validate()) { // Variables.bowType == 0 ? 14
													// : 15
				Task.sleep(100);
				if (fletch.getChild(38) != null
						&& fletch.getChild(38).isOnScreen()
						|| chooseKnife != null && chooseKnife.isOnScreen()) {
					main.global.Methods.s("first menu");
					break;
				}
			}
			if (chooseKnife != null && chooseKnife.isOnScreen()) {
				main.global.Methods.s("clicking menu");
				chooseKnife.click(true);
				Task.sleep(200);
				final Timer timer = new Timer(1500);
				while (timer.isRunning() && !fletch.getChild(38).validate()) {
					Task.sleep(100);
					if (fletch.getChild(38) != null
							&& fletch.getChild(38).isOnScreen()) {
						break;
					}
				}
			}
			main.global.Methods.s("Cutting bow");
			fletch.getChild(38).click(true);
			while (fletch != null && fletch.validate()) {
				Task.sleep(50);
			}
			Mouse.click(NPCs.getNearest(Const.BANKERS).getCentralPoint(), false);
			Mouse.move(Mouse.getLocation().x,
					Mouse.getLocation().y + Random.nextInt(25, 35));
			final Timer timer = new Timer(0);
			while (Inventory.getCount(Variables.logType) > 0) {
				Task.sleep(50);
				if (timer.getElapsed() > ((Inventory
						.getCount(Variables.logType) * 600) + 1000)
						&& Inventory.getCount(Variables.logType) < 1) {
					break;
				}
			}
		}

	}

}
