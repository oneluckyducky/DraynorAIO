package money.nodes.basket;

import java.awt.Point;

import money.misc.Constant;
import money.misc.Methods;
import money.misc.Variables;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.Item;

public class Banking extends Node {

	@Override
	public boolean activate() {
		return main.global.Methods.isIn() && main.global.Methods.isInBank()
				&& Inventory.getCount(Constant.BASKET) <= 1
				|| main.global.Methods.isIn() && main.global.Methods.isInBank()
				&& Inventory.getCount(Variables.fruitId) == 0;
	}

	@Override
	public void execute() {
		main.global.Methods.s("Opening bank");
		if(!Bank.isOpen()) {
			if (main.global.Methods.openBank()) {
				while (!Bank.isOpen()) {
					Task.sleep(200);
				}
			}
		}
		if (Bank.isOpen()) {
			if(Inventory.getCount() > 0) {
				main.global.Methods.s("Depositing Inventory");
				Bank.depositInventory();
				Task.sleep(300);
			}

			if (!Methods.bankContains(Constant.BASKET)) {
				Methods.stop(0);
			}
			if (!Methods.bankContains(Variables.fruitId)) {
				Methods.stop(1);
			}

			if (Inventory.getCount(Constant.BASKET) == 0) { // 45
				main.global.Methods.s("Withdrawing 5 baskets");
				final Item b = Bank.getItem(Constant.BASKET);
				if (b != null) {
					final Point pb = b.getWidgetChild().getCentralPoint();
					Mouse.move(pb.x, pb.y);
					if (Mouse.click(false) && Menu.isOpen()
							&& Menu.contains("Withdraw-5", "Basket")) {
						Mouse.hop(pb.x, pb.y + 45);
						Mouse.click(true);
					}
					final Timer t = new Timer(0);
					while (Inventory.getCount(Constant.BASKET) < 1) {
						Task.sleep(200);
						if(t.getElapsed() > 1400) {
							break;
						}
					}
				}
				// Bank.withdraw(Constant.BASKET, 4);
				// Task.sleep(200);
			}
			if (Inventory.getItem(Variables.fruitId) == null) { // 110
				main.global.Methods.s("Withdrawing fruit");
				final Item f = Bank.getItem(Variables.fruitId);
				Point fb = f.getWidgetChild().getCentralPoint();
				if (fb != null) {
					Mouse.move(fb.x, fb.y);
					if (Mouse.click(false) && Menu.isOpen()
							&& Menu.contains("Withdraw-All")) {
						Mouse.hop(fb.x, fb.y + 110);
						Mouse.click(true);
						final Timer t = new Timer(0);
						while (Inventory.getCount(Variables.fruitId) < 5) {
							Task.sleep(200);
							if(t.getElapsed() > 1400) {
								break;
							}
						}
					}
				}
				// Bank.withdraw(Variables.fruitId, 0);
				// Task.sleep(200);
			}
			if (Inventory.getCount(Variables.fruitId) > 4
					&& Inventory.getCount(Constant.BASKET) == 5) {
				Bank.close();
			}
		}
	}
}
