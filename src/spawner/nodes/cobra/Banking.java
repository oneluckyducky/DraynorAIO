package spawner.nodes.cobra;

import main.Summoning;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.input.Mouse.Speed;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

import spawner.misc.Enums.Eggs;
import spawner.misc.Methods;
import spawner.misc.Variables;

public class Banking extends Node {

	@Override
	public boolean activate() {
		return main.global.Methods.isIn() && Methods.isInBank()
				&& Inventory.getItem(Eggs.CHICKEN_EGG.getId()) == null

				|| main.global.Methods.isIn() && Methods.isInBank()
				&& Inventory.getItem(Variables.scrollId) == null

				|| main.global.Methods.isIn() && !Summoning.isFamiliarSummoned()
				&& Inventory.getCount(Variables.pouchId) == 0
				&& Methods.isInBank();
	}

	@Override
	public void execute() {
		main.global.Methods.s("Opening bank");
		if (Methods.openBank()) {
			while (!Bank.isOpen()) {
				Task.sleep(200);
			}
		}
		if (Bank.isOpen()) {
			Mouse.setSpeed(Speed.SLOW);
			int eggs = Inventory.getCount(Eggs.COCKATRICE_EGG.getId());
			Bank.depositInventory();
			Variables.collected += eggs;
			Variables.profit = Variables.collected
					* main.global.Methods.getPrice(Eggs.COCKATRICE_EGG.getId());
			eggs = 0;
			Task.sleep(800);
			if (Inventory.getItem(Variables.scrollId) == null
					&& Bank.getItemCount(Variables.scrollId) == 0
					|| Methods.getScrollsLeft() == 9999
					&& Bank.getItemCount(Variables.scrollId) == 0
					|| Inventory.getItem(Eggs.CHICKEN_EGG.getId()) == null
					&& Bank.getItemCount(Eggs.CHICKEN_EGG.getId()) == 0) {
				Methods.stop();
			}
			if (!Summoning.isFamiliarSummoned()
					&& Inventory.getCount(Variables.pouchId) == 0) {
				main.global.Methods.s("Withdrawing new pouch");
				Bank.withdraw(Variables.pouchId, 1);
				Task.sleep(800);
			}
			if (Inventory.getItem(Variables.scrollId) == null
					&& Bank.getItem(Variables.scrollId).getStackSize() >= 1) {
				main.global.Methods.s("Withdrawing more scrolls");
				Bank.withdraw(Variables.scrollId, 0);
				Task.sleep(500);
			}
			if (Inventory.getCount(Eggs.CHICKEN_EGG.getId()) == 0) {
				main.global.Methods.s("Withdrawing eggs");
				Bank.withdraw(Eggs.CHICKEN_EGG.getId(), 27);
				Task.sleep(500);
			}
			Bank.withdraw(Variables.scrollId, 0);
			Task.sleep(200);
			main.global.Methods.s("Closing bank");
			Bank.close();
			Task.sleep(200);
			Mouse.setSpeed(Speed.FAST);
		}
	}
}
